package com.service;

import com.dao.UserInterface;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by Student on 3/28/2017.
 */
@Component("userDetailsService")
public class CurrentUserDetailsService implements UserDetailsService {
    private final UserInterface userService;

    @Autowired
    public CurrentUserDetailsService(UserInterface userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.findByUsername(name);
        if(user == null){
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", name));
        }
        return new CurrentUser(user);
    }
}
