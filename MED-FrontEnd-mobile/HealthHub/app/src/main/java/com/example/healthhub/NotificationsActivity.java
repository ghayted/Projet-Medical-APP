package com.example.healthhub;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class NotificationsActivity extends AppCompatActivity {

    private LinearLayout layoutNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        layoutNotifications = findViewById(R.id.layoutNotifications);
        Button btnRafraichir = findViewById(R.id.btnRafraichir);


        btnRafraichir.setOnClickListener(v -> {
            layoutNotifications.removeAllViews();

        });
    }


}
