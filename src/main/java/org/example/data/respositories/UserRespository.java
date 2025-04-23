package org.example.data.respositories;

import org.example.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepositoryMongo")
public interface UserRespository extends MongoRepository<User,String> {

}
