package com.dao;

import com.model.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Student on 3/27/2017.
 */
public interface CategoryInterface extends CrudRepository<Category, Long> {
}
