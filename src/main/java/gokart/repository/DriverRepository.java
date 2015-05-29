package gokart.repository;

import gokart.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by florian on 03/04/15.
 */
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByUsername(String username);
}
