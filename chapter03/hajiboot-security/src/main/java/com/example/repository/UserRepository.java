package com.example.repository;

import com.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yoshimasa Tanabe
 */
public interface UserRepository extends JpaRepository<User, String> {}
