package org.example.data.respositories;

import org.example.data.models.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("doctorRepositoryMongo")
public interface DoctorRepository extends MongoRepository<Doctor,String> {
    Doctor findByName(String name);
}
