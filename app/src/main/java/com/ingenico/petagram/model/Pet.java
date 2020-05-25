package com.ingenico.petagram.model;

import java.io.Serializable;

public class Pet implements Serializable {

    private int id;
    private String name;
    private int rating;
    private int photo;
    private String photoUrl;
    private String caption;
    private String id_str;

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    public Pet(String name, int rating, int photo) {
        this.name = name;
        this.rating = rating;
        this.photo = photo;
    }

    public Pet(int rating, int photo) {
        this.rating = rating;
        this.photo = photo;
    }

    public Pet() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}