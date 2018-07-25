package com.example.danial.panditsutra1.PanditsClasses;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.autofill.AutofillValue;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.text.format.DateFormat;

import com.example.danial.panditsutra1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MsgPanditActivity extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    TextView textView, tvDateNTime;
    Button sentBtn;
    String panditEmail;
    String panditType;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    String userId;
    String  userName;
    String userPhone;
    String userEmail;
    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal,hourFinal, minuteFinal;
    String time;
    RatingBar ratingBar;
    Button ratePBtn;
    String pPrevRate;
    float newRate;
    int rateCounter = UserViewPanditsActivity.rateCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_pandit);
    textView = (TextView) findViewById(R.id.tvInfo);
    sentBtn = (Button) findViewById( R.id.sendMsgButton);
    tvDateNTime = (TextView) findViewById(R.id.dateAndTime);

    Bundle bundle = getIntent().getExtras();
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        final FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratePBtn = (Button) findViewById(R.id.ratePandit);

        myRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userName = dataSnapshot.child(userId).child("userName").getValue().toString()  + " " + dataSnapshot.child(userId).child("userSureName").getValue().toString();
                userPhone = (dataSnapshot.child(userId).child("userPhone").getValue().toString() );
                userEmail = (dataSnapshot.child(userId).child("userEmail").getValue().toString() );


                Toast.makeText(getApplicationContext(), userName + " " + userPhone + " " + userEmail, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });


        tvDateNTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MsgPanditActivity.this, (DatePickerDialog.OnDateSetListener) MsgPanditActivity.this,year,month, day);
                datePickerDialog.show();
            }
        });
        ratePBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               newRate = (float) ratingBar.getRating();
                float f1 = Float.parseFloat(pPrevRate);

                if(rateCounter == 0.0){
                    newRate = (float) ratingBar.getRating();

                }else{

                }
                float avg = (newRate + (float) rateCounter) / 2;

                Toast.makeText(getApplicationContext(),"Rating is " + f1, Toast.LENGTH_LONG).show();
            }
        });

//        textView.setText(bundle.getString("PanditEmail"));
        String pnEmail  = bundle.getString("PanditEmail");
        List<String> myList = new ArrayList<String>(Arrays.asList(pnEmail.split(",")));

        textView.setText(myList.get(1).toString());


        panditEmail = myList.get(1).toString();
        panditType = myList.get(3).toString();
        pPrevRate = myList.get(4).toString();

        myRef.child("Pandits").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                    }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        
        sentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dNt = tvDateNTime.getText().toString().trim();
                if(dNt.isEmpty()){

                    tvDateNTime.setError("Email is required");
                    tvDateNTime.requestFocus();

                    return;
                } else {

                    sentMsg();
                }

            }
        });





    }
    private void sentMsg(){

        String email = panditEmail;
        String[] receptients = email.split(",");
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, receptients);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Appointment for Pooja");
        intent.putExtra(Intent.EXTRA_TEXT, "Dear Sir,\n\n" +
                "I would like to take an appointment for " + dayFinal + "." + monthFinal + "." + yearFinal + " at "+
        hourFinal + ":" + minuteFinal + " for " + panditType + " Pooja. \nPlease take my appointment into considaration." +
                "\n\nSincerely,\n" + userName + "\n" + userPhone );
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose Email-Sending Application"));
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month + 1;
        dayFinal = dayOfMonth;

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(MsgPanditActivity.this, MsgPanditActivity.this,
                hour, minute,DateFormat.is24HourFormat(MsgPanditActivity.this));
                timePickerDialog.show();

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourFinal = hourOfDay;
        minuteFinal = minute;
        tvDateNTime.setText(yearFinal + " " + monthFinal + " " + dayFinal + " "
                + hourFinal + " " + minuteFinal);
    }
}
