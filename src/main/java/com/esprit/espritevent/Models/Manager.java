package com.esprit.espritevent.Models;

public class Manager {

  private long id ;
  private String nom,prenom;
  private String email;
  private String associatedClub;

    public Manager(long id, String nom, String prenom, String email, String associatedClub) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.associatedClub = associatedClub;
    }

    public Manager(int idManager, String nom, String prenom, String email, Object associatedClub) {
    }

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getAssociatedClub() {
        return associatedClub;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Manager() {
    }

    public void setAssociatedClub(String associatedClub) {
        this.associatedClub = associatedClub;
    }
}
