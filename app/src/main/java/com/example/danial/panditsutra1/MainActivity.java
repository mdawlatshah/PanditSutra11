package com.example.danial.panditsutra1;

import android.content.Intent;
import android.support.annotation.NonNull;
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

public class MainActivity extends AppCompatActivity {

    String takeEmail;
    String takeName;
    String takeLastName;
    String takePhone;



    private Button guestBtn;
    private TextView emailTxt;
    private TextView passwrodTxt;
    private Button loginBtn;
    FirebaseAuth mAuth;
    LoginButton fbLogin;
    TextView forgotPassword;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//        getSupportActionBar().hide(); // hides the title bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enables full screen

        BarColors.colorBars(this, R.color.black);

        setContentView(R.layout.activity_main);

        guestBtn = (Button) findViewById(R.id.registrationActivityBtn);
        passwrodTxt = (TextView) findViewById(R.id.password);
        emailTxt = (TextView) findViewById(R.id.emailEditText);
        loginBtn = (Button) findViewById(R.id.singinButton);
        mAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());
        fbLogin = (LoginButton)findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        forgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        fbLogin.setReadPermissions(Arrays.asList("email"));
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              //  validate(emailTxt.getText().toString(),passwrod.getText().toString() );
//            }
//        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    finish();
                startActivity(new Intent(MainActivity.this, PasswordActivity.class));
            }
        });
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
            takePhone = (object.getString("phone"));



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


//    public void buttonClickFb(View v){
//        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//
//                handleFacebookToken(loginResult.getAccessToken());
//            }
//
//            @Override
//            public void onCancel() {
//                Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }
    private void handleFacebookToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = firebaseDatabase.getReference();
                    UserProfile userProfile = new UserProfile(takeName, takeLastName, takeEmail, takePhone);
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
//                    finish();
                    checkEmailVerification();

//                    Intent intent = new Intent(MainActivity.this, AfterLogIn.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void  onClick(View v){


        if(v.getId() == R.id.registrationActivityBtn){
//            finish();
            startActivity(new Intent(this, RegistrationActivity.class));

        }
        else if(v.getId() == R.id.singinButton)
        {
            //Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();

            userLogin();
        }
       /* switch ((v.getId()))
        {
            case R.id.guestBtn:
                startActivity(new Intent(this, RegistrationActivity.class));
                break;

            case R.id.singinButton:
                Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
                userLogin();
                break;
        }*/
//        protected  void onStart()
//        {
//            super.onStart();
////        if(mAuth.getCurrentUser() != null){
////            finish();;
////            startActivity(new Intent(this, ProfileActivity.class));
////        }
//
//        }
//        if(v.getId() == R.id.guestBtn)
//        {
//            Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
//
//        }
    }
    private  void checkEmailVerification(){
        FirebaseUser firebaseUser = mAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        if(emailflag){
//            finish();

            startActivity(new Intent(MainActivity.this, AfterLogIn.class));
        }else{
            Toast.makeText(this, "Please verify your email", Toast.LENGTH_SHORT).show();
            mAuth.signOut();

        }
    }
//    public void validate (String name, String password){
//        if((name.equals("0552")) && (password.equals("12345")))
//        {
//            Intent intent = new Intent(MainActivity.this, AfterLogIn.class);
//            startActivity(intent);
//        }else if(!(name.equals("0552")))
//        {
//            Context context = getApplicationContext();
//            Toast.makeText(context, "Invalid Phone Number", Toast.LENGTH_LONG).show();
//        }else if(!(password.equals("12345")))
//        {
//            Context context = getApplicationContext();
//            Toast.makeText(context, "Invalid Password", Toast.LENGTH_LONG).show();
//        }else
//        {
//            Context context = getApplicationContext();
//            Toast.makeText(context, "Invalid Phone Number and Password", Toast.LENGTH_LONG).show();
//        }
//    }

}
