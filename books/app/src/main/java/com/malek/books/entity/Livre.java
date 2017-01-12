package com.malek.books.entity;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by malek on 18/07/2016.
 */
public class Livre implements Serializable{
    private int id;
    private String nom;
    private String langue;
    private String disc;
    private String auteur;
    private String nbrPage;
    private String img;
    private int idCat;
    private int idUser;

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

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getNbrPage() {
        return nbrPage;
    }

    public void setNbrPage(String nbrPage) {
        this.nbrPage = nbrPage;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
