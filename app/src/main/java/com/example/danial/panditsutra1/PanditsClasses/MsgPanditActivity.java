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
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.text.format.DateFormat;

import com.example.danial.panditsutra1.ProfileClasses.AppointmentClass;
import com.example.danial.panditsutra1.R;
import com.example.danial.panditsutra1.RatePanditActivity;
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
   public static String panditEmail;
    String panditType, panditName, panditPhone;

    private DatabaseReference appointmentDatabase;

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
   public static float pPrevRate;
    String previousRating;
   public static int rateCounter;
    String rCounter;
    EditText adressEt;
    public String address;
    public  String panditId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_pandit);

        adressEt = (EditText) findViewById(R.id.edAdress);
        address = adressEt.getText().toString();
    textView = (TextView) findViewById(R.id.tvInfo);
    sentBtn = (Button) findViewById( R.id.sendMsgButton);
    tvDateNTime = (TextView) findViewById(R.id.dateAndTime);
        appointmentDatabase = FirebaseDatabase.getInstance().getReference();

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


        String pnEmail  = bundle.getString("PanditEmail");
        List<String> myList = new ArrayList<String>(Arrays.asList(pnEmail.split(",")));

        textView.setText(myList.get(1).toString());

        panditName = myList.get(0).toString().trim();
        panditEmail = myList.get(1).toString().trim();
        panditPhone = myList.get(2).toString().trim();
        panditType = myList.get(3).toString().trim();
        previousRating = myList.get(4).toString().trim();
        rCounter = myList.get(5).toString().trim();
        panditId = myList.get(6).toString().trim();

        Toast.makeText(getApplicationContext(), panditId, Toast.LENGTH_SHORT).show();

        rateCounter = Integer.parseInt(rCounter);

        pPrevRate = Float.parseFloat(previousRating);
        ratingBar.setRating(pPrevRate);
//
//


        sentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    sentDatabase();
                    sentMsg();
            }
        });
        ratePBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MsgPanditActivity.this, RatePanditActivity.class));
            }
        });

    }
    private  void sentDatabase()
    {
        address = adressEt.getText().toString();

        String kk = (yearFinal + "." + monthFinal + "." + dayFinal + " - "
                + hourFinal + ":" + minuteFinal);
        String appStr = userName +" - " + panditName;
        AppointmentClass appointmentClass = new AppointmentClass(userName, userEmail,userPhone, panditName,panditEmail, panditPhone, kk, address );
        appointmentDatabase.child("Appointments").child(appStr).setValue(appointmentClass);
        Toast.makeText(getApplicationContext(),"Successfully added", Toast.LENGTH_LONG).show();
    }
    private void sentMsg(){

        String email = panditEmail;
        String[] receptients = email.split(",");
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, receptients);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Appointment for Pooja");
        intent.putExtra(Intent.EXTRA_TEXT, "Dear Sir,\n\n" +
                "I would like to take an appointment for " + dayFinal + "." + monthFinal + "." + yearFinal + " at "+
        hourFinal + ":" + minuteFinal + " for " + panditType + " Pooja. \nPlease take my appointment into considaration.\n"
                + "My address is: " + address +
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
//
//
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourFinal = hourOfDay;
        minuteFinal = minute;
        tvDateNTime.setText(yearFinal + " " + monthFinal + " " + dayFinal + " "
                + hourFinal + " " + minuteFinal);
    }
}
