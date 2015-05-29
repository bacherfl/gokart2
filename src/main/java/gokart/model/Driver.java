package gokart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by florian on 03/04/15.
 */
@Entity
public class Driver {

    @OneToMany(mappedBy = "driver")
    private Set<RaceResult> raceResults = new HashSet<>();

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;

    @JsonIgnore
    private String username;
    private String password;

    private String imageUrl;

    public Driver(String password, String username, String lastName, String firstName) {
        this.password = password;
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Driver() {
    }

    public Set<RaceResult> getRaceResults() {
        return raceResults;
    }

    public void setRaceResults(Set<RaceResult> raceResults) {
        this.raceResults = raceResults;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
