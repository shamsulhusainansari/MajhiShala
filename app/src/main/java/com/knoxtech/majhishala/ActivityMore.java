package com.knoxtech.majhishala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.knoxtech.majhishala.ui.Adapter.PostAdapter;
import com.knoxtech.majhishala.ui.Model.ItemData;

import java.util.ArrayList;
import java.util.Objects;

public class ActivityMore extends AppCompatActivity {


    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<ItemData> list;
    PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);


        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String std = bundle.getString("std");
        String sub=bundle.getString("sub");

        Toolbar toolbar=findViewById(R.id.moreToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(sub);


        recyclerView = findViewById(R.id.moreRecycler);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));


        reference = FirebaseDatabase.getInstance().getReference().child(std+sub);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    ItemData p = dataSnapshot1.getValue(ItemData.class);
                    list.add(p);
                }
                adapter = new PostAdapter(ActivityMore.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ActivityMore.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}