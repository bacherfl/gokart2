package gokart.service;

import gokart.model.Driver;
import gokart.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static java.util.Arrays.asList;

/**
 * Created by florian on 01/05/15.
 */
@Service
public class DriverDetailsService implements UserDetailsService{

    @Autowired
    DriverRepository accountRepository;

    public DriverDetailsService() {}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Driver> d = accountRepository.findByUsername(username);
        if (d.isPresent()) {
            return new User(d.get().getUsername(), d.get().getPassword(), true, true, true, true,
                    getGrantedAuthorities(username));
        }
        else return null;
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(String username) {
        Collection<? extends GrantedAuthority> authorities;
        if (username.equals("bacherfl@gmail.com")) {
            authorities = asList(() -> "ROLE_ADMIN", () -> "ROLE_BASIC");
        } else {
            authorities = asList(() -> "ROLE_BASIC");
        }
        return authorities;
    }
}
