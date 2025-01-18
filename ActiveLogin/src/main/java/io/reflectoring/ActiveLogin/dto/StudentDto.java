package io.reflectoring.ActiveLogin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private Long tkmId;
    private int year;
    private String firstName;
    private String lastName;
    private String email;
    private String rollNo;
    private Integer actpts;
}
