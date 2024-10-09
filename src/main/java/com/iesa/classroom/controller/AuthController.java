package com.iesa.classroom.controller;

import com.iesa.classroom.model.User;
import com.iesa.classroom.repository.UserRepository;
import com.iesa.classroom.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return authService.login(user);
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return authService.getAllUsers();
    }
}
