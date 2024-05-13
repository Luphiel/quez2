package com.example.quez2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class dave extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dave);
        EditText shill = findViewById(R.id.shill);
        Button jm = findViewById(R.id.jm);
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        jm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String write = shill.getText().toString();
                LocalDate date = LocalDate.now();

                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("message", write);
                user.put("date", date.toString());

// Add a new document with a generated ID
                db.collection("userz")
                        .add(user)
                        .addOnSuccessListener(documentReference -> {
                            Intent intent = new Intent(dave.this, Dashburd.class);
                            startActivity(intent);
                        })

                        .addOnFailureListener(e -> {

                        });
            }
        });
    }
}