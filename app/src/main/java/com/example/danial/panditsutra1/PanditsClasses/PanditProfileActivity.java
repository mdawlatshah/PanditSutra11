package com.example.danial.panditsutra1.PanditsClasses;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.danial.panditsutra1.AdminFiles.AdminActivity;
import com.example.danial.panditsutra1.AdminFiles.KundliPanditProfileActivity;
import com.example.danial.panditsutra1.AfterLogIn;
import com.example.danial.panditsutra1.ProfileClasses.AppointmentClass;
import com.example.danial.panditsutra1.ProfileClasses.PanditProfile;
import com.example.danial.panditsutra1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PanditProfileActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imageView;
    Button chngProfileImgBtn;
    Button addPanditGalleryBtn;
    DatabaseReference myRef;
    private FirebaseStorage mStorage;
    private StorageReference storageRef;
    public static String userName, userPhone,userAdd, userEmail, appDateNtime ;
    public static String panditStrtoSend;

    FirebaseAuth mAuth;
    String appointmentID;

     String currEmail;
    FirebaseUser mUser;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    private DatabaseReference mDatabase;
    AppointmentClass appointmentClass;
    ArrayList<String> arrayList;
    private ListView listView;
    ArrayAdapter<String> adapter;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pandit_profile);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getInstance().getReference().child("Appointments");
        listView = (ListView)findViewById(R.id.lvAppshow);
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.activity_pandit_profile_temp, R.id.appointmentsTv , arrayList);

        imageView = (ImageView) findViewById(R.id.profileImage);
        chngProfileImgBtn = (Button) findViewById(R.id.changePanditProfileImgBtn);
        addPanditGalleryBtn = (Button) findViewById(R.id.galleryBtn);


        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        mStorage = FirebaseStorage.getInstance();

        storageRef = mStorage.getReference();

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        currEmail = mUser.getEmail().toString().trim();
        appointmentClass = new AppointmentClass();


        final String userId = mUser.getUid();
        storageRef.child("PanditProfileImages/" + userId).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(PanditProfileActivity.this).load(uri)
                        .fit()
                        .centerInside()
                        .into(imageView);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    String temp = ds.child("panditEmail").getValue().toString();


                    if (temp.equals(currEmail)) {


                        AppointmentClass appointmentClass = new AppointmentClass();
                        userName = ds.child("userName").getValue().toString();
                        userEmail = ds.child("userEmail").getValue().toString();
                        userAdd = ds.child("address").getValue().toString();
                        appDateNtime = ds.child("timeAndDate").getValue().toString();
                        userPhone = ds.child("userPhone").getValue().toString();
                        String[] sm = ds.getRef().toString().split("%20");
                        appointmentID = sm[sm.length - 3] + sm[sm.length - 2] + sm[sm.length-1];


                        arrayList.add(userName + "\n" + userEmail + "\n" + userPhone + "\n" +userAdd + "\n" + appDateNtime + "\n  " + appointmentID);
                    }


                }
                listView.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        }) ;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent( PanditProfileActivity.this, DeleteAppointmentActivity.class);
                panditStrtoSend = listView.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(), listView.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                intent.putExtra("PanditProfile", listView.getItemAtPosition(position).toString());
                startActivity(intent) ;

            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.changePanditProfileImgBtn:
                startActivity(new Intent(PanditProfileActivity.this, AddPanditProfileImageActivity.class));
                break;
            case R.id.galleryBtn:
                startActivity(new Intent(PanditProfileActivity.this, AddPanditGalleryActivity.class));
                break;

        }
    }
}

