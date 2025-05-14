package com.example.healthhub;

public class Medecin {
    private String nom;
    private String specialite;
    private int imageResId;
    private float rating;
    private String description;
    private String lieuTravail;
    private int experienceAnnees;

    public Medecin(String nom, String specialite, int imageResId,
                   float rating, String description, String lieuTravail, int experienceAnnees) {
        this.nom = nom;
        this.specialite = specialite;
        this.imageResId = imageResId;
        this.rating = rating;
        this.description = description;
        this.lieuTravail = lieuTravail;
        this.experienceAnnees = experienceAnnees;
    }

    public String getNom() {
        return nom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public int getImageResId() {
        return imageResId;
    }

    public float getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getLieuTravail() {
        return lieuTravail;
    }

    public int getExperienceAnnees() {
        return experienceAnnees;
    }
}
