package com.example.model;

public class TypeAliment {

    private int id;
    private String nom;

    public TypeAliment(){
    }

    public TypeAliment(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    
}
