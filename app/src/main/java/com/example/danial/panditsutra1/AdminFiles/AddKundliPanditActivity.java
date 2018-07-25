package com.example.danial.panditsutra1.AdminFiles;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.danial.panditsutra1.BarColors;
import com.example.danial.panditsutra1.MainActivity;
import com.example.danial.panditsutra1.ProfileClasses.KundliPandit;
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

public class AddKundliPanditActivity extends AppCompatActivity {

    //  ArrayList<String> paymentArray, typeArray;
    ArrayAdapter<CharSequence> payment_adapter, type_adapter;

    private DatabaseReference mDatabase;
    private EditText etName, etEmail,etPhone, etPassword, etLocation;
    private Button addPandit;
    private FirebaseAuth mAuth;
    public String kName, kEmail, kPhone, kPassword;
    String userType = "Kundli_Pandit";
    String horoType = " ";

     String health = " ";
     String travel = " ";
     String professional = " ";
     String personal = " ";
     String luck = " ";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kundli_pandit);

        BarColors.colorBars(this, R.color.status_bar);

        etName = (EditText) findViewById(R.id.kpName);
        etEmail = (EditText) findViewById(R.id.kpEmail);
        etPhone = (EditText) findViewById(R.id.kpPhone);
        etPassword = (EditText) findViewById(R.id.kpPassword);


        addPandit = (Button) findViewById(R.id.addKPandit);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


    }


    public void onClick(View view) {
        kName = etName.getText().toString();
        kEmail = etEmail.getText().toString();
        kPhone = etPhone.getText().toString().trim();
        kPassword = etPassword.getText().toString();


        mAuth.createUserWithEmailAndPassword(kEmail, kPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    sendEmailVerification();

                    Toast.makeText(getApplicationContext(), " Successfully Added", Toast.LENGTH_LONG).show();
                   // KundliPandit kundliPandit = new KundliPandit(userType, horoType, kName, kEmail, kPhone);
                    KundliPandit kundliPandit = new KundliPandit(userType, horoType, kName, kEmail, kPhone, health, travel, professional, personal, luck);
                    final String userId =mAuth.getUid();
                    mDatabase.child("kundliPandit").child(userId).setValue(kundliPandit);

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
                        Toast.makeText(AddKundliPanditActivity.this,"Successfully Registered, Verification mail is sent", Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                        finish();
                        startActivity(new Intent(AddKundliPanditActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(AddKundliPanditActivity.this,"Verification mail is failed to be sent", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }
}
