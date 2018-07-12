package com.example.danial.panditsutra1;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.danial.panditsutra1.ProfileClasses.PanditProfile;
import com.example.danial.panditsutra1.ProfileClasses.UserProfile;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import android.widget.Toolbar;


public class AfterLogIn extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText editText;
    private EditText ed2;
    String nummm;
    String toSend;
    private Button logout;
    DatabaseReference myRef;
    private FacebookAuthProvider facebookAuthProvider;
    private FirebaseAuth mAuth;

    //tabs fragment ...
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    TabItem tabChats;
    TabItem tabStatus;
    TabItem tabCalls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_log_in);

//status bar color
        BarColors.colorBars(this, R.color.status_bar);

//toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

////



//tabs fragment ...
        tabLayout = findViewById(R.id.tabLayout);
        tabChats = findViewById(R.id.tabChats);
        tabStatus = findViewById(R.id.tabStatus);
        tabCalls = findViewById(R.id.tabCalls);
        viewPager = findViewById(R.id.viewPager);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(AfterLogIn.this,
                            R.color.tab_color3));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(AfterLogIn.this,
                            R.color.tab_color3));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(AfterLogIn.this,
                                R.color.tab_color3));
                    }
                } else if (tab.getPosition() == 2) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(AfterLogIn.this,
                            R.color.tab_color2));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(AfterLogIn.this,
                            R.color.tab_color2));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(AfterLogIn.this,
                                R.color.tab_color2));
                    }
                } else {
                    toolbar.setBackgroundColor(ContextCompat.getColor(AfterLogIn.this,
                            R.color.tab_color1));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(AfterLogIn.this,
                            R.color.tab_color1));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(AfterLogIn.this,
                                R.color.tab_color1));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }


            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

////////////////////////////////////////////////////////////////

        mAuth = FirebaseAuth.getInstance();
//        ed2 = (EditText) findViewById(R.id.textView2);
//        editText = (EditText) findViewById(R.id.phoneToSend);


        firebaseAuth = FirebaseAuth.getInstance();
        //logout = (Button) findViewById(R.id.logoutBtn);
        FacebookSdk.sdkInitialize(getApplicationContext());
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseApp.initializeApp(this);
         myRef = firebaseDatabase.getInstance().getReference().child("Users");
        final PanditProfile userProfile = new PanditProfile();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String passc = dataSnapshot.child(mAuth.getUid()).child("userType").getValue().toString();
                if(passc.equals("Pandit")){
                    Toast.makeText(getApplicationContext(), "Yess", Toast.LENGTH_LONG).show();
                }
                else{
                 Toast.makeText(getApplicationContext(),"Users checked", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        }) ;
//h
        //////////////////////////////////////////////////////////////////////////
//        if(myRef.child("Users").child(firebaseAuth.getUid()).child("userPhone").equals(" "))
//        {
//            String numm = editText.getText().toString();
//            myRef.child("Users").child(firebaseAuth.getUid()).child("userPhone").setValue(numm);
//            Toast.makeText(getApplicationContext(), "Please insert your phone Number", Toast.LENGTH_LONG).show();
//        }else {
//            editText.setText(" No Number");
//        }
//////////////////////////////////////////////////////////////////////////////////





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ((item.getItemId())) {
            case R.id.logoutMenu: {
                firebaseAuth.signOut();
                LoginManager.getInstance().logOut();
                finish();
                startActivity(new Intent(AfterLogIn.this, MainActivity.class));
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(AfterLogIn.this, ProfileActivity.class));
        }

        return super.onOptionsItemSelected(item);

    }


}
