package org.example.data.respositories;

import org.example.data.models.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("appointmentRepositoryMongo")
public interface AppointmentRepository extends MongoRepository<Appointment, String> {




}
