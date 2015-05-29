package gokart.controller;

import gokart.model.RaceResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by florian on 20/03/15.
 */
@RestController
public class RaceResultsController {

    @RequestMapping(value = "/RaceResults", method = RequestMethod.GET)
    public @ResponseBody
    List<RaceResult> getAllRaceResults() {
        return null;
    }

    @RequestMapping(value = "/RaceResults/{id}", method = RequestMethod.GET)
    public @ResponseBody
    RaceResult getRaceResultById(@PathVariable("id") long RaceResultId) {
        return null;
    }

    @RequestMapping(value = "/RaceResults/race/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<RaceResult> getRaceRaceResults(@PathVariable("id") long raceId) {
        return null;
    }

    @RequestMapping(value = "/RaceResults/driver/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<RaceResult> getDriverRaceResults(@PathVariable("id") long driverId) {
        return null;
    }

    @RequestMapping(value = "/RaceResults/add/{raceId}/{driverId}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    RaceResult addRaceResult(@RequestBody RaceResult RaceResult) {
        return RaceResult;
    }

    @RequestMapping(value = "/RaceResults/delete/{id}", method = RequestMethod.DELETE)
    public void deleteRaceResult(@PathVariable("id") long RaceResultId) {

    }

    @RequestMapping(value = "/RaceResults/update/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    RaceResult updateRaceResult(@PathVariable("id") long RaceResultId, @RequestBody RaceResult RaceResult) {
        return RaceResult;
    }

}
