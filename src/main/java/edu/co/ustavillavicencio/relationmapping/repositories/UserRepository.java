package edu.co.ustavillavicencio.relationmapping.repositories;

import edu.co.ustavillavicencio.relationmapping.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByUsername(String username);
}