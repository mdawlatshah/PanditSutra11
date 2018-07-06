package com.example.danial.panditsutra1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.danial.panditsutra1.ProfileClasses.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private DatabaseReference myRef;
    private TextView profileName;
    private  TextView profileEmail;
    private TextView profilePhone;

    private FirebaseAuth mAuth;
     private FirebaseDatabase mFirebaseDatabase;
     private FirebaseAuth.AuthStateListener mAuthListener;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = (TextView) findViewById(R.id.tvName);
        profileEmail = (TextView) findViewById(R.id.tvEmail);
        profilePhone = (TextView) findViewById(R.id.tvPhoneNum);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();

//   firebaseDatabase = FirebaseDatabase.getInstance();



//        DatabaseReference databaseReference = firebaseDatabase.getReference().child("user").child(firebaseAuth.getUid());
// not important        mDatabase.child("users").child(userId).setValue();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds: dataSnapshot.getChildren()){
            UserProfile uInfo = new UserProfile();
            uInfo.setUserName(ds.child(userId).getValue(UserProfile.class).getUserName());
            uInfo.setUserEmail(ds.child(userId).getValue(UserProfile.class).getUserEmail());
            uInfo.setUserPhone(ds.child(userId).getValue(UserProfile.class).getUserPhone());
            uInfo.setUserSureName(ds.child(userId).getValue(UserProfile.class).getUserSureName());

            profileName.setText(uInfo.getUserName() + " " + uInfo.getUserSureName());
            profilePhone.setText(uInfo.getUserPhone());
            profileEmail.setText(uInfo.getUserEmail());

        }

    }

    @Override
    public  void onStart(){
       super.onStart();
       mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public  void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
