package org.example.services;

import org.example.data.models.Appointment;
import org.example.data.models.Doctor;
import org.example.data.models.Patient;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface PatientServices {

    Patient registerPatient(Patient patient);
    Patient login(Patient patient);
    Appointment bookAppointment(Patient patient, Doctor doctor, Date appointmentDate, Time appointmentTime);
    List<Appointment> getAppointments();
    void cancelAppointment(Long id);
    void printAppointments();

}
