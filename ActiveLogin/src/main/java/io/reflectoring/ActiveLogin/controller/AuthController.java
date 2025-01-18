package io.reflectoring.ActiveLogin.controller;

import io.reflectoring.ActiveLogin.models.User;
import io.reflectoring.ActiveLogin.repository.UserRepository;
import io.reflectoring.ActiveLogin.security.JwtUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already taken!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String token = jwtUtils.generateToken(user.getUsername());
            return ResponseEntity.ok(new AuthResponse(token, "3600"));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @Getter
    @Setter
    public static class AuthResponse {
        private String access_token;
        private String expires_in;

        public AuthResponse(String access_token, String expires_in) {
            this.access_token = access_token;
            this.expires_in = expires_in;
        }

        // Getters and Setters
    }
}
