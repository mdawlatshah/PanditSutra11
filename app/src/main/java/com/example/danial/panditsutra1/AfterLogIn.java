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
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.danial.panditsutra1.AdminFiles.KundliPanditProfileActivity;
import com.example.danial.panditsutra1.AdminFiles.PanditProfileActivity;
import com.example.danial.panditsutra1.ProfileClasses.KundliPandit;
import com.example.danial.panditsutra1.AdminFiles.KundliPanditProfileActivity;
import com.example.danial.panditsutra1.AdminFiles.PanditProfileActivity;
import com.example.danial.panditsutra1.ProfileClasses.KundliPandit;
import com.example.danial.panditsutra1.MainPageFiles.ViewPagerAdapter;
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

import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

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
    String locationText;
    LocationManager locationManager;


    //tabs fragment ...
    TabLayout tabLayout;
    ViewPager viewPagerMain;
    MainTabsPageAdapter mainTabsPageAdapter;
    TabItem tabPandits;
    TabItem tabKundli;
    TabItem tabOther;

//sponsors image slide
    LinearLayout sponsorsLayout;
    ViewPager viewPagerSponsors;
    LinearLayout sliderDotsPanel;
    private int dotsCount;
    private ImageView[] dots;


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
////

////sponsors Layout
        sponsorsLayout = findViewById(R.id.SliderDots);
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
                    sponsorsLayout.setBackgroundColor(ContextCompat.getColor(AfterLogIn.this,
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
                    sponsorsLayout.setBackgroundColor(ContextCompat.getColor(AfterLogIn.this,
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
                    sponsorsLayout.setBackgroundColor(ContextCompat.getColor(AfterLogIn.this,
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


// sponsors image slider - viewpageradapter
        viewPagerSponsors = findViewById(R.id.viewPagerSponsors);

        sliderDotsPanel = (LinearLayout) findViewById(R.id.SliderDots);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPagerSponsors.setAdapter(viewPagerAdapter);

        dotsCount = viewPagerAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotsPanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPagerSponsors.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                            R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 500,1000);

        mAuth = FirebaseAuth.getInstance();



        firebaseAuth = FirebaseAuth.getInstance();
        //logout = (Button) findViewById(R.id.logoutBtn);
        FacebookSdk.sdkInitialize(getApplicationContext());
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseApp.initializeApp(this);
         myRef = firebaseDatabase.getInstance().getReference().child("Users");
        final PanditProfile userProfile = new PanditProfile();
        final KundliPandit kundllProfile = new KundliPandit();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String passc = dataSnapshot.child(mAuth.getUid()).child("userType").getValue().toString();
                if(passc.equals("Pandit")){
                    Toast.makeText(getApplicationContext(), "Yess", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AfterLogIn.this, PanditProfileActivity.class));
                } if(passc.equals("Kundli_Pandit"))
                {
                    Toast.makeText(getApplicationContext(), "Kundli Pandit", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AfterLogIn.this, KundliPanditProfileActivity.class));
                }

                else{
                 Toast.makeText(getApplicationContext(),"Users checked", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        }) ;



    }

//    sponsors image auto slide using viewPagerAdapter
    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            AfterLogIn.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPagerSponsors.getCurrentItem() == 0) {
                        viewPagerSponsors.setCurrentItem(1);
                    } else if (viewPagerSponsors.getCurrentItem() == 1) {
                        viewPagerSponsors.setCurrentItem(2);
                    } else {
                        viewPagerSponsors.setCurrentItem(0);
                    }
                }
            });

        }
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
