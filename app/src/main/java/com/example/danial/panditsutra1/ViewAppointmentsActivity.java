package com.example.danial.panditsutra1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.danial.panditsutra1.ProfileClasses.AppointmentClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewAppointmentsActivity extends AppCompatActivity {
    DatabaseReference myRef;
    public static String userName, userPhone,userAdd, userEmail, appDateNtime ;
    public static String panditStrtoSend;

    FirebaseAuth mAuth;
    String appointmentID;

    String currEmail;
    FirebaseUser mUser;
    private FirebaseDatabase database;



    AppointmentClass appointmentClass;
    ArrayList<String> arrayList;
    public ListView listView ;
    ArrayAdapter<String> adapter;
    int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointments);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getInstance().getReference().child("Appointments");
        listView = (ListView) findViewById(R.id.appListView);
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.activity_view_appointment_temp, R.id.userAppointmentTv, arrayList);









        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        currEmail = mUser.getEmail().toString().trim();
        Toast.makeText(getApplicationContext(), currEmail, Toast.LENGTH_SHORT).show();
        appointmentClass = new AppointmentClass();




        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    String temp = ds.child("userEmail").getValue().toString();


                    if (temp.equals(currEmail)) {


                        AppointmentClass appointmentClass = new AppointmentClass();
                        userName = ds.child("panditName").getValue().toString();
                        userEmail = ds.child("panditEmail").getValue().toString();
                        appDateNtime = ds.child("timeAndDate").getValue().toString();
                        userPhone = ds.child("panditPhone").getValue().toString();
                        String[] sm = ds.getRef().toString().split("%20");
                        appointmentID = sm[sm.length - 3] + sm[sm.length - 2] + sm[sm.length-1];


                        arrayList.add(userName + "\n" + userEmail + "\n" + userPhone + "\n"  + appDateNtime + "\n  " );
                    }


                }
                listView.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        }) ;

    }
}
