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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView2 = findViewById(R.id.textView2);
        EditText Fullname = findViewById(R.id.Fullname);
        EditText Username = findViewById(R.id.Username);
        EditText Emil = findViewById(R.id.Emil);
        EditText Pasword = findViewById(R.id.Pasword);
        EditText repass = findViewById(R.id.repass);
        Button sing = findViewById(R.id.sing);
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        sing.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String pass = Pasword.getText().toString();
            String re_pass = repass.getText().toString();

            if(pass.equals(re_pass)){
                Map<String, Object> user = new HashMap<>();
                user.put("Username", Username.getText().toString());
                user.put("Email", Emil.getText().toString());
                user.put("Password", Pasword.getText().toString());
                user.put("Repassword", repass.getText().toString());

// Add a new document with a generated ID
                db.collection("userz")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();
                            }
                        });
            }else
                Toast.makeText(MainActivity.this,"Password and Retype password does not Match",Toast.LENGTH_LONG).show();

        }
    });

    textView2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,Login.class);
            startActivity(intent);

        }

    });}}