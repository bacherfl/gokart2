package gokart.controller;

import gokart.model.Driver;
import gokart.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

/**
 * Created by florian on 17/01/15.
 */
@RestController
public class DriverController {

    @Autowired
    DriverRepository driverRepository;

    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    public @ResponseBody
    List<Driver> drivers() {
        List<Driver> drivers = driverRepository.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        return drivers;
    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Driver getDriverById(@PathVariable("id") long driverId) {
        return driverRepository.findOne(driverId);
    }

    @RequestMapping(value = "/drivers/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    Driver addDriver(@RequestBody Driver driver) {
        Driver d = driverRepository.save(driver);
        return d;
    }

    @RequestMapping(value = "/drivers/delete/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteDriver(@PathVariable("id") long driverId) {
        driverRepository.delete(driverRepository.findOne(driverId));
    }

    @RequestMapping(value = "/drivers/{id}/addImage", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addImage(@PathVariable("id") long driverId, @RequestParam("file") MultipartFile img) {
        if (!img.isEmpty()) {
            try {
                Driver d = driverRepository.findOne(driverId);
                byte[] bytes = img.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File("driver" + d.getId() + ".png"))
                );
                stream.write(bytes);
                stream.close();
                d.setImageUrl("driver" + d.getId() + ".png");
                driverRepository.save(d);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @RequestMapping(value = "/drivers/{id}/image", method = RequestMethod.GET)
    public byte[] getImage(@PathVariable("id") long driverId) {
        Driver d = driverRepository.findOne(driverId);
        if (d == null)
            return null;

        if (Files.exists(Paths.get(d.getImageUrl()))) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(d.getImageUrl()));
                return bytes;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/drivers/update/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    Driver updateDriver(@PathVariable("id") long driverId, @RequestBody Driver driver) {
        Driver d = driverRepository.findOne(driver.getId());
        d.setFirstName(driver.getFirstName());
        d.setLastName(driver.getLastName());
        d.setPassword(driver.getPassword());
        d.setUsername(driver.getUsername());
        return driver;
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

}
