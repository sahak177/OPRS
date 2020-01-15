package com.example.oprs.repository;

import com.example.oprs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByEmail(String username);

}
