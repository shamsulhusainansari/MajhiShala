package com.knoxtech.majhishala;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.knoxtech.majhishala.ui.Adapter.MyItemGroupAdapter;
import com.knoxtech.majhishala.ui.Interface.IFirebaseLoadListener;
import com.knoxtech.majhishala.ui.Model.ItemData;
import com.knoxtech.majhishala.ui.Model.ItemGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dmax.dialog.SpotsDialog;


public class MainActivity extends AppCompatActivity implements IFirebaseLoadListener {

    Dialog dialog;
    IFirebaseLoadListener iFirebaseLoadListener;
    RecyclerView my_recyclerview;
    DatabaseReference myData;
    FirebaseAuth mAuth;
    //private final String TAG = MainActivity.class.getSimpleName();
    //private InterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.maiBar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);
        dialog = new Dialog(this);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String standard = bundle.getString("startId");
        mAuth = FirebaseAuth.getInstance();

        assert standard != null;
        myData = FirebaseDatabase.getInstance().getReference(standard);
        dialog=new SpotsDialog.Builder().setContext(this).build();
        iFirebaseLoadListener = this;

        my_recyclerview = findViewById(R.id.recyclerView);
        my_recyclerview.setHasFixedSize(true);
        my_recyclerview.setLayoutManager(new LinearLayoutManager(this));


        getFirebaseData();

        //interstitialAd = new InterstitialAd(this, "678566876342028_678569516341764");
        // Set listeners for the Interstitial Ad
//        interstitialAd.setAdListener(new InterstitialAdListener() {
//            @Override
//            public void onInterstitialDisplayed(Ad ad) {
//                // Interstitial ad displayed callback
//                Log.e(TAG, "Interstitial ad displayed.");
//            }
//
//            @Override
//            public void onInterstitialDismissed(Ad ad) {
//                // Interstitial dismissed callback
//                Log.e(TAG, "Interstitial ad dismissed.");
//            }
//
//            @Override
//            public void onError(Ad ad, AdError adError) {
//                // Ad error callback
//                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//                // Interstitial ad is loaded and ready to be displayed
//                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
//                // Show the ad
//                interstitialAd.show();
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//                // Ad clicked callback
//                Log.d(TAG, "Interstitial ad clicked!");
//            }
//
//            @Override
//            public void onLoggingImpression(Ad ad) {
//                // Ad impression logged callback
//                Log.d(TAG, "Interstitial ad impression logged!");
//            }
//        });
//        interstitialAd.loadAd();
    }


    private void getFirebaseData() {
        dialog.show();
        myData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<ItemGroup> itemGroups = new ArrayList<>();
                for (DataSnapshot groupSnapshot : snapshot.getChildren()) {
                    ItemGroup itemGroup = new ItemGroup();
                    itemGroup.setHeaderTitle(Objects.requireNonNull(groupSnapshot.child("headerTitle").getValue(true)).toString());
                    GenericTypeIndicator<ArrayList<ItemData>> genericTypeIndicator = new GenericTypeIndicator<ArrayList<ItemData>>() {
                    };
                    itemGroup.setListItem(groupSnapshot.child("listItem").getValue(genericTypeIndicator));
                    itemGroups.add(itemGroup);
                }
                iFirebaseLoadListener.onFirebaseLoadSuccess(itemGroups);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                iFirebaseLoadListener.onFirebaseLoadFailed(error.getMessage());
            }
        });
    }

    @Override
    public void onFirebaseLoadSuccess(List<ItemGroup> itemGroupList) {
        MyItemGroupAdapter adapter = new MyItemGroupAdapter(this, itemGroupList);
        my_recyclerview.setAdapter(adapter);
        dialog.dismiss();
    }

    @Override
    public void onFirebaseLoadFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }



    @Override
    protected void onStart() {
        super.onStart();

        //if the user is not logged in
        //opening the login activity
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, SignInActivity.class));
        }
    }

//    @Override
//    protected void onDestroy() {
//        if (interstitialAd != null) {
//            interstitialAd.destroy();
//        }
//        super.onDestroy();
//    }
}