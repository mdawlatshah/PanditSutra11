package com.example.danial.panditsutra1.AdminFiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.danial.panditsutra1.ProfileClasses.PanditProfile;
import com.example.danial.panditsutra1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ViewPanditActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private String userID;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pandit);
        mListView = (ListView) findViewById(R.id.listview);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String s = ds.child(userID).child("userType").getValue().toString();
                    if(s.equals("Pandit")) {

                        Toast.makeText(getApplicationContext(), "Checked", Toast.LENGTH_LONG).show();
                        PanditProfile panditProfile = new PanditProfile();
                        panditProfile.setName(ds.child(userID).getValue(PanditProfile.class).getName());
                        panditProfile.setEmail(ds.child(userID).getValue(PanditProfile.class).getEmail());

                        ArrayList<String> array = new ArrayList<>();
                        array.add(panditProfile.getName());
                        array.add(panditProfile.getEmail());

                        ArrayAdapter adapter = new ArrayAdapter(ViewPanditActivity.this, android.R.layout.simple_list_item_1, array);
                        mListView.setAdapter(adapter);
                    }
                    Toast.makeText(getApplicationContext(), "not Checked", Toast.LENGTH_LONG).show();
                }
//
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
