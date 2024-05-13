package com.example.quez2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Dashburd extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<Coffee_model> userArraylist;
    adaptor myAdapter;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashburd);


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        userArraylist = new ArrayList<Coffee_model>();
        myAdapter = new adaptor(Dashburd.this, userArraylist);

        recyclerView.setAdapter(myAdapter);
        FloatingActionButton rae = findViewById(R.id.rae);
        rae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashburd.this, dave.class);
                startActivity(intent);
            }
        });
        db.collection("userz")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Dashburd", "listen failed", error);
                            return;
                        }

                        if (value != null) {
                            userArraylist.clear(); // Clear the list before adding new data

                            for (DocumentSnapshot document : value.getDocuments()) {
                                try {
                                    // Retrieve data from Firestore document
                                    String write = document.getString("date");
                                    String date = document.getString("message");

                                    // Create a Coffee_model object and add it to the list
                                    Coffee_model user = new Coffee_model(write, date);
                                    userArraylist.add(user);
                                } catch (Exception e) {
                                    Log.e("dashboard", "Error parsing document.", e);
                                }
                            }

                            // Notify the adapter that the data set has changed
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}