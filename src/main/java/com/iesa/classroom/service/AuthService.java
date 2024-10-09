package com.iesa.classroom.service;

import com.iesa.classroom.model.User;
import com.iesa.classroom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class AuthService {
    private static final Logger logger = Logger.getLogger(AuthService.class.getName());
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> register(User user) {
        if (userRepository.findByUsernameOrEmailOrDni(user.getUsername(), user.getEmail(), user.getDni()) != null) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        } else {
            //logger.info("Original password: " + user.getPassword());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            //logger.info("Encoded password: " + user.getPassword());
            userRepository.save(user);
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        }
    }

    public ResponseEntity<String> login(User user) {
        User user1 = userRepository.findByUsernameOrEmailOrDni(user.getUsername(), user.getEmail(), user.getDni());
        if (user1 == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } else {
            if (passwordEncoder.matches(user.getPassword(), user1.getPassword())) {
                //logger.info("Password received: " + user.getPassword());
                // logger.info("Password from database: " + user1.getPassword());
                return new ResponseEntity<>("User logged in successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Incorrect password", HttpStatus.UNAUTHORIZED);
            }
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
