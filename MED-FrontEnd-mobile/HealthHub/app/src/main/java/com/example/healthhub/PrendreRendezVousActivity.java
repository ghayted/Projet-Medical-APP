package com.example.healthhub;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.Locale;

public class PrendreRendezVousActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prendre_rendez_vous);

        // Récupération des vues
        EditText etDateRdv = findViewById(R.id.etDateRdv);
        EditText etHeureRdv = findViewById(R.id.etHeureRdv);

        TextView tvNom = findViewById(R.id.tvNomMedecin);
        TextView tvSpecialite = findViewById(R.id.tvSpecialiteMedecin);
        TextView tvAdresse = findViewById(R.id.tvAdresseMedecin);
        // L’image n’existe pas dans le layout XML actuel, donc tu dois l’ajouter ou commenter cette ligne :


        // Récupération des données envoyées
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nom = extras.getString("nom", "Nom inconnu");
            String specialite = extras.getString("specialite", "Spécialité inconnue");
            String adresse = extras.getString("adresse", "Adresse inconnue");
            int image = extras.getInt("image", R.drawable.doctor);

            // Affichage des données
            tvNom.setText(nom);
            tvSpecialite.setText("Spécialité : " + specialite);
            tvAdresse.setText("Lieu : " + adresse);
        }

        // Sélection de la date
        etDateRdv.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
                        String formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", selectedDayOfMonth, selectedMonth + 1, selectedYear);
                        etDateRdv.setText(formattedDate);
                    },
                    year, month, day
            ).show();
        });

        // Sélection de l’heure
        etHeureRdv.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int heure = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            new TimePickerDialog(
                    this,
                    (view, hourOfDay, minute1) -> {
                        String formattedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute1);
                        etHeureRdv.setText(formattedTime);
                    },
                    heure, minute, true
            ).show();
        });
    }
}
