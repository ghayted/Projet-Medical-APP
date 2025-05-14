package com.example.healthhub;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class rendezvousactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendezvous);

        // Initialisation du RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerRendezVous);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Liste des faux rendez-vous
        List<rendezvous> fauxRendezVous = Arrays.asList(
                new rendezvous("Dr. Amina Lahlou", "Cardiologue", "12 Mai 2025", "14h30"),
                new rendezvous("Dr. Youssef Karim", "Dentiste", "14 Mai 2025", "10h00"),
                new rendezvous("Dr. Fatima Zahra", "Dermatologue", "18 Mai 2025", "16h45")
        );

        // Adapter
        RendezVousAdapter adapter = new RendezVousAdapter(fauxRendezVous);
        recyclerView.setAdapter(adapter);
    }
}
