package com.example.healthhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DashboardActivityDoctor extends AppCompatActivity {
    ImageView ivNotif;

    CardView cardrdvs;
    CardView teleconsultation;
    CardView notifications ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboardoctor); // ton fichier XML ici
        ivNotif = findViewById(R.id.ivNotif); // lien avec l’icône de profil

        cardrdvs = findViewById(R.id.cardrdvs);
        teleconsultation = findViewById(R.id.teleconsultation);
        notifications = findViewById(R.id.notifications);


        // Lancer l'activité ProfilPatientActivity quand on clique sur l’icône
        ivNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivityDoctor.this, ProfilDoctorActivity.class);
                startActivity(intent);
            }
        });
        // Lier la carte


        //cardrdvs
        cardrdvs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivityDoctor.this, "Carte patients vous cliquée", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DashboardActivityDoctor.this, rendezvousactivity.class);
                startActivity(intent);
            }
        });
        teleconsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivityDoctor.this, "teleconsultations cliquée", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DashboardActivityDoctor.this, ConversationsActivity.class);
                startActivity(intent);
            }
        });
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivityDoctor.this, "teleconsultations cliquée", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DashboardActivityDoctor.this, NotificationsActivityDoctor.class);
                startActivity(intent);
            }
        });
    }
}
