package gokart.controller;

import gokart.model.Race;
import gokart.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by florian on 20/03/15.
 */
@RestController
public class RaceController {

    @Autowired
    RaceRepository raceRepository;

    @RequestMapping(value = "/races", method = RequestMethod.GET)
    public @ResponseBody
    List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    @RequestMapping(value = "/races/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Race getRaceById(@PathVariable("id") long raceId) {
        return raceRepository.findOne(raceId);
    }

    @RequestMapping(value = "/races/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    Race addRace(@RequestBody Race race) {
        Race saved = raceRepository.save(race);
        return saved;
    }

    @RequestMapping(value = "/races/delete/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteRace(@PathVariable("id") long raceId) {
        raceRepository.delete(raceId);
    }

    @RequestMapping(value = "/races/update/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    Race updateRace(@PathVariable("id") long raceId, @RequestBody Race race) {
        Race toSave = raceRepository.findOne(raceId);
        toSave.setLocation(race.getLocation());
        toSave.setDate(race.getDate());
        toSave.setTrackImageUrl(race.getTrackImageUrl());
        raceRepository.save(toSave);
        return race;
    }
}
