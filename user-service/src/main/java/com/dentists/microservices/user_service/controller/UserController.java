package com.dentists.microservices.user_service.controller;

import com.dentists.microservices.user_service.model.User;
import com.dentists.microservices.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/test")
    public User getUser(@AuthenticationPrincipal Jwt jwt) {
        log.info("getUser");
        String auth0Id = jwt.getSubject();
        log.info("auth0Id: {}", auth0Id);
        return userService.getUser(auth0Id)
                .orElseGet(() -> {
                    // Optionally, create a new profile if none exists
                    User user = new User();
                    user.setAuth0Id(auth0Id);
                    // You may choose to fill in additional default values or trigger user onboarding logic
                    return userService.saveOrUpdateUser(user);
                });
    }
    @PostMapping()
    public User saveOrUpdateUser(@RequestBody User user) {
        return userService.saveOrUpdateUser(user);
    }
}
