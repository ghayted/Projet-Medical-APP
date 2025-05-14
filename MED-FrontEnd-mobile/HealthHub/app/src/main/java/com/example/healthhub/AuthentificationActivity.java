package com.example.healthhub;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AuthentificationActivity extends AppCompatActivity {

    private TextInputEditText editTextNom, editTextPrenom, editTextTelephone, editTextEmail, editTextMotDePasse, editTextConfirmerMotDePasse;
    private TextInputLayout textInputLayoutNom, textInputLayoutPrenom, textInputLayoutTelephone, textInputLayoutEmail, textInputLayoutMotDePasse, textInputLayoutConfirmerMotDePasse;
    private MaterialButton buttonSinscrire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userauth);  // Assurez-vous que votre fichier XML correspond au bon nom

        // Initialiser les vues
        editTextNom = findViewById(R.id.editTextNom);
        editTextPrenom = findViewById(R.id.editTextPrenom);
        editTextTelephone = findViewById(R.id.editTextTelephone);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMotDePasse = findViewById(R.id.editTextMotDePasse);
        editTextConfirmerMotDePasse = findViewById(R.id.editTextConfirmerMotDePasse);
        buttonSinscrire = findViewById(R.id.buttonSinscrire);

        // Bouton d'inscription
        buttonSinscrire.setOnClickListener(v -> {
            String nom = editTextNom.getText().toString().trim();
            String prenom = editTextPrenom.getText().toString().trim();
            String telephone = editTextTelephone.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String motDePasse = editTextMotDePasse.getText().toString().trim();
            String confirmerMotDePasse = editTextConfirmerMotDePasse.getText().toString().trim();

            // Vérifier si tous les champs sont remplis
            if (TextUtils.isEmpty(nom) || TextUtils.isEmpty(prenom) || TextUtils.isEmpty(telephone) || TextUtils.isEmpty(email) || TextUtils.isEmpty(motDePasse) || TextUtils.isEmpty(confirmerMotDePasse)) {
                Toast.makeText(AuthentificationActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            // Vérification si les mots de passe correspondent
            if (!motDePasse.equals(confirmerMotDePasse)) {
                Toast.makeText(AuthentificationActivity.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                return;
            }

            // Si tout est valide, enregistrez l'utilisateur dans la base de données
            // Par exemple, vous pouvez créer un objet Patient et l'enregistrer dans la base de données SQLite

            // Afficher un message de succès
            Toast.makeText(AuthentificationActivity.this, "Inscription réussie !", Toast.LENGTH_SHORT).show();
            finish();  // Ferme l'activité après l'inscription
        });
    }
}
