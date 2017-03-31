package com.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by Student on 3/27/2017.
 */
@Entity
public class Item implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Category category;
    private Double price;
    @Transient
    private List<MultipartFile> imageMultiparts;
    private Collection<Image> images;
    private Collection<Comment> comments;

    @Id
    @GeneratedValue
    @Column(name = "item_id", nullable = false, insertable = false, updatable = false)
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
    @Basic
    @Column(name = "description", nullable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
    public Collection<Image> getImages() {
        return images;
    }

    public void setImages(Collection<Image> images) {
        this.images = images;
    }
    @OneToMany(mappedBy = "item")
    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }
    @Transient
    public List<MultipartFile> getImageMultiparts() {
        return imageMultiparts;
    }
    @Transient
    public void setImageMultiparts(List<MultipartFile> imageMultiparts) {
        this.imageMultiparts = imageMultiparts;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
