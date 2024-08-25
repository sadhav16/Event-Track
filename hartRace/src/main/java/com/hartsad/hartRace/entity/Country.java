package com.hartsad.hartRace.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="countries")
public class Country {
    @Id
    @Column(name="countryCode")
    private String countryCode;

    @Column(name="cname")
    private String cname;

    @Column(name="gold")
    private int gold;

    @Column(name="silver")
    private int silver;

    @Column(name="bronze")
    private int bronze;

    @Column(name="points")
    private int points;


}
