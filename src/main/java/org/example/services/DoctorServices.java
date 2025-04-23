package org.example.services;

import org.example.data.models.Appointment;
import org.example.data.models.Doctor;
import org.example.data.models.Patient;
import org.example.data.respositories.AppointmentRepository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface DoctorServices {
    Doctor login(Doctor doctor);

    Doctor registerDoctor(Doctor doctor);

    List<Appointment> viewAppointments();

    Appointment scheduleAppointment(Doctor doctor,Patient patient, Date appointmentDate, Time appointmentTime, AppointmentRepository appointmentRepository);

    Appointment scheduleAppointment(Doctor doctor, Date appointmentDate, Time appointmentTime, AppointmentRepository appointmentRepository);

    Doctor login(String email, String password);

    List<Appointment> getAppointments();
}
