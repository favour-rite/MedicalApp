package org.example.services;

import org.example.data.models.*;
import org.example.data.respositories.PatientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PatientServicesImplTest {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientServices patientServices;

    @Test
    public void testThatPatientCanRegister() {
        Patient patient = new Patient();
        patient.setName("Mary Baby");
        patient.setPhoneNumber("0908182734");
        patient.setPassword("password");
        patient.setEmail("figwe101@gmail.com");
        Patient registeredPatient = patientServices.registerPatient(patient);
        assertEquals("Mary Baby", patient.getName());
        assertEquals("0908182734", patient.getPhoneNumber());
        assertEquals("password", patient.getPassword());

    }
    @Test
    public void testThatPatientCanLogin() {
        Patient patient = new Patient();
        patient.setName("Mary Baby");
        patient.setPhoneNumber("0908182734");
        patient.setPassword("password");
        patient.setEmail("figwe101@gmail.com");
        Patient registeredPatient = patientServices.registerPatient(patient);
        Patient login = patientServices.login(patient);
        assertEquals("password", patient.getPassword());
        assertEquals("figwe101@gmail.com", patient.getEmail());
    }
    @Test
    public void testThatPatientCannotLoginWithWrongCredentials() {
        Patient patient = new Patient();
        patient.setPassword("Wrong password");
        patient.setEmail("figwe2001@gmail.com");
        assertThrows(IllegalArgumentException.class, () -> patientServices.login(patient));
    }
    @Test
    public void testThatPatientCanBookAppointmentWhenHeOrSheIsLoggedIn() {
        Patient patient = new Patient();
        patient.setPassword("password");
        patient.setEmail("figwe101@gmail.com");
        patientRepository.save(patient);

        Date appointmentDate = new Date();
        Time appointmentTime = new Time(10-23-45);
        Doctor doctor = new Doctor();
        doctor.setName("Dayo");
        doctor.setEmail("dayomeji@gmail.com");
        doctor.setPassword("password");
        doctor.setPhoneNumber("0909897234");
        doctor.setGender(Gender.FEMALE);
        doctor.setSpecialization(Specialization.CARDIOLOGIST);

        Patient patient1 = new  Patient();
        patient1.setName("Babe");
        patient.setEmail( "babe@gmail.com");
        patient1.setPassword("mypassword");
        patient1.setGender(Gender.MALE);
        Appointment appointment = new Appointment();

        Patient bookAppointment = patientServices.bookAppointment(doctor,appointmentDate, appointmentTime);

        assertEquals(1, patientServices.getAppointments().size());
        Appointment bookedAppointment = patientServices.getAppointments().getFirst();
        assertEquals(String.valueOf(appointmentDate), bookedAppointment.getAppointmentDate());
        assertEquals(String.valueOf(appointmentTime), bookedAppointment.getAppointmentTime());
        assertEquals(doctor, bookedAppointment.getDoctor());
        assertEquals(patient, bookedAppointment.getPatient());

    }

    @AfterEach
    public void tearDown(){
        patientRepository.deleteAll();
    }
}
