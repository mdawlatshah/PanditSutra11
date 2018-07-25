package com.example.danial.panditsutra1;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.danial.panditsutra1.AdminFiles.AdminActivity;
import com.example.danial.panditsutra1.AdminFiles.KundliPanditProfileActivity;
import com.example.danial.panditsutra1.AdminFiles.PanditProfileActivity;
import com.example.danial.panditsutra1.AdminSponsorFiles.SponsorImageUploads;
import com.example.danial.panditsutra1.AdminSponsorFiles.SponsorsImageAdapter;
import com.example.danial.panditsutra1.ProfileClasses.KundliPandit;
import com.example.danial.panditsutra1.ProfileClasses.PanditProfile;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

//import android.widget.Toolbar;


public class AfterLogIn extends AppCompatActivity implements SponsorsImageAdapter.OnItemClickListener {

    //Sponsor Image Glide
    private RecyclerView mRecyclerView;
    private SponsorsImageAdapter mAdapter;
    private ProgressBar mProgressCircle;
    private FirebaseStorage mStorage; //use it to get a reference to the images in firebase storage
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<SponsorImageUploads> mUploads;

    //Sponsors Image Glide Auto
    final int duration = 14;
    final int pixelsToMove = 30;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    ///

    private FirebaseAuth firebaseAuth;
    private EditText editText;
    private EditText ed2;
    String nummm;
    String toSend;
    private Button logout;
    DatabaseReference myRef;
    private FacebookAuthProvider facebookAuthProvider;
    private FirebaseAuth mAuth;
    String locationText;
    LocationManager locationManager;





    //tabs fragment ...
    TabLayout tabLayout;
    ViewPager viewPagerMain;
    MainTabsPageAdapter mainTabsPageAdapter;
    TabItem tabPandits;
    TabItem tabKundli;
    TabItem tabOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_log_in);


        //Sponsors Image Glide
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mUploads = new ArrayList<>();
        mAdapter = new SponsorsImageAdapter(AfterLogIn.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(AfterLogIn.this);

        mProgressCircle = findViewById(R.id.progress_circle);

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("SponsorImgUploads");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mUploads.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    SponsorImageUploads upload = postSnapshot.getValue(SponsorImageUploads.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }

                mAdapter.notifyDataSetChanged();

                mProgressCircle.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AfterLogIn.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

        ///Sponsors Image Glide Auto
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
                if(lastItem == layoutManager.getItemCount()-1){
                    mHandler.removeCallbacks(SCROLLING_RUNNABLE);
                    Handler postHandler = new Handler();
                    postHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mRecyclerView.setAdapter(null);
                            mRecyclerView.setAdapter(mAdapter);
                            mHandler.postDelayed(SCROLLING_RUNNABLE, 2000);
                        }
                    }, 2000);
                }
            }
        });
        mHandler.postDelayed(SCROLLING_RUNNABLE, 2000);

        ///////


//status bar color
        BarColors.colorBars(this, R.color.status_bar);

//toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
////


//tabs fragment ...
        tabLayout = findViewById(R.id.tabLayout);
        tabPandits = findViewById(R.id.tabPandits);
        tabKundli = findViewById(R.id.tabKundli);
        tabOther = findViewById(R.id.tabOther);
        viewPagerMain = findViewById(R.id.viewPagerMain);


        mainTabsPageAdapter = new MainTabsPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPagerMain.setAdapter(mainTabsPageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerMain.setCurrentItem(tab.getPosition());
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

        viewPagerMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        mAuth = FirebaseAuth.getInstance();


        firebaseAuth = FirebaseAuth.getInstance();
        //logout = (Button) findViewById(R.id.logoutBtn);
        FacebookSdk.sdkInitialize(getApplicationContext());
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseApp.initializeApp(this);
         myRef = firebaseDatabase.getInstance().getReference().child("Users");
        final PanditProfile userProfile = new PanditProfile();
        final KundliPandit kundllProfile = new KundliPandit();
//        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String passc = dataSnapshot.child(mAuth.getUid()).child("userType").getValue().toString();
//                if(passc.equals("Pandit")){
//                    Toast.makeText(getApplicationContext(), "Yess", Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(AfterLogIn.this, PanditProfileActivity.class));
//                } else if(passc.equals("Kundli_Pandit"))
//                {
//                    Toast.makeText(getApplicationContext(), "Kundli Pandit", Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(AfterLogIn.this, KundliPanditProfileActivity.class));
//                }else if(passc.equals("Admin")){
//                    startActivity(new Intent(AfterLogIn.this, AdminActivity.class));
//                    finish();
//                }
//
//                else{
//                 Toast.makeText(getApplicationContext(),"Users checked", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//
//        }) ;



    }


    //Sponsors Image Glide Auto
    private final Runnable SCROLLING_RUNNABLE = new Runnable() {
        @Override
        public void run() {
            mRecyclerView.smoothScrollBy(pixelsToMove, 0);
            mHandler.postDelayed(this, duration);
        }
    };

    @Override
    public void onItemClick(int position) {
    }

    @Override
    public void onDeleteClick(int position) {
    }
    ////


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
