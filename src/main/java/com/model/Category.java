package com.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Entity
public class Category implements Serializable {
    private Long id;
    private String name;
    private Collection<Item> items;

    @Id
    @GeneratedValue
    @Column(name = "category_id", nullable = false, insertable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "name", nullable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @OneToMany(mappedBy = "category")
    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }
}
