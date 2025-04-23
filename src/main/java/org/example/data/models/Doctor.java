package org.example.data.models;

import jakarta.persistence.*;
import lombok.Data;
import org.example.data.enums.Gender;
import org.example.data.enums.Specialization;


import java.util.List;

@Data
@Entity
public class Doctor extends User{

    private Specialization specialization;
    private Gender gender;
    @OneToMany
    public List<Appointment> appointments;

}
