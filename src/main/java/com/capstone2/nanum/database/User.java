package com.capstone2.nanum.database;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


//User table 만들기
@Entity
@Data
@ToString
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String password;
    @Column(unique = true)
    private String email;
    private String gender;


}
