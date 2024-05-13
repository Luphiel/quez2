package com.example.quez2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView sgnup = findViewById(R.id.sgnup);
        EditText userNameEditText = findViewById(R.id.use);
        EditText passwordEditText = findViewById(R.id.word);
        Button loginButton = findViewById(R.id.butun);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Query Firestore to check if user exists
                db.collection("userz")
                        .whereEqualTo("Email", userName)
                        .whereEqualTo("Password", password)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    // User found, login successful
                                    Toast.makeText(Login.this, "Login successful.",
                                            Toast.LENGTH_SHORT).show();
                                    // Proceed to dashboard activity
                                    Intent intent = new Intent(Login.this,Dashburd.class);
                                    startActivity(intent);
                                    finish();
                                    // Proceed to next activity or do something else
                                } else {
                                    // User not found or incorrect password
                                    Toast.makeText(Login.this, "Incorrect username or password.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Error fetching user document
                                Toast.makeText(Login.this, "Error: " + e.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        sgnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
