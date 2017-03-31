package com.dao;

import com.model.Image;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Student on 3/27/2017.
 */
public interface ImageInterface extends CrudRepository<Image, Long> {
}
