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
            Patient newPatient = new Patient();
            newPatient.setName(patient.getName());
            newPatient.setEmail(patient.getEmail());
            newPatient.setPassword(patient.getPassword());
            newPatient.setPhoneNumber(patient.getPhoneNumber());
            patientRepository.save(newPatient);
            return newPatient;
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
    public Patient bookAppointment(Doctor doctor, Date appointmentDate, Time appointmentTime) {
        return null;
    }

    @Override
    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }


}
