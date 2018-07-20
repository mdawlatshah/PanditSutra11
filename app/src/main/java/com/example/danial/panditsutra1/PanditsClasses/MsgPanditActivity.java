package com.example.danial.panditsutra1.PanditsClasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danial.panditsutra1.ProfileClasses.PanditProfile;
import com.example.danial.panditsutra1.R;

public class MsgPanditActivity extends AppCompatActivity {

    TextView textView;
    String  userName = UserViewPanditsActivity.userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_pandit);
    textView = (TextView) findViewById(R.id.tvInfo);

        String s = getIntent().getExtras().getString("panditName");
        Toast.makeText(getApplicationContext(), userName, Toast.LENGTH_LONG).show();



        textView.setText(s);


    }
}
