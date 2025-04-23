package org.example.data.models;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Time;
import java.util.*;

@Entity
@Data
public class MedicalHistory {

    private Date appointmentDate;
    private Time appointmentTime;

    private String description;
    private String status;
    @Id
    private Long id;


}