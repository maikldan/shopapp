package com.dao;

import com.model.Profile;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Student on 3/27/2017.
 */
public interface ProfileInterface extends CrudRepository<Profile, Long> {

}
