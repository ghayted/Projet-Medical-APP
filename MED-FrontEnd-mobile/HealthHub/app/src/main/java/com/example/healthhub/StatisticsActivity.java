package com.example.healthhub;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {

    // Déclare les composants
    TextView textDocteurs, textPatients, textRendezVous;
    EditText inputTension, inputPoids, inputMois, inputAnnee;
    Button btnAfficherGraphique;
    LineChart lineChart;

    // Données statiques
    private static final int DOCTEURS = 5;
    private static final int PATIENTS = 25;
    private static final int RENDEZ_VOUS = 14;
    private static final float TENSION_STATIQUE = 12.5f; // Exemple de tension statique
    private static final float POIDS_STATIQUE = 70f; // Exemple de poids statique
    private static final int MOIS_STATIQUE = 1; // Exemple de mois

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics); // Assure-toi que c'est bien le nom du layout

        // Liaison avec les vues
        textDocteurs = findViewById(R.id.textDocteurs);
        textPatients = findViewById(R.id.textPatients);
        textRendezVous = findViewById(R.id.textRendezVous);

        inputTension = findViewById(R.id.inputTension);
        inputPoids = findViewById(R.id.inputPoids);
        inputMois = findViewById(R.id.inputMois);
        inputAnnee = findViewById(R.id.inputAnnee);

        btnAfficherGraphique = findViewById(R.id.btnAfficherGraphique);
        lineChart = findViewById(R.id.lineChart);

        // Initialisation des données statiques
        textDocteurs.setText(String.valueOf(DOCTEURS));
        textPatients.setText(String.valueOf(PATIENTS));
        textRendezVous.setText(String.valueOf(RENDEZ_VOUS));

        // Remplir les champs avec des données statiques
        inputTension.setText(String.valueOf(TENSION_STATIQUE));
        inputPoids.setText(String.valueOf(POIDS_STATIQUE));
        inputMois.setText(String.valueOf(MOIS_STATIQUE));

        // Exemple de graphique avec des données statiques
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1f, TENSION_STATIQUE));  // Exemple avec mois = 1 et tension fixe

        LineDataSet dataSet = new LineDataSet(entries, "Graphique de Tension");
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate(); // Rafraîchir le graphique pour afficher les nouvelles données

        // Gestionnaire de clics pour afficher un graphique basé sur des données statiques
        btnAfficherGraphique.setOnClickListener(v -> {
            // Tu peux manipuler des valeurs statiques ici ou afficher d'autres données si nécessaire
            Toast.makeText(this, "Graphique mis à jour avec des données statiques.", Toast.LENGTH_SHORT).show();
        });
    }
}
