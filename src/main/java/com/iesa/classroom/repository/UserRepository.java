package com.iesa.classroom.repository;

import com.iesa.classroom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameOrEmailOrDni(String username, String email, Long dni);
}
