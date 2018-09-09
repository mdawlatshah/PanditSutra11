package com.example.danial.panditsutra1.AdminFiles;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.danial.panditsutra1.BarColors;
import com.example.danial.panditsutra1.MainActivity;
import com.example.danial.panditsutra1.ProfileClasses.PanditProfile;
import com.example.danial.panditsutra1.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class AddPanditsActivity extends AppCompatActivity {

    Spinner paymentSpinner, typeSpinner;
    //  ArrayList<String> paymentArray, typeArray;
    ArrayAdapter<CharSequence> payment_adapter, type_adapter;

    private DatabaseReference mDatabase;
    private EditText etName, etEmail,etPhone, etPassword, etLocation;
    private Button addPandit;
    private FirebaseAuth mAuth;
    public String pName, pEmail, pPhone, pPassword, pLocation, pType, pPaymentType, pPayment;
    String userType = "Pandit";
    String panditType = " ";
    float rate = (float) 0.0;
    int counter = 0;

    private CheckBox cbVastu, cbAstro, cbJiyoti,cbShstri, cbBiyant, cbMahant;

    ArrayList<String> panditTypeArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_pandits);

        BarColors.colorBars(this, R.color.status_bar);
        cbVastu = (CheckBox) findViewById(R.id.checkBoxVastu);
        cbAstro = (CheckBox) findViewById(R.id.checkBoxAstrologal);
        cbJiyoti = (CheckBox) findViewById(R.id.checkBoxJyotish);
        cbShstri = (CheckBox) findViewById(R.id.checkBoxShastri);
        cbBiyant = (CheckBox) findViewById(R.id.checkBoxByias);
        cbMahant = (CheckBox) findViewById(R.id.checkBoxMahant);


        etName = (EditText) findViewById(R.id.pdName);
        etEmail = (EditText) findViewById(R.id.pdEmail);
        etPhone = (EditText) findViewById(R.id.pdPhone);
        etPassword = (EditText) findViewById(R.id.pdPassword);
        etLocation = (EditText) findViewById(R.id.pdLocation);

        addPandit = (Button) findViewById(R.id.addKPandit);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();




//        typeSpinner = (Spinner) findViewById(R.id.paditsType);
//        paymentSpinner = (Spinner) findViewById(R.id.paymentSpinner);
//
//        payment_adapter = ArrayAdapter.createFromResource(this, R.array.paymentTypes,android.R.layout.simple_spinner_item);
//        type_adapter = ArrayAdapter.createFromResource(this, R.array.types,android.R.layout.simple_spinner_item);
//
//        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        payment_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        typeSpinner.setAdapter(type_adapter);
//        paymentSpinner.setAdapter(payment_adapter);
//
//        paymentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//
//                pPayment = parent.getItemAtPosition(position).toString();
//                    Toast.makeText(getBaseContext(), pPayment,Toast.LENGTH_LONG).show();
//
//
//
//
//                    typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            pType = parent.getItemAtPosition(position).toString();
//                            Toast.makeText(getApplicationContext(), pType, Toast.LENGTH_LONG).show();
//                            panditType = pType;
//
//
//
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    });
//
//
//            }
//
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });




    }
    public void checkBoxMethod (View v){
        if(cbVastu.isChecked())
        {
            //panditTypeArray.add("vastu");
            Toast.makeText(getBaseContext(), "clicked" ,Toast.LENGTH_LONG).show();
        }
//        if(cbMahant.isChecked())
//        {
//            panditTypeArray.add("mahant");
//            Toast.makeText(getBaseContext(), panditTypeArray.get(1), Toast.LENGTH_LONG).show();
//        }
//        if(cbBiyant.isChecked())
//        {
//            panditTypeArray.add("biyas");
//            Toast.makeText(getBaseContext(), panditTypeArray.get(2), Toast.LENGTH_LONG).show();
//        }
//        if(cbJiyoti.isChecked())
//        {
//            panditTypeArray.add("jyotish");
//            Toast.makeText(getBaseContext(), panditTypeArray.get(3), Toast.LENGTH_LONG).show();
//        }
//        if(cbAstro.isChecked())
//        {
//            panditTypeArray.add("astrologal");
//            Toast.makeText(getBaseContext(), panditTypeArray.get(4), Toast.LENGTH_LONG).show();
//        }
//        if(cbShstri.isChecked())
//        {
//            panditTypeArray.add("Shastri");
//            Toast.makeText(getBaseContext(), panditTypeArray.get(5), Toast.LENGTH_LONG).show();
//        }

    }



    public void onClick(View view) {
        pName = etName.getText().toString();
        pEmail = etEmail.getText().toString();
        pPhone = etPhone.getText().toString().trim();
        pPassword = etPassword.getText().toString();
        pLocation = etLocation.getText().toString();


        mAuth.createUserWithEmailAndPassword(pEmail,pPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    sendEmailVerification();

                    Toast.makeText(getApplicationContext(), " Successfully Added", Toast.LENGTH_LONG).show();
                    PanditProfile panditProfile = new PanditProfile(userType, pName,pEmail,pPhone,pLocation,pPayment,panditType, rate, counter);
                    final String userId =mAuth.getUid();
                    mDatabase.child("Pandits").child(userId).setValue(panditProfile);

                }else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "User is already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
//        etName.setText(pType);
//        etLocation.setText(pPayment);
    }
    private void sendEmailVerification (){
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(AddPanditsActivity.this,"Successfully Registered, Verification mail is sent", Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                        finish();
                        startActivity(new Intent(AddPanditsActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(AddPanditsActivity.this,"Verification mail is failed to be sent", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }
}

