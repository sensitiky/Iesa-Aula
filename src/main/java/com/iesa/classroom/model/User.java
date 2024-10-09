package com.iesa.classroom.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Long dni;
    @Getter
    public String username;
    public String password;
    @Getter
    public String email;
    public String role;

    public User() {
    }

    public User(Long id, Long dni, String username, String password, String email, String role) {
        this.id = id;
        this.dni = dni;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
