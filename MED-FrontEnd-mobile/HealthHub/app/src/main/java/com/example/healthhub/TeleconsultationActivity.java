package com.example.healthhub;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class TeleconsultationActivity extends Activity {

    private EditText editTextMessage;
    private LinearLayout messagesContainer;
    private ScrollView scrollView;
    private static final int PICK_MEDIA_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleconsultation); // Assure-toi que ton fichier XML porte bien ce nom

        editTextMessage = findViewById(R.id.editTextMessage);
        messagesContainer = findViewById(R.id.messagesContainer);
        scrollView = findViewById(R.id.scrollViewMessages);
        ImageButton btnSendMessage = findViewById(R.id.btnSendMessage);
        Button btnCallDoctor = findViewById(R.id.btnCallDoctor);
        Button btnUploadMedia = findViewById(R.id.btnUploadMedia);

        btnSendMessage.setOnClickListener(v -> sendMessage());
        btnCallDoctor.setOnClickListener(v -> callDoctor());
        btnUploadMedia.setOnClickListener(v -> openMediaPicker());
    }

    private void sendMessage() {
        String message = editTextMessage.getText().toString().trim();
        if (!message.isEmpty()) {
            TextView textView = new TextView(this);
            textView.setText(message);
            textView.setPadding(8, 8, 8, 8);
            textView.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);
            messagesContainer.addView(textView);
            editTextMessage.setText("");

            scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
        } else {
            Toast.makeText(this, "Le message est vide", Toast.LENGTH_SHORT).show();
        }
    }

    private void callDoctor() {
        Toast.makeText(this, "Appel vocal au docteur...", Toast.LENGTH_SHORT).show();
    }

    private void openMediaPicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("*/*");
        startActivityForResult(Intent.createChooser(intent, "Choisir un média"), PICK_MEDIA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_MEDIA_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedMediaUri = data.getData();
            Toast.makeText(this, "Média sélectionné : " + selectedMediaUri.getLastPathSegment(), Toast.LENGTH_SHORT).show();
        }
    }
}
