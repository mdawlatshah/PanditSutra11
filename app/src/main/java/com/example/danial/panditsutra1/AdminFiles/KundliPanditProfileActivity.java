package com.example.danial.panditsutra1.AdminFiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.danial.panditsutra1.ProfileClasses.KundliPandit;
import com.example.danial.panditsutra1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KundliPanditProfileActivity extends AppCompatActivity {

    Spinner typeSpinner;
    ArrayAdapter<CharSequence> payment_adapter, type_adapter;
    String horoType;
    public String horoTypeGet;
    EditText etHealth, etPersonalLife, etProfession, etTravel, etLuck;
    Button sendBtn;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    String health, profess,personal, travel, luck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kundli_pandit_profile);

        sendBtn = (Button) findViewById(R.id.sendHoro);
        etHealth = (EditText) findViewById(R.id.etHealth);
        etPersonalLife = (EditText) findViewById(R.id.etPersonalLife);
        etProfession = (EditText) findViewById(R.id.etProfession);
        etTravel = (EditText) findViewById(R.id.etTravel);
        etLuck = (EditText) findViewById(R.id.etLuck);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


        typeSpinner = (Spinner) findViewById(R.id.horoType);
        type_adapter = ArrayAdapter.createFromResource(this, R.array.horoTypes,android.R.layout.simple_spinner_item);

        typeSpinner.setAdapter(type_adapter);

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                horoType = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), horoType, Toast.LENGTH_LONG).show();
                horoTypeGet = horoType;



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    public void onClick(View view) {
        health = etHealth.getText().toString();
        profess = etProfession.getText().toString();
        personal = etPersonalLife.getText().toString();
        travel = etTravel.getText().toString();
        luck = etLuck.getText().toString();

        KundliPandit kundliPandit = new KundliPandit(health,personal,profess,travel,luck);
        final String userId =mAuth.getUid();
        mDatabase.child("rashifal").child(horoTypeGet).setValue(kundliPandit);
        Toast.makeText(getApplicationContext(),"Successfully added", Toast.LENGTH_LONG).show();

        etHealth.setText("Health");

        etProfession.setText("Profession ");

        etPersonalLife.setText("Personal Life ");

        etTravel.setText("Travel ");

        etLuck.setText("Luck ");




    }
}
