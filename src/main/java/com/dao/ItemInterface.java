package com.dao;

import com.model.Item;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Student on 3/27/2017.
 */
public interface ItemInterface extends CrudRepository<Item, Long> {

}
