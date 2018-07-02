package com.example.danial.panditsutra1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class AfterLogIn extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button logout;
    private FacebookAuthProvider facebookAuthProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_log_in);

        firebaseAuth = FirebaseAuth.getInstance();
        logout = (Button) findViewById(R.id.logoutBtn);
        FacebookSdk.sdkInitialize(getApplicationContext());


//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                firebaseAuth.signOut();
//                LoginManager.getInstance().logOut();
//                finish();
//                startActivity(new Intent(AfterLogIn.this, MainActivity.class));
//            }
//        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
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
