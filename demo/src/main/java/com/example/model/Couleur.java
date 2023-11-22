package com.example.model;


public class Couleur {
    private int id;
    private String nom;
    private String hexadecimal_rvb;
    private int lastIdDeleted;

    public Couleur(String nom, String hexadecimal_rvb) {
        this.nom = nom;
        this.hexadecimal_rvb = hexadecimal_rvb;
    }

    public Couleur(int id, String nom, String hexadecimal_rvb) {
        this.id = id;
        this.nom = nom;
        this.hexadecimal_rvb = hexadecimal_rvb;
    }

    public Couleur() {
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nomCouleur) {
        this.nom = nomCouleur;
    }

    public String getHexadecimal_rvb() {
        return hexadecimal_rvb;
    }

    public void setHexadecimal_rvb(String hexadecimal_rvb) {
        this.hexadecimal_rvb = hexadecimal_rvb;
    }

    public int getId() {
        return id;
    }

    public int setId(int id) {
        this.id = id;
        return this.id;
    }

    public int getlastIdDeleted() {
        return lastIdDeleted;
    }

    public void setLastIdDeleted(int id) {
        this.lastIdDeleted = id;
    }

}
