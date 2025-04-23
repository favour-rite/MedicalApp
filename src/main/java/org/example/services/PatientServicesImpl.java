package org.example.services;

import org.example.data.models.Appointment;
import org.example.data.models.Doctor;
import org.example.data.models.Patient;
import org.example.data.respositories.AppointmentRepository;
import org.example.data.respositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;


@Service
public class PatientServicesImpl implements PatientServices {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Patient registerPatient(Patient patient) {
        Patient existingPatient = patientRepository.findByName(patient.getName());
        if (existingPatient == null) {
//            Patient newPatient = new Patient();
            patient.setName(patient.getName());
            patient.setEmail(patient.getEmail());
            patient.setPassword(patient.getPassword());
            patient.setPhoneNumber(patient.getPhoneNumber());
            patientRepository.save(patient);
            return patient;
        }
            throw new IllegalArgumentException("Username already exists.");
    }
    @Override
    public Patient login(Patient patient) {
        Patient existingPatient = patientRepository.findByName(patient.getName());
        if (existingPatient == null) {
            throw new IllegalArgumentException("Username does not exist.");
        }
        return existingPatient;
    }
    @Override
    public Appointment bookAppointment(Patient patient, Doctor doctor, Date appointmentDate, Time appointmentTime) {
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setAppointmentTime(appointmentTime);
        return appointmentRepository.save(appointment);

    }

    @Override
    public void cancelAppointment(Long id){
        appointmentRepository.deleteById(String.valueOf(id));
    }

    @Override
    public void printAppointments() {
        for (Appointment appointment : appointmentRepository.findAll()) {
            System.out.println("Appointment Details:");
            System.out.println("Patient: " + appointment.getPatient().getEmail());
            System.out.println("Doctor: " + appointment.getDoctor().getName());
            System.out.println("Date: " + appointment.getAppointmentDate());
            System.out.println("Time: " + appointment.getAppointmentTime());
            System.out.println("-------------------------------");
        }
    }

    @Override
    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }


}
