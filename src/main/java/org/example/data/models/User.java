package org.example.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class User {


    private String name;
    private String password;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


}
