package com.example.danial.panditsutra1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.danial.panditsutra1.ProfileClasses.UserProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    //this is for git
    // hell
    private DatabaseReference mDatabase;
    private EditText firstName, email,sureName, phone,  password, repeatPass;
    private Button signUpBtn;
    private FirebaseAuth mAuth;
    String fName, sName, phn, eml, psd,rtPsd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//        getSupportActionBar().hide(); // hides the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enables full screen
        BarColors.colorBars(this, R.color.status_bar);

        setContentView(R.layout.activity_registration);

        setUpUIVeiws();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.signUpBtn).setOnClickListener(this);
//        signUpBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            String user_email = email.getText().toString().trim();
//            String user_name = firstName.getText().toString().trim();
//
//            mAuth.createUserWithEmailAndPassword(user_name, user_email ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if(task.isSuccessful()) {
//                        finish();
//                        Context context = getApplicationContext();
//                        Toast.makeText(context, "Signing up is done", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));}
////                    }else
////                    {
////                        Context context = getApplicationContext();
////                        Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show();
////                    }
//                }
//            });
//            }
//        });
    }

    private void registerUser(){

         fName = firstName.getText().toString();
         sName = sureName.getText().toString();
         phn = phone.getText().toString();
         eml = email.getText().toString().trim();
         psd = password.getText().toString().trim();
         rtPsd = repeatPass.getText().toString();

        if((fName.isEmpty()) || (sName.isEmpty()) || (phn.isEmpty()) ||  (eml.isEmpty()) ||(psd.equals("")) || (rtPsd.isEmpty()) ) {
            Context context = getApplicationContext();
            Toast.makeText(context, "Please insert all information!", Toast.LENGTH_SHORT).show();
            return;
        }if(!psd.equals(rtPsd))
        {
            Context context = getApplicationContext();
            Toast.makeText(context, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phn.length() < 10 || phn.length() > 14){
            Context context = getApplicationContext();
            Toast.makeText(context, "Please put a correct phone number!", Toast.LENGTH_SHORT).show();
            return;
        } if (!Patterns.EMAIL_ADDRESS.matcher(eml).matches()) {
            email.setError("Please enter a valid email");
            email.requestFocus();
            return;
        }if (psd.length() < 6) {
            password.setError("Minimum lenght of password should be 6");
            password.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(eml, psd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
//                    finish();
//                    startActivity(new Intent(RegistrationActivity.this, AfterLogIn.class));
                    sendEmailVerification();
                    sendUserData();
                   Toast.makeText(RegistrationActivity.this, " Registration successfully done.", Toast.LENGTH_SHORT).show();
                   finish();
                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class));

                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }
    private void setUpUIVeiws(){
        firstName = (EditText) findViewById(R.id.firstName);
        sureName = (EditText) findViewById(R.id.sureName);
        phone = (EditText) findViewById(R.id.emailEditText);
        password = (EditText) findViewById(R.id.password);
        repeatPass = (EditText) findViewById(R.id.repeatPassword);
        email = (EditText) findViewById(R.id.email);

        signUpBtn = (Button) findViewById(R.id.signUpBtn);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signUpBtn:
                registerUser();
                break;

//            case R.id.textViewLogin:
//                finish();
//                startActivity(new Intent(this, MainActivity.class));
//                break;
        }

    }
    private void sendEmailVerification (){
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful()){
                   Toast.makeText(RegistrationActivity.this,"Successfully Registered, Verification mail is sent", Toast.LENGTH_SHORT).show();
                   mAuth.signOut();
                   finish();
                   startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
               }else{
                   Toast.makeText(RegistrationActivity.this,"Verification mail is failed to be sent", Toast.LENGTH_SHORT).show();
               }

                }
            });

        }
    }

    private  void sendUserData(){
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = firebaseDatabase.getReference();
//        UserProfile userProfile = new UserProfile(fName, sName, eml, phn);
        //myRef.setValue(userProfile);
        UserProfile userProfile = new UserProfile(fName, sName, eml, phn);
        final String userId =mAuth.getUid();
        mDatabase.child("Users").child(userId).setValue(userProfile);

    }
}
