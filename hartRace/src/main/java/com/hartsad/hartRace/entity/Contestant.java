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
@Table(name = "contenders")
public class Contestant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="First_Name")
    private String fName;

    @Column(name ="Last_Name")
    private String lName;

    @Column(name ="age")
    private int age;

    @Column(name ="gender")
    private String gender;

    @Column(name ="event")
    private String event;

    @Column(name ="item")
    private String item;

    @Column(name ="isocode")
    private String isocode;

    @Column(name ="cname")
    private String cname;

    @Column(name = "gold",nullable = true)
    private int gold;

    @Column(name="silver",nullable = true)
    private int silver;

    @Column(name="bronze",nullable = true)
    private int bronze;


}
