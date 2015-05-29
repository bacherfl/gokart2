package gokart.repository;

import gokart.model.RaceResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * Created by florian on 03/04/15.
 */
public interface RaceResultRepository extends JpaRepository<RaceResult, Long> {
    Collection<RaceResult> findByRaceLocation(String location);
    Collection<RaceResult> findByDriverUsername(String username);
}
