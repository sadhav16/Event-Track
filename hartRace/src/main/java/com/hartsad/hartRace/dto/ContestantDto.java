package com.hartsad.hartRace.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContestantDto {
    private Long id;
    private String fName;
    private String lName;
    private int age;
    private String gender;
    private String event;
    private String item;
    private String isocode;
    private String cname;
    private int gold;
    private int silver;
    private int bronze;
}
