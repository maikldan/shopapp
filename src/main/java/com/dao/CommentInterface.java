package com.dao;

import com.model.Comment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Student on 3/27/2017.
 */
public interface CommentInterface extends CrudRepository<Comment, Long> {
}
