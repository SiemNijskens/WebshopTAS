package com.Webshop.ClassAssignment.ItVitae.Webshop.repositories;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
