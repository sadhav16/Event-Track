package com.hartsad.hartRace.service;

import com.hartsad.hartRace.dto.CountryDto;
import com.hartsad.hartRace.dto.ContestantDto;

import java.util.List;

public interface ContestantService {
    ContestantDto createEmployee(ContestantDto empdto);
    ContestantDto getEmployeeById(Long employeeeId);
    List<ContestantDto> getAllEmployees();
    ContestantDto updateEmployee(Long Id, ContestantDto updatedemployee);
    void deleteEmployee(Long Id);
    void generateAndSaveRegistrations(int count);

    List<ContestantDto> eventContestants(String event);
    List<ContestantDto> itemParticipants(String item);
    List<ContestantDto> genderBased(String gender);

    List<ContestantDto> simulateItem(String event, String item, String gender);

    ContestantDto topMedalist();

    List<CountryDto> topNation(int n,String event);

    CountryDto topMedalistNation();

    String topcontestant(String gender);

    CountryDto findbystats(String type,String criteria);

    int simulateAll();

}
