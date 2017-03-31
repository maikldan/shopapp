package com.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Student on 3/27/2017.
 */
@Entity
public class Image implements Serializable {
    private Long id;
    private String path;
    private Item item;


    @Id
    @GeneratedValue
    @Column(name = "image_id", nullable = false, insertable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "path", nullable = true)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "item_id", nullable = false)
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
