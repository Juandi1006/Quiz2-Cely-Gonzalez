package edu.co.ustavillavicencio.relationmapping.repositories;

import edu.co.ustavillavicencio.relationmapping.entities.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KitchenRepository extends JpaRepository<Kitchen, Long> {
    List<Kitchen> findByChefId(Long chefId);
}
