package com.example.healthhub;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "patients.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Création de la table Patients
        String CREATE_PATIENTS_TABLE = "CREATE TABLE Patients (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom TEXT NOT NULL," +
                "prenom TEXT NOT NULL," +
                "email TEXT UNIQUE NOT NULL," +
                "motDePasse TEXT NOT NULL," +
                "telephone TEXT," +
                "adresse TEXT," +
                "dateNaissance TEXT)";
        db.execSQL(CREATE_PATIENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Supprimer et recréer la table
        db.execSQL("DROP TABLE IF EXISTS Patients");
        onCreate(db);
    }
}
