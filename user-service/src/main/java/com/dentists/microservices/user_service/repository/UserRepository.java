package com.dentists.microservices.user_service.repository;

import com.dentists.microservices.user_service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByAuth0Id(String auth0Id);
}
