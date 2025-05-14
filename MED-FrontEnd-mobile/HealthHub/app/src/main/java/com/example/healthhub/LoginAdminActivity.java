package com.example.healthhub;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginAdminActivity extends AppCompatActivity {

    private TextInputEditText editTextEmail, editTextPassword;
    private MaterialButton buttonLogin;
    private TextView textCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin); // ton layout XML avec ScrollView

        // Lier les composants
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textCreateAccount = findViewById(R.id.textCreateAccount);

        // Bouton de connexion
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginAdminActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent;  // 🔹 Déclaration unique

                if (email.equals("admin@example.com") && password.equals("123")) {
                    Toast.makeText(LoginAdminActivity.this, "Connexion réussie en tant qu'administrateur", Toast.LENGTH_SHORT).show();

                    intent = new Intent(LoginAdminActivity.this, DashboardActivity.class);

                } else if (email.equals("doctor@example.com") && password.equals("doctor123")) {
                    Toast.makeText(LoginAdminActivity.this, "Connexion réussie en tant que médecin", Toast.LENGTH_SHORT).show();

                    // Coordonnées fictives du médecin
                    String doctorName = "Dr. Ahmed Benali";
                    String doctorSpecialty = "Cardiologue";
                    String doctorPhone = "+212 600 123 456";
                    String doctorEmail = email;

                    intent = new Intent(LoginAdminActivity.this, DashboardActivityDoctor.class);
                    intent.putExtra("doctorName", doctorName);
                    intent.putExtra("doctorSpecialty", doctorSpecialty);
                    intent.putExtra("doctorPhone", doctorPhone);
                    intent.putExtra("doctorEmail", doctorEmail);

                } else {
                    Toast.makeText(LoginAdminActivity.this, "Identifiants incorrects", Toast.LENGTH_SHORT).show();
                    return;
                }

                startActivity(intent);  // 🔹 Lancement centralisé
                finish();
            }
        });


        // Lien "Créer un compte"
        textCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aller vers l'activité d'inscription
                Intent intent = new Intent(LoginAdminActivity.this, AuthentificationActivity.class);
                startActivity(intent);
            }
        });
    }
}
