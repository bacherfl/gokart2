package gokart.repository;

import gokart.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by florian on 03/04/15.
 */
public interface RaceRepository extends JpaRepository<Race, Long> {
}
