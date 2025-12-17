package com.Webshop.ClassAssignment.ItVitae.Webshop.repositories;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
