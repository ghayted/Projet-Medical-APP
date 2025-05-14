package com.example.healthhub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PatientDao {
    private final SQLiteDatabase db;

    public PatientDao(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public boolean ajouterPatient(String nom, String prenom, String email, String motDePasse, String telephone, String adresse, String dateNaissance) {
        ContentValues values = new ContentValues();
        values.put("nom", nom);
        values.put("prenom", prenom);
        values.put("email", email);
        values.put("motDePasse", motDePasse);  // Non sécurisé ! Juste pour le test
        values.put("telephone", telephone);
        values.put("adresse", adresse);
        values.put("dateNaissance", dateNaissance);

        long result = db.insert("Patients", null, values);
        return result != -1;
    }

    public boolean verifierConnexion(String email, String motDePasse) {
        Cursor cursor = db.rawQuery("SELECT * FROM Patients WHERE email=? AND motDePasse=?", new String[]{email, motDePasse});
        boolean resultat = cursor.moveToFirst();
        cursor.close();
        return resultat;
    }
}
