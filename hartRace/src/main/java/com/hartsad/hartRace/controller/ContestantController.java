package com.hartsad.hartRace.controller;


import com.hartsad.hartRace.dto.CountryDto;
import com.hartsad.hartRace.dto.ContestantDto;
import com.hartsad.hartRace.service.ContestantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/hart")
public class ContestantController {
    private ContestantService contestantService;

    //BUILD Add Employee REST API
    @PostMapping
    public ResponseEntity<ContestantDto> createEmployee(@RequestBody ContestantDto contestantDto){
        ContestantDto savedEmployee = contestantService.createEmployee(contestantDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //Build GET emp REST API
    @GetMapping("{id}")
    public ResponseEntity<ContestantDto> getEmployeebyId(@PathVariable("id") Long empId){
        ContestantDto contestantDto =  contestantService.getEmployeeById(empId);
        return ResponseEntity.ok(contestantDto);

    }

    //Build GET ALL emp REST API
    @GetMapping
    public ResponseEntity<List<ContestantDto>> getAllEmployees(){
        List<ContestantDto> employees = contestantService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    //Build update emp REST API
    @PutMapping("{id}")
    public ResponseEntity<ContestantDto> updateEmployee(@PathVariable("id") Long empId,
                                                        @RequestBody ContestantDto updatedemp){
        ContestantDto contestantDto = contestantService.updateEmployee(empId,updatedemp);
        return ResponseEntity.ok(contestantDto);
    }

    //Build delete emp REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long empId){
        contestantService.deleteEmployee(empId);
        return ResponseEntity.ok("Contestant Deleted Successfuly");
    }


    //generate 1000 default contestants
    @PostMapping("/generate")
    public ResponseEntity<String> generateEmployees(@RequestParam(defaultValue = "10") int count) {
        contestantService.generateAndSaveRegistrations();
        return ResponseEntity.ok("5000 contestants added");
    }

    //get item contestants for a particular item
    @GetMapping("/getitemcontestents/{item}")
    public ResponseEntity<List<ContestantDto>> geteventcont(@PathVariable("item") String item){
        List<ContestantDto> itemtemps = contestantService.itemParticipants(item);
        return ResponseEntity.ok(itemtemps);
    }

    //get event contestants for a particular event
    @GetMapping("/geteventparticipants/{event}")
    public ResponseEntity<List<ContestantDto>> getitemcont(@PathVariable("event") String event){
        List<ContestantDto> eventemps = contestantService.eventContestants(event);
        return ResponseEntity.ok(eventemps);
    }


    //get item contestants for a particular gender type
    @GetMapping("/getparticipantsongender/{gender}")
    public ResponseEntity<List<ContestantDto>> getgencont(@PathVariable("gender") String gender){
        List<ContestantDto> gentemps = contestantService.genderBased(gender);
        return ResponseEntity.ok(gentemps);
    }

    //simulate/get all contestants for a particular event
    @GetMapping("/simulateevent")
    public ResponseEntity<List<ContestantDto>> simulateit(
                                                @RequestParam(name="event",required = true) String event,
                                                @RequestParam(name="item",required = true) String item,
                                                @RequestParam(name="gender",required = false) String gender){
        List<ContestantDto> simits = contestantService.simulateItem(event, item ,gender);
        return ResponseEntity.ok(simits);
    }


    //contestant with top number of medals
    @GetMapping("/topmedalist")
    public ResponseEntity<ContestantDto> mostDecorated(){
        ContestantDto topper = contestantService.topMedalist();
        return ResponseEntity.ok(topper);
    }

    @GetMapping("/topcontestant/{gender}")
    public ResponseEntity<String> topPointsCont(@PathVariable("gender") String gender){
            String flc = contestantService.topcontestant(gender);
            return ResponseEntity.ok(flc);
    }

    //top nation with highest number of medals
    @GetMapping("/topnation")
    public ResponseEntity<CountryDto> topmedaln(){
        CountryDto topc = contestantService.topMedalistNation();
        return ResponseEntity.ok(topc);
    }

    //top n nations with highest number of medals
    @GetMapping("/topnations")
    public ResponseEntity<List<CountryDto>> topgoldsnations(
                    @RequestParam(name="number",required = false) Integer n,
                    @RequestParam(name="event",required=false) String event){
        if( n == null) {
            n=1;
        }
        List<CountryDto> topn = contestantService.topNation(n,event);
        return ResponseEntity.ok(topn);
    }

    @GetMapping("/nationrankings")
    public ResponseEntity<CountryDto> statsonnations(
                            @RequestParam(name="type", required = true) String type,
                            @RequestParam(name="criteria",required = true) String criteria) {
        CountryDto nationresult = contestantService.findbystats(type,criteria);
        return ResponseEntity.ok(nationresult);
    }

    @GetMapping("/simulateall")
    public ResponseEntity<String> simall(){
        int eflag = contestantService.simulateAll();
        if(eflag==0) {
            return ResponseEntity.ok("All events with contestants simulated");
        }
        else{
            return ResponseEntity.ok("Some events did not meet Minimum participants count");
        }
    }

}
