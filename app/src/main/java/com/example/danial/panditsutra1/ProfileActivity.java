package com.example.danial.panditsutra1;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danial.panditsutra1.ProfileClasses.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity implements LocationListener {

    private DatabaseReference myRef;
    private TextView profileName;
    private  TextView profileEmail;
    private TextView profilePhone;

    private FirebaseAuth mAuth;
     private FirebaseDatabase mFirebaseDatabase;
     private FirebaseAuth.AuthStateListener mAuthListener;
    String userId;

    //google location
    //Button getLocationBtn;
    TextView locationText;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BarColors.colorBars(this, R.color.status_bar2);

        setContentView(R.layout.activity_profile);

        profileName = (TextView) findViewById(R.id.tvName);
        profileEmail = (TextView) findViewById(R.id.tvEmail);
        profilePhone = (TextView) findViewById(R.id.tvPhoneNum);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();

//h
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




        //google location
        //getLocationBtn = (Button)findViewById(R.id.getLocationBtn);
        //locationText = (TextView)findViewById(R.id.locationText);
        locationText = (TextView)findViewById(R.id.locationText);



        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        //gets address automatically
        getLocation();

        //gets address by clicking on button
//        getLocationBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getLocation();
//            }
//        });
        ////////////////////////////////////////////



    }


    //google location

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //shows both latitude and longitude
        //locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//            locationText.setText(locationText.getText() + "\n"+addresses.get(0).getAddressLine(0) + "\nCode " +
//                            addresses.get(0).getPostalCode());

            locationText.setText(
                    addresses.get(0).getSubThoroughfare()
                            +", " + addresses.get(0).getThoroughfare()
                            +"\n" + addresses.get(0).getSubLocality()
                            +", " + addresses.get(0).getSubAdminArea()
                            +"\n" + addresses.get(0).getAdminArea()
                            +", " + addresses.get(0).getPostalCode()
                            +"\n" + addresses.get(0).getCountryName());

        }catch(Exception e)
        {

        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(ProfileActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }


    ////////////////////////////////////////////////////////////////////



    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds: dataSnapshot.getChildren()){
            String s = ds.child(userId).child("userType").getValue().toString();
            if(s.equals("User")){

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
