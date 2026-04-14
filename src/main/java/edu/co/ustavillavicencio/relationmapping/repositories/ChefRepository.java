package edu.co.ustavillavicencio.relationmapping.repositories;

import edu.co.ustavillavicencio.relationmapping.entities.Chef;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChefRepository extends JpaRepository<Chef, Long> {

    @EntityGraph(attributePaths = {"user", "kitchens"})
    List<Chef> findAll();

    @EntityGraph(attributePaths = {"user", "kitchens"})
    Optional<Chef> findById(Long id);

    @EntityGraph(attributePaths = {"user", "kitchens"})
    Optional<Chef> findByUserUsername(String username);
}
