package com.example.healthhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.widget.ImageView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity { // <-- HÉRITAGE IMPORTANT

    CardView cardRdv;
    CardView cardSuiviSante;
    CardView cardrdvs;
    CardView teleconsultation;
    CardView notifications ;
    ImageView ivNotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashbord); // ton fichier XML ici

        cardRdv = findViewById(R.id.cardRdv);
        ivNotif = findViewById(R.id.ivNotif); // lien avec l’icône de profil
        cardSuiviSante = findViewById(R.id.cardSuiviSante);
        cardrdvs = findViewById(R.id.cardrdvs);
        teleconsultation = findViewById(R.id.teleconsultation);
        notifications = findViewById(R.id.notifications);

        cardRdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ChoisirMedecinActivity.class);
                startActivity(intent);
            }
        });
        // Lancer l'activité ProfilPatientActivity quand on clique sur l’icône
        ivNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent(DashboardActivity.this, ProfilPatientActivity.class);
             startActivity(intent);
            }
        });
        // Lier la carte

        // Click listener
        cardSuiviSante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, "Carte Suivi cliquée", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DashboardActivity.this, StatisticsActivity.class);
                startActivity(intent);
            }
        });
        //cardrdvs
        cardrdvs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, "Carte rendez vous cliquée", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DashboardActivity.this, rendezvousactivity.class);
                startActivity(intent);
            }
        });
        teleconsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, "teleconsultations cliquée", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DashboardActivity.this, ConversationsActivity.class);
                startActivity(intent);
            }
        });
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, "teleconsultations cliquée", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DashboardActivity.this, NotificationsActivity.class);
                startActivity(intent);
            }
        });
    }
}
