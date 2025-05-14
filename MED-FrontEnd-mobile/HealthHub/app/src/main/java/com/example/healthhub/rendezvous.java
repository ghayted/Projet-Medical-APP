package com.example.healthhub;

public class rendezvous {
    private String nomMedecin;
    private String specialite;
    private String date;
    private String heure;

    public rendezvous(String nomMedecin, String specialite, String date, String heure) {
        this.nomMedecin = nomMedecin;
        this.specialite = specialite;
        this.date = date;
        this.heure = heure;
    }

    public String getNomMedecin() {
        return nomMedecin;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }
}
