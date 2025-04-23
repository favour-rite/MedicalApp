package org.example.data.respositories;

import org.example.data.models.MedicalHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalHistoryRepository extends MongoRepository<MedicalHistory,String> {


}
