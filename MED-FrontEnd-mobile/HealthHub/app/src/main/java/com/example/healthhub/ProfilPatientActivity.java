package com.example.healthhub;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfilPatientActivity extends AppCompatActivity {

    private TextView tvNomPatient, tvAgePatient, tvEmailPatient, tvTelephonePatient, tvLocalisationPatient;
    private Button btnModifierProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_patient);

        // Initialiser les vues
        tvNomPatient = findViewById(R.id.tvName); // anciennement tvNomPatient
        tvAgePatient = findViewById(R.id.tvAge); // anciennement tvAgePatient
        tvEmailPatient = findViewById(R.id.tvEmail); // anciennement tvEmailPatient
        tvTelephonePatient = findViewById(R.id.tvPhone); // anciennement tvTelephonePatient
        tvLocalisationPatient = findViewById(R.id.tvLocation); // anciennement tvLocalisationPatient
        btnModifierProfil = findViewById(R.id.btnModifier); // anciennement btnModifierProfil


        // Exemple : récupération du patient connecté
        Patient patientConnecte = getPatientConnecte(); // Supposons que cette méthode récupère le patient connecté

        // Affichage des informations du patient
        tvNomPatient.setText("Nom: " + patientConnecte.getNom() + " " + patientConnecte.getPrenom());
        tvAgePatient.setText("Âge: " + patientConnecte.getAge() + " ans");
        tvEmailPatient.setText("Email: " + patientConnecte.getEmail());
        tvTelephonePatient.setText("Téléphone: " + patientConnecte.getTelephone());
        tvLocalisationPatient.setText("Localisation: " + patientConnecte.getAdresse());

        // Gérer le clic sur le bouton "Modifier le profil"
        btnModifierProfil.setOnClickListener(v -> {
            // Naviguer vers une autre activité pour modifier les informations du patient
            // Intent intent = new Intent(ProfilPatientActivity.this, ModifierProfilActivity.class);
            // startActivity(intent);
        });
    }

    // Méthode simulée pour récupérer un patient connecté
    private Patient getPatientConnecte() {
        // Récupérer ici les données réelles du patient, par exemple depuis une base de données

        return new Patient(
                "Dupont", "Jean",  // nom, prénom
                "jean.dupont@mail.com",  // email
                "monMotDePasse123",      // mot de passe
                "0123456789",            // téléphone
                "Paris, France",         // adresse
                20             // date de naissance (format ISO recommandé)
        );


    }
}
