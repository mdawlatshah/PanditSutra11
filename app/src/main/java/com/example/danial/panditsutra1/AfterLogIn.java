package com.example.danial.panditsutra1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//import android.widget.Toolbar;
import android.support.v7.widget.Toolbar;

import com.example.danial.panditsutra1.ProfileClasses.UserProfile;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AfterLogIn extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText editText;
    private EditText ed2;
    String nummm;
    String toSend;
    private Button logout;
    DatabaseReference myRef;
    private FacebookAuthProvider facebookAuthProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BarColors.colorBars(this, R.color.status_bar);

        setContentView(R.layout.activity_after_log_in);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ed2 = (EditText) findViewById(R.id.textView2);
        editText = (EditText) findViewById(R.id.phoneToSend);
        firebaseAuth = FirebaseAuth.getInstance();
        logout = (Button) findViewById(R.id.logoutBtn);
        FacebookSdk.sdkInitialize(getApplicationContext());
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
         myRef = firebaseDatabase.getInstance().getReference();
        final UserProfile userProfile = new UserProfile();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getValue(UserProfile.class);

                if(userProfile.userPhone == null)
                {
                    Toast.makeText(getApplicationContext(),"put num", Toast.LENGTH_LONG).show();
                    toSend = editText.getText().toString();
                    //Toast.makeText(getApplicationContext(),"put num", Toast.LENGTH_LONG).show();
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

        switch ((item.getItemId())){
            case R.id.logoutMenu : {
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
