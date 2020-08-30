package com.knoxtech.majhishala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.util.Objects;

public class StartActivity extends AppCompatActivity {

        RecyclerView recyclerView;
        Context context;
        RecyclerView.Adapter recyclerView_Adapter;
        RecyclerView.LayoutManager recyclerViewLayoutManager;
        String[] numbers = {
                "10th",
                "12th",
                "Diploma(Polytechnic)",
        };


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_start);
            Toolbar toolbar=findViewById(R.id.streamBar);
            setSupportActionBar(toolbar);
            Objects.requireNonNull(getSupportActionBar()).setTitle("Choose Stream");
            context = getApplicationContext();
            recyclerView = findViewById(R.id.recycler_view1);
            //Change 2 to your choice because here 2 is the number of Grid layout Columns in each row.
            recyclerViewLayoutManager = new GridLayoutManager(context, 2);
            recyclerView.setLayoutManager(recyclerViewLayoutManager);
            recyclerView_Adapter = new RecyclerViewAdapter(context,numbers);
            recyclerView.setAdapter(recyclerView_Adapter);

        }
    }