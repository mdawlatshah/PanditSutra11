package com.example.danial.panditsutra1.RashifalFiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danial.panditsutra1.MainPageFiles.RashiFragment;
import com.example.danial.panditsutra1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RashifalActivity extends AppCompatActivity {

    public String horoSelected = RashiFragment.horoSelected.toString();
    TextView tvHoroSelected, tvHealth, tvPersonal, tvProfessional, tvTravel, tvLuck;
    private FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rashifal);
        tvHealth = (TextView) findViewById(R.id.tvHealth);
        tvHoroSelected = (TextView) findViewById(R.id.horoSelected);
        tvPersonal = (TextView) findViewById(R.id.tvPersonalLife);
        tvProfessional = (TextView) findViewById(R.id.tvProfessionalLife);
        tvTravel = (TextView) findViewById(R.id.tvTravel);
        tvLuck = (TextView) findViewById(R.id.tvLuck);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();

        ref.child("rashifal").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tvHealth.setText(dataSnapshot.child(horoSelected).child("health").getValue().toString());
                tvPersonal.setText(dataSnapshot.child(horoSelected).child("personalLife").getValue().toString());
                tvProfessional.setText(dataSnapshot.child(horoSelected).child("profession").getValue().toString());
                tvTravel.setText(dataSnapshot.child(horoSelected).child("travel").getValue().toString());
                tvLuck.setText(dataSnapshot.child(horoSelected).child("luck").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        tvHoroSelected.setText(horoSelected);




        Toast.makeText(getApplicationContext(), horoSelected, Toast.LENGTH_SHORT).show();



    }
}
