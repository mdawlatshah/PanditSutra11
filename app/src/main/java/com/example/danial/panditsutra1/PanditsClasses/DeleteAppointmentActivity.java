package com.example.danial.panditsutra1.PanditsClasses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.danial.panditsutra1.ProfileClasses.PanditProfile;
import com.example.danial.panditsutra1.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeleteAppointmentActivity extends AppCompatActivity  {

    String panditStr = PanditProfileActivity.panditStrtoSend.toString();
    Button btn;
    private FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_appointment);

        btn = (Button) findViewById(R.id.delete);
        Bundle bundle = getIntent().getExtras();
        String pnEmail  = bundle.getString("PanditEmail");

        Toast.makeText(getApplicationContext(), panditStr, Toast.LENGTH_SHORT).show();
        String[] sm = panditStr.split(" ");
        final String panditID = sm[sm.length - 1];
        Toast.makeText(getApplicationContext(), panditID, Toast.LENGTH_SHORT).show();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

                myRef = firebaseDatabase.getInstance().getReference().child("Appointments");


            }
            });
    }

}
