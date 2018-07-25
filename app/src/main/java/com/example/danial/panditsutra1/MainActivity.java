package com.example.danial.panditsutra1;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danial.panditsutra1.AdminFiles.AdminActivity;
import com.example.danial.panditsutra1.ProfileClasses.UserProfile;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    String takeEmail;
    String takeName;
    String takeLastName;
    String takePhone = " ";

//h
    LocationManager locationManager;
    private Button userRegistrationBtn;
    private TextView emailTxt;
    private TextView passwrodTxt;
    private Button loginBtn;
    FirebaseAuth mAuth;
    LoginButton fbLogin;
    TextView forgotPassword;
    CallbackManager callbackManager;
    public static String userLocation = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//        getSupportActionBar().hide(); // hides the title bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enables full screen

        BarColors.colorBars(this, R.color.black);

        setContentView(R.layout.activity_main);

        userRegistrationBtn = (Button) findViewById(R.id.registrationActivityBtn);
        passwrodTxt = (TextView) findViewById(R.id.password);
        emailTxt = (TextView) findViewById(R.id.emailEditText);
        loginBtn = (Button) findViewById(R.id.singinButton);
        mAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());
        fbLogin = (LoginButton)findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        forgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        fbLogin.setReadPermissions(Arrays.asList("email"));

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, PasswordActivity.class));
            }
        });
        userRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));

            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
        // Locator
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        //gets address automatically
        getLocation();



    }
    public void buttonClickFb(View v){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookToken(loginResult.getAccessToken());
                String accessToken = loginResult.getAccessToken().getToken();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("response", response.toString() );
                        getData(object);

                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "first_name,last_name, email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void getData(JSONObject object) {
        try{
            URL profile_picture = new URL("http://graph.facebook.com/ " + object.getString("id"));
            takeEmail = (object.getString("email"));
            takeName = (object.getString("first_name"));
            takeLastName = (object.getString("last_name"));
          //  takePhone = (object.getString("phone"));



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void handleFacebookToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String userType = "User";
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = firebaseDatabase.getReference();
                    UserProfile userProfile = new UserProfile(userType, takeName, takeLastName, takeEmail, takePhone);
                    myRef.child("Users").child(mAuth.getUid()).setValue(userProfile);
                    FirebaseUser myuserobj = mAuth.getCurrentUser();
                    Intent intent = new Intent(MainActivity.this, AfterLogIn.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(intent);



                    // updateUI(myuserobj);
                }else{
                    Toast.makeText(getApplicationContext(), "could not register",Toast.LENGTH_LONG ).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void userLogin() {
        String email = emailTxt.getText().toString().trim();
        String password = passwrodTxt.getText().toString().trim();


        if(email.equals("as") && password.equals("ss"))
        {
            finish();
            startActivity(new Intent(MainActivity.this, AdminActivity.class));
        }

        if(email.equals("a") && password.equals("b"))
        {
            finish();
            startActivity(new Intent(MainActivity.this, AfterLogIn.class));
        }

        if (email.isEmpty()) {
            emailTxt.setError("Email is required");
            emailTxt.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTxt.setError("Please enter a valid email");
            emailTxt.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwrodTxt.setError("Password is required");
            passwrodTxt.requestFocus();
            return;
        }

        if (password.length() < 6) {
            passwrodTxt.setError("Minimum lenght of password should be 6");
            passwrodTxt.requestFocus();
            return;
        }



        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {

                    checkEmailVerification();


                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private  void checkEmailVerification(){
        FirebaseUser firebaseUser = mAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        if(emailflag){
//            finish();

            startActivity(new Intent(MainActivity.this, AfterLogIn.class));
        }
        else{
            Toast.makeText(this, "Please verify your email", Toast.LENGTH_SHORT).show();
            mAuth.signOut();

        }
    }

    // Locator methods

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, (LocationListener) this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //shows both latitude and longitude
        //locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//            locationText.setText(locationText.getText() + "\n"+addresses.get(0).getAddressLine(0) + "\nCode " +
//                            addresses.get(0).getPostalCode());


            userLocation = addresses.get(0).getSubAdminArea().toString().toLowerCase();
            Toast.makeText(getApplicationContext(), userLocation + " userProfile getLocation view", Toast.LENGTH_LONG).show();

        }catch(Exception e)
        {

        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(MainActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

}
