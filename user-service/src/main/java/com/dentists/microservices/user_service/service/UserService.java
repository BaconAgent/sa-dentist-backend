package com.dentists.microservices.user_service.service;

import com.dentists.microservices.user_service.model.User;
import com.dentists.microservices.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    // Retrieve a profile by Auth0 userId
    public Optional<User> getUser(String auth0Id) {
        return userRepository.findByAuth0Id(auth0Id);
    }

    // Create or update user profile data
    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }
}
