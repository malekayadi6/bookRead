package com.malek.books.entity;

import java.io.Serializable;

/**
 * Created by malek on 20/07/2016.
 */
public class Categorie implements Serializable {
    private int id;
    private String nom,image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
