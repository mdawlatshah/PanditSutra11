package com.example.danial.panditsutra1;

import android.content.Intent;
import android.location.LocationListener;
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

import org.json.JSONArray;
import org.json.JSONException;

import java.io.CharArrayWriter;
import java.lang.reflect.Array;
import java.util.Arrays;

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
    String panditEmail = MsgPanditActivity.panditEmail.toString().trim();
    FirebaseDatabase pdata;
    DatabaseReference pRef;
    private FirebaseAuth pAuth;
    PanditProfile newPanditData = new PanditProfile();
    String[] tokens;
    String panditID;
    private DatabaseReference mDatabase;
    float newAvg;



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
        mDatabase = FirebaseDatabase.getInstance().getReference();

        sendRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(rateCounter == 0 || rateCounter == 1){
                    newRate = (float) ratingBar.getRating();
                    String s = String.format("%.2f", newRate);
                    newAvg = Float.parseFloat(s);
                    Toast.makeText(getApplicationContext(),"Rating is " + newRate, Toast.LENGTH_LONG).show();
                }else if (rateCounter > 1){
                    newRate = (float) ratingBar.getRating();

                    avg = ( ((float)prevRate * (float) rateCounter) + (float)newRate) / ((float)rateCounter + 1);
                    String s = String.format("%.2f", avg);
                     newAvg = Float.parseFloat(s);
                    Toast.makeText(getApplicationContext(),"Avg Rating is " + newAvg, Toast.LENGTH_LONG).show();

                }

                        pRef = pdata.getInstance().getReference().child("Pandits");




        pRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                   PanditProfile panditProfile = new PanditProfile();
                    panditProfile = ds.getValue(PanditProfile.class);
                    String s = panditProfile.getEmail().toString().trim();

                    if(s.equals(panditEmail.toString()))
                    {
                        Toast.makeText(getApplicationContext(), "Pandit for Rate found" + panditEmail, Toast.LENGTH_LONG).show();
                        newPanditData = ds.getValue(PanditProfile.class);
                        String[] sm = ds.getRef().toString().split("/");
//                        final String[] tokens = sm.split();
                        panditID = sm[sm.length - 1];
//                        Toast.makeText(getApplicationContext(), sm[sm.length - 1],Toast.LENGTH_SHORT).show();


                        ++rateCounter;
                        mDatabase.child("Pandits").child(panditID).child("rating").setValue(newAvg);
                        mDatabase.child("Pandits").child(panditID).child("rateCounter").setValue(rateCounter);
                        finish();
                        break;





                    }
                }


//                String passc = dataSnapshot.child(pAuth.getUid()).child("email").getValue().toString();
//                if(passc.equals(panditEmail)){
//                    pRef.child(pAuth.getUid()).child("rating").setValue("0");
//                    Toast.makeText(getApplicationContext(), "Value set", Toast.LENGTH_LONG).show();
//                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        }) ;
                Toast.makeText(getApplicationContext(), "out of the loop",Toast.LENGTH_SHORT).show();



            }
        });

    }
}