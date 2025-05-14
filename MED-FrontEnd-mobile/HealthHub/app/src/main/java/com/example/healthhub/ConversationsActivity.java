package com.example.healthhub;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ConversationsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MedecinAdapter adapter;
    private ArrayList<Medecin> listeMedecins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conservationsactivity); // Assure-toi que ce layout existe

        recyclerView = findViewById(R.id.recyclerConversations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Données fictives
        listeMedecins = new ArrayList<>();
        listeMedecins.add(new Medecin("Dr. Amina", "Cardiologue", R.drawable.doctor, 4.5f,
                "Spécialiste en cardiologie depuis 10 ans", "Clinique Al Amal", 12));
        listeMedecins.add(new Medecin("Dr. Yassine", "Dermatologue", R.drawable.doctor, 4.0f,
                "Expert en soins de la peau et traitements esthétiques", "Polyclinique Ibn Sina", 8));
        listeMedecins.add(new Medecin("Dr. Sara", "Pédiatre", R.drawable.doctor, 4.8f,
                "Passionnée par la santé des enfants", "Hôpital Mère-Enfant", 15));

        adapter = new MedecinAdapter(listeMedecins, medecin -> {
            Intent intent = new Intent(this, TeleconsultationActivity.class);
            intent.putExtra("nomMedecin", medecin.getNom());
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }
}
