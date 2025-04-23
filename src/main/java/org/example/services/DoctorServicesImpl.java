package org.example.services;

import org.example.data.models.Appointment;
import org.example.data.models.Doctor;
import org.example.data.models.Patient;
import org.example.data.respositories.AppointmentRepository;
import org.example.data.respositories.DoctorRepository;
import org.example.data.respositories.MedicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
public class DoctorServicesImpl implements DoctorServices {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;


    @Override
    public Doctor registerDoctor(Doctor doctor) {
            Doctor existingDoctor = doctorRepository.findByName(doctor.getName());
            if (existingDoctor == null) {
                Doctor newDoctor = new Doctor();
                newDoctor.setName(doctor.getName());
                newDoctor.setEmail(doctor.getEmail());
                newDoctor.setPassword(doctor.getPassword());
                newDoctor.setPhoneNumber(doctor.getPhoneNumber());
                doctorRepository.save(newDoctor);
                return newDoctor;
            }
            throw new IllegalArgumentException("Username already exists.");

    }

    @Override
    public List<Appointment> viewAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment scheduleAppointment(Doctor doctor, Patient patient, Date appointmentDate, Time appointmentTime, AppointmentRepository appointmentRepository) {
        return null;
    }

    @Override
    public Appointment scheduleAppointment(Doctor doctor, Date appointmentDate, Time appointmentTime, AppointmentRepository appointmentRepository) {
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setAppointmentTime(appointmentTime);

        return appointmentRepository.save(appointment);
    }

    @Override
    public Doctor login(String email, String password) {
        Doctor existingDoctor = doctorRepository.findByName(doctor.getName());
        if (existingDoctor == null) {
            throw new IllegalArgumentException("Username does not exist.");
        }
        return existingDoctor;
    }
    @Override
    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }




}
