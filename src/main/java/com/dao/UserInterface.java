package com.dao;

import com.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Student on 3/27/2017.
 */
public interface UserInterface extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
