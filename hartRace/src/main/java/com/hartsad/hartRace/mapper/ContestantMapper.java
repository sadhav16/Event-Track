package com.hartsad.hartRace.mapper;


import com.hartsad.hartRace.dto.ContestantDto;
import com.hartsad.hartRace.entity.Contestant;

public class ContestantMapper {
    public static ContestantDto maptoEdto(Contestant contestant){
        return new ContestantDto(
                contestant.getId(),
                contestant.getFName(),
                contestant.getLName(),
                contestant.getAge(),
                contestant.getGender(),
                contestant.getEvent(),
                contestant.getItem(),
                contestant.getIsocode(),
                contestant.getCname(),
                contestant.getGold(),
                contestant.getSilver(),
                contestant.getBronze()
        );
    }

    public static Contestant maptoE(ContestantDto emp){
        return new Contestant(
                emp.getId(),
                emp.getFName(),
                emp.getLName(),
                emp.getAge(),
                emp.getGender(),
                emp.getEvent(),
                emp.getItem(),
                emp.getIsocode(),
                emp.getCname(),
                emp.getGold(),
                emp.getSilver(),
                emp.getBronze()
        );
    }
}
