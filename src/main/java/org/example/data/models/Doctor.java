package org.example.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
public class Doctor extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Specialization specialization;
    private Qualification qualification;
    private Gender gender;
    @OneToMany
    public List<Appointment> appointments;

}
