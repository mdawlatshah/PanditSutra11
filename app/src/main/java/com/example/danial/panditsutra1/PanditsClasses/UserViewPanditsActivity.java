package com.example.danial.panditsutra1.PanditsClasses;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danial.panditsutra1.AfterLogIn;
import com.example.danial.panditsutra1.ProfileClasses.PanditProfile;
import com.example.danial.panditsutra1.R;
import com.facebook.login.LoginManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class UserViewPanditsActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;
    String locationText;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    private FirebaseDatabase database;
    ArrayList<String> arrayList;
    PanditProfile panditProfile = new PanditProfile();
    int i = 0;
    TextView tv1;
    public static  String userName = " T";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_pandits);
        arrayList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();

        tv1 = (TextView) findViewById(R.id.premiunPandit1);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        //gets address automatically
        getLocation();

        ref.child("Pandits").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if(ds.child("location").getValue().toString().equals("nnnn") && ds.child("paymentType").getValue().toString().equals("Premium")){
                        Toast.makeText(getApplicationContext(),"Hello", Toast.LENGTH_LONG).show();
                        panditProfile = ds.getValue(PanditProfile.class);
                        ++i;
                        arrayList.add("" + i);
                        String pName = panditProfile.getName().toString();
                        String pEmail = panditProfile.getEmail().toString();

                        arrayList.add(pName);
                        arrayList.add(pEmail);
                        userName = pName;
                        Bundle b = new Bundle();
                        b.putString("panditName", pName);
                        Intent pass = new Intent(getApplication(), MsgPanditActivity.class);
                        pass.putExtras(b);
                       // startActivity(pass);

                    }
                    tv1.setText(arrayList.toString());

//                    for(int j = 0; j < arrayList.size(); i++)
//                    {
//                        tv1.setText(arrayList.get(j));
//                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserViewPanditsActivity.this, MsgPanditActivity.class));
            }
        });

    }




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


            locationText =  addresses.get(0).getSubAdminArea();

            Toast.makeText(getApplicationContext(), locationText, Toast.LENGTH_LONG).show();

        }catch(Exception e)
        {

        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(UserViewPanditsActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

}



