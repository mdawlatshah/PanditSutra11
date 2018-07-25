package com.example.danial.panditsutra1.AdminFiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.danial.panditsutra1.ProfileClasses.PanditProfile;
import com.example.danial.panditsutra1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewPanditActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference ref;
    private String userID;
    private ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    PanditProfile panditProfile;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pandit);
        panditProfile = new PanditProfile();
        listView = (ListView)findViewById(R.id.listView);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference();



        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.view_pandit_temp, R.id.panditName , arrayList);

        ref.child("Pandits").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
//                    String s = ds.child("Pandits").child(userID).child("userType").getValue().toString();
//                    if(s.equals("Pandit")) {
//
//                        Toast.makeText(getApplicationContext(), "Checked", Toast.LENGTH_LONG).show();
//                        PanditProfile panditProfile = new PanditProfile();
//                        panditProfile.setName(ds.child(userID).getValue(PanditProfile.class).getName());
//                        panditProfile.setEmail(ds.child(userID).getValue(PanditProfile.class).getEmail());
//
//                        ArrayList<String> array = new ArrayList<>();
//                        array.add(panditProfile.getName());
//                        array.add(panditProfile.getEmail());
//
//                        ArrayAdapter adapter = new ArrayAdapter(ViewPanditActivity.this, android.R.layout.simple_list_item_1, array);
//                        mListView.setAdapter(adapter);
//                    }
//
                        panditProfile = ds.getValue(PanditProfile.class);
                        ++i;

                        arrayList.add("" + i);
                        arrayList.add(panditProfile.getName().toString());
                        arrayList.add(panditProfile.getEmail().toString());

                }
                listView.setAdapter(adapter);

//
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
