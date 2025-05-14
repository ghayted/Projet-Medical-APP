package com.example.healthhub;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ProfilMedecinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_medecin);

        // Récupération des vues
        ImageView imageMedecin = findViewById(R.id.imageMedecin);
        TextView nom = findViewById(R.id.tvNomMedecin);
        TextView specialite = findViewById(R.id.tvSpecialite);
        TextView adresse = findViewById(R.id.tvAdresse);
        Button btnRdv = findViewById(R.id.btnPrendreRdv);

        // Récupération des données envoyées depuis l’intent
        Intent intent = getIntent();
        nom.setText(intent.getStringExtra("nom"));
        specialite.setText("Spécialité : " + intent.getStringExtra("specialite"));
        adresse.setText("Lieu : " + intent.getStringExtra("lieuTravail"));
        imageMedecin.setImageResource(intent.getIntExtra("image", R.drawable.doctor));

        // Exemple d’action sur le bouton
        btnRdv.setOnClickListener(v -> {
            // Créer un nouvel Intent pour passer les informations du médecin
            Intent intentRdv = new Intent(ProfilMedecinActivity.this, PrendreRendezVousActivity.class);
            intentRdv.putExtra("nom", nom.getText().toString());
            intentRdv.putExtra("specialite", specialite.getText().toString().replace("Spécialité : ", ""));
            intentRdv.putExtra("adresse", adresse.getText().toString().replace("Lieu : ", ""));
            intentRdv.putExtra("image", intent.getIntExtra("image", R.drawable.doctor)); // réutilisation de l'intent initial

            // Lancer l'activité de prise de rendez-vous
            startActivity(intentRdv);
        });
    }
}
