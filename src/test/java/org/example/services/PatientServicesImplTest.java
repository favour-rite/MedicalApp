package org.example.services;

import org.example.data.enums.Gender;
import org.example.data.enums.Specialization;
import org.example.data.models.*;
import org.example.data.respositories.*;
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
    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void testThatPatientCanRegister() {
        Patient patient = new Patient();
        patient.setName("Mary Baby");
        patient.setPhoneNumber("0908182734");
        patient.setPassword("password");
        patient.setEmail("figwe101@gmail.com");
        patientRepository.save(patient);

        Patient registeredPatient = patientServices.registerPatient(patient);
        assertEquals("Mary Baby", patient.getName());
        assertEquals("0908182734", patient.getPhoneNumber());


    }
    @Test
    public void testThatPatientCanLogin() {
        Patient patient = new Patient();
        patient.setName("Mary Baby");
        patient.setPhoneNumber("0908182734");
        patient.setPassword("password");
        patient.setEmail("figwe101@gmail.com");
        patientRepository.save(patient);

        Patient registeredPatient = patientServices.registerPatient(patient);
        Patient login = patientServices.login(patient);
        assertEquals("password", patient.getPassword());
        assertEquals("figwe101@gmail.com", patient.getEmail());
        assertEquals(login, registeredPatient);
    }
    @Test
    public void testThatPatientCannotLoginWithWrongCredentials() {
        Patient patient = new Patient();
        patient.setPassword("Wrong password");
        patient.setEmail("figwe2001@gmail.com");
        assertThrows(IllegalArgumentException.class, () -> patientServices.login(patient));
    }
    @Test
    public void testThatPatientCannotRegisterWithSameCredentialsTwice() {
        Patient patient = new Patient();
        patient.setName("Santa Clause");
        patient.setPhoneNumber("0908182734");
        patient.setPassword("christmas");
        patient.setEmail("santaclause@gmail.com");
        Patient registeredPatient1 = patientServices.registerPatient(patient);
        assertEquals(registeredPatient1, patient);

        patient.setName("Santa Clause");
        patient.setPhoneNumber("0908182734");
        patient.setPassword("christmas");
        patient.setEmail("santaClause@gmail.com");
        Patient registeredPatient = patientServices.registerPatient(patient);
        assertThrows(IllegalArgumentException.class, () -> patientServices.registerPatient(patient));
    }
    @Test
    public void testThatPatientCanBookAppointmentWhenHeOrSheIsLoggedIn() {
        Patient patient = new Patient();
        patient.setPassword("password");
        patient.setEmail("figwe101@gmail.com");
        patientRepository.save(patient);

        Doctor doctor = new Doctor();
        doctor.setName("Dr. Day");
        doctor.setEmail("dayomeji@gmail.com");
        doctor.setPassword("password");
        doctor.setPhoneNumber("0909897234");
        doctor.setGender(Gender.FEMALE);
        doctor.setSpecialization(Specialization.CARDIOLOGIST);
        doctorRepository.save(doctor);

        Date appointmentDate = new Date(System.currentTimeMillis());
        Time appointmentTime = Time.valueOf("10:23:45");

        Appointment appointment = patientServices.bookAppointment(patient,doctor, appointmentDate, appointmentTime);
        assertNotNull(appointment);
        assertEquals(1, patientServices.getAppointments().size());

        Appointment bookedAppointment = patientServices.getAppointments().getFirst();
        assertEquals(appointment, bookedAppointment.getAppointmentDate());
        assertEquals(appointment, bookedAppointment.getAppointmentTime());
        assertEquals(doctor, bookedAppointment.getDoctor());
        assertEquals(patient, bookedAppointment.getPatient());
    }
    @Test
    public void testThatPatientCanCancelAppointment() {

        Patient patient = new Patient();
        patient.setPassword("password");
        patient.setEmail("figwe101@gmail.com");
        patientRepository.save(patient);

        Doctor doctor = new Doctor();
        doctor.setName("Day");
        doctor.setEmail("dayomeji@gmail.com");
        doctor.setPassword("password");
        doctor.setPhoneNumber("0909897234");
        doctor.setGender(Gender.FEMALE);
        doctor.setSpecialization(Specialization.CARDIOLOGIST);

        Date appointmentDate = new Date(System.currentTimeMillis());
        Time appointmentTime = Time.valueOf("10: 23:45");

        Appointment bookedAppointment = patientServices.bookAppointment(patient, doctor, appointmentDate, appointmentTime);
        assertNotNull(bookedAppointment);
        assertEquals(1, patientServices.getAppointments().size());
        patientServices.cancelAppointment(bookedAppointment.getId());
        assertEquals(0, patientServices.getAppointments().size());
    }
    @Test
    public void testThatAppointmentsBookedCanBePrinted() {

        Patient patient = new Patient();
        patient.setPassword("password");
        patient.setEmail("figwe101@gmail.com");
        patientRepository.save(patient);

        Doctor doctor = new Doctor();
        doctor.setName("Day");
        doctor.setEmail("dayomeji@gmail.com");
        doctor.setPassword("password");
        doctor.setPhoneNumber("0909897234");
        doctor.setGender(Gender.FEMALE);
        doctor.setSpecialization(Specialization.CARDIOLOGIST);

        Date appointmentDate = new Date(System.currentTimeMillis());
        Time appointmentTime = Time.valueOf("10:23:45");

        Appointment bookedAppointment = patientServices.bookAppointment(patient, doctor, appointmentDate, appointmentTime);
        patientServices.printAppointments();
    }


    @AfterEach
    public void tearDown(){
        patientRepository.deleteAll();
    }
}
