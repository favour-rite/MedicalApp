package org.example.data.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.util.*;

@Entity
@Data
public class MedicalHistory {

    private Long date;
    private String time;
    private String description;
    private String status;

//    private List<Appointment> appointments = new ArrayList<Appointment>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}