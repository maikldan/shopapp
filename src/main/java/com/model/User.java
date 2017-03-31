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
@Table(name = "person")
public class User  implements Serializable {
    private Long id;
    private String username;
    private String password;
    private Profile profile;
    private String picturePath;
    @Transient
    private MultipartFile imageMultipart;
    @Transient
    public MultipartFile getImageMultipart() {
        return imageMultipart;
    }
    @Transient
    public void setImageMultipart(MultipartFile imageMultipart) {
        this.imageMultipart = imageMultipart;
    }
    private Collection<Comment> comments;


    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "username", nullable = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Basic
    @Column(name = "password", nullable = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id", nullable = false)
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    @OneToMany(mappedBy = "user")
    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }
    @Basic
    @Column(name = "picturePath", nullable = true)
    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
