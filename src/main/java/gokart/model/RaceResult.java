package gokart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by florian on 03/04/15.
 */
@Entity
public class RaceResult {

    @JsonIgnore
    @ManyToOne
    private Driver driver;

    @JsonIgnore
    @ManyToOne
    private Race race;

    @Id
    @GeneratedValue
    private Long id;

    private short raceNr;
    private short ranking;
    private boolean polePosition;
    private double bestLap;

    public RaceResult() {
    }

    public RaceResult(Driver driver, Race race, short raceNr, short ranking, boolean polePosition, double bestLap) {
        this.driver = driver;
        this.race = race;
        this.raceNr = raceNr;
        this.ranking = ranking;
        this.polePosition = polePosition;
        this.bestLap = bestLap;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getRaceNr() {
        return raceNr;
    }

    public void setRaceNr(short raceNr) {
        this.raceNr = raceNr;
    }

    public short getRanking() {
        return ranking;
    }

    public void setRanking(short ranking) {
        this.ranking = ranking;
    }

    public boolean isPolePosition() {
        return polePosition;
    }

    public void setPolePosition(boolean polePosition) {
        this.polePosition = polePosition;
    }

    public double getBestLap() {
        return bestLap;
    }

    public void setBestLap(double bestLap) {
        this.bestLap = bestLap;
    }
}
