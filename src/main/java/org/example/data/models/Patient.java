package org.example.data.models;

import jakarta.persistence.*;
import lombok.Data;
import org.example.data.enums.Gender;

import java.util.ArrayList;

import java.util.List;

@Data
@Entity
public class Patient extends User{

    private Gender gender;
    @OneToMany
    private List< Appointment> appointment = new ArrayList< Appointment>();

}
