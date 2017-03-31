package com.service;

import com.model.Profile;
import com.model.User;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Created by Student on 3/28/2017.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User{

    private User user;

    public CurrentUser(User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getProfile().toString()));
        this.user = user;
    }
    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public Profile getRole() {
        return user.getProfile();
    }
}