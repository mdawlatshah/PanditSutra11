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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danial.panditsutra1.AdminFiles.ViewPanditActivity;
import com.example.danial.panditsutra1.MainActivity;
import com.example.danial.panditsutra1.MainPageFiles.PanditsFragment;
import com.example.danial.panditsutra1.ProfileActivity;
import com.example.danial.panditsutra1.ProfileClasses.PanditProfile;
import com.example.danial.panditsutra1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class UserViewPanditsActivity extends AppCompatActivity  {



    DatabaseReference ref;
    private FirebaseDatabase database;
    ArrayList<String> arrayList;
    PanditProfile panditProfile = new PanditProfile();
    int i = 0;
    TextView tvPremium1;
    TextView tvPremium2;
    TextView tvPremium3;
    String userLocation = MainActivity.userLocation;
    ListView listView;

    String userName;

    ArrayList<TextView> tvList;
    ArrayList<String> panditEmail;
    ArrayAdapter<String> adapter;
     int rateCounter;
     String panditTypeSelected = PanditsFragment.panditTypeSelected.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_pandits);
        arrayList = new ArrayList<>();
        panditEmail = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();


  //      tvPremium1 = (TextView) findViewById(R.id.pp1Name);
      //  tvPremium2 = (TextView) findViewById(R.id.premiunPandit2);
       // tvPremium3 = (TextView) findViewById(R.id.premiunPandit3);

        listView = (ListView) findViewById(R.id.listView);
        Toast.makeText(getApplicationContext(), panditTypeSelected, Toast.LENGTH_SHORT).show();


        adapter = new ArrayAdapter<String>(this, R.layout.activity_user_view_pandit_temp,R.id.panditInfo ,arrayList);


        ref.child("Pandits").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        Toast.makeText(getApplicationContext(), userLocation, Toast.LENGTH_LONG).show();

                            if (ds.child("location").getValue().toString().toLowerCase().equals(userLocation) && ds.child("paymentType").getValue().toString().equals("Premium")
                            ) {
                                Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();

                                panditProfile = ds.getValue(PanditProfile.class);
                                ++i;
                                arrayList.add("" + i);
                                String pName = panditProfile.getName().toString();
                                String pEmail = panditProfile.getEmail().toString();
                                String pPhone = panditProfile.getPhone().toString();
                                String pType = panditProfile.getType().toString();
                                float pRating = panditProfile.getRating();
                                rateCounter = panditProfile.getRateCounter();


                                arrayList.add(pName + " , " + pEmail + " , " + pPhone + " , " + pType + " , " + pRating + " , " + rateCounter);
                                panditEmail.add(pEmail);

//                            if (i == 1){
//                                String x = String.valueOf(arrayList);
//                                x.split(",");
//                                tvPremium1.setText(x);
//                                arrayList.clear();
//
//                            }
//                            if (i == 2){
//                                tvPremium2.setText(arrayList.toString());
//                                arrayList.clear();
//
//                            }
//                            if (i == 3){
//                                tvPremium3.setText(arrayList.toString());
//                                arrayList.clear();
//
//                            }


//                            tvList.setText(arrayList.toString());


                            }

                        listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        tvPremium1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(UserViewPanditsActivity.this, MsgPanditActivity.class));
//            }
//        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent( UserViewPanditsActivity.this, MsgPanditActivity.class);
                intent.putExtra("PanditEmail", listView.getItemAtPosition(position).toString());
                startActivity(intent) ;

            }
        });


    }




}



