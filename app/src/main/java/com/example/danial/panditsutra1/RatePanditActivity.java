package com.example.danial.panditsutra1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.danial.panditsutra1.PanditsClasses.MsgPanditActivity;
import com.example.danial.panditsutra1.PanditsClasses.UserViewPanditsActivity;
import com.example.danial.panditsutra1.ProfileClasses.PanditProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RatePanditActivity extends AppCompatActivity {

    float prevRate = MsgPanditActivity.pPrevRate;
    float newRate;
    RatingBar ratingBar;
    Button sendRate;
    float avg;
    private FirebaseAuth mAuth;
    DatabaseReference ref;
    private FirebaseDatabase database;
    int rateCounter = MsgPanditActivity.rateCounter;
    String panditEmail = MsgPanditActivity.panditEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_pandit);
        ratingBar = (RatingBar) findViewById(R.id.rbRatePandit);
        newRate = ratingBar.getRating();
        sendRate = (Button) findViewById(R.id.sendRating);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        mAuth = FirebaseAuth.getInstance();
        sendRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(rateCounter == 0 || rateCounter == 1){
                    newRate = (float) ratingBar.getRating();
                    Toast.makeText(getApplicationContext(),"Rating is " + newRate, Toast.LENGTH_LONG).show();
                }else if (rateCounter > 1){
                    newRate = (float) ratingBar.getRating();

                    avg = ( ((float)prevRate * (float) rateCounter) + (float)newRate) / ((float)rateCounter + 1);
                    Toast.makeText(getApplicationContext(),"Avg Rating is " + avg, Toast.LENGTH_LONG).show();

                }
                ref.child("Pandits").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                            Toast.makeText(getApplicationContext(), "Hello form  the other activity", Toast.LENGTH_LONG).show();

                            if (ds.child("email").getValue().toString().equals(panditEmail)) {






                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }
        });

    }
}