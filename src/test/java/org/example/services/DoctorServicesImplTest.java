package org.example.services;

import org.example.data.enums.Gender;
import org.example.data.enums.Specialization;
import org.example.data.models.*;
import org.example.data.respositories.AppointmentRepository;
import org.example.data.respositories.DoctorRepository;
import org.example.data.respositories.PatientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DoctorServicesImplTest {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorServices doctorServices;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientServices patientServices;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    public void testThatDoctorCanRegister() {
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Andrew");
        doctor.setPhoneNumber("0908146577");
        doctor.setPassword("staysafe");
        doctor.setEmail("andrew56@gmail.com");
        Doctor registeredDoctor = doctorServices.registerDoctor(doctor);
        assertEquals("Dr. Andrew", doctor.getName());
        assertEquals("0908146577", doctor.getPhoneNumber());
        assertEquals("staysafe", doctor.getPassword());

    }
    @Test
    public void testThatDoctorCanLogin() {
        Doctor doctor = new Doctor();
        doctor.setPassword("staysafe");
        doctor.setEmail("andrew56@gmail.com");
        Doctor registeredPatient = doctorServices.registerDoctor(doctor);
        Doctor login = doctorServices.login(doctor);
        assertEquals(registeredPatient, login);
        assertEquals("staysafe", doctor.getPassword());
        assertEquals("andrew56@gmail.com", doctor.getEmail());
    }
    @Test
    public void testThatDoctorCannotLoginWithWrongCredentials() {
        Doctor doctor = new Doctor();
        doctor.setPassword("Wrong password");
        doctor.setEmail("meandu2001@gmail.com");
        assertThrows(IllegalArgumentException.class, () -> doctorServices.login(doctor));
    }
    @Test
    public void testDoctorCanViewAppointmentsNumberOfTimesAppointmentsAreBookedByPatient() {
        Patient patient = new Patient();
        patient.setPassword("password");
        patient.setEmail("figwe101@gmail.com");
        patientRepository.save(patient);

        Doctor doctor = new Doctor();
        doctor.setName("Dayo");
        doctor.setEmail("dayomeji@gmail.com");
        doctor.setPassword("password");
        doctor.setPhoneNumber("0909897234");
        doctor.setGender(Gender.FEMALE);
        doctor.setSpecialization(Specialization.CARDIOLOGIST);

        Date appointmentDate = new Date(System.currentTimeMillis());
        Time appointmentTime1 = Time.valueOf("10:30:00");
        patientServices.bookAppointment(patient, doctor, appointmentDate, appointmentTime1);

        Time appointmentTime2 = Time.valueOf("10:30:00");
        patientServices.bookAppointment(patient, doctor, appointmentDate, appointmentTime2);

        List<Appointment> doctorAppointments = doctorServices.viewAppointments();

        assertEquals(2, doctorAppointments.size());

        for (Appointment appointment : appointmentRepository.findAll())  {
            System.out.println("-------------------------");
            System.out.println("Doctor: " + doctor.getName());
            System.out.println("Patient: " + appointment.getPatient().getEmail());
            System.out.println("Date: " + appointment.getAppointmentDate());
            System.out.println("Time: " + appointment.getAppointmentTime());
            System.out.println("-------------------------");
        }
    }
    @Test
    public void testScheduleAppointment() {
        Patient patient = new Patient();
        Doctor doctor = new Doctor();
        Date appointmentDate = new Date();
        Time appointmentTime = Time.valueOf("10:30:00");

        Appointment appointment = doctorServices.scheduleAppointment(doctor,appointmentDate, appointmentTime, appointmentRepository);

        assertNotNull(appointment);
        assertEquals(doctor, appointment.getDoctor());
        assertEquals(patient, appointment.getPatient());
        assertEquals(appointmentDate, appointment.getAppointmentDate());
        assertEquals(appointmentTime, appointment.getAppointmentTime());

        assertTrue(appointmentRepository.findById(String.valueOf(appointment.getId())).isPresent());
    }


    @AfterEach
    public void tearDown() {
        doctorRepository.deleteAll();
    }
}

