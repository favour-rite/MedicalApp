package org.example.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Data
@Entity
public class Patient extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Gender gender;

}
