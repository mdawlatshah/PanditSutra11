package com.example.danial.panditsutra1.AdminFiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.danial.panditsutra1.AdminSponsorFiles.AddSponsorPhotosActivity;
import com.example.danial.panditsutra1.R;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addBtn;
    private Button addKundli;
    private  Button viewPandits;

    //add sponsor images btn
    private Button addSponsorImageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toast.makeText(getApplicationContext(), "Hello to Admin View", Toast.LENGTH_LONG).show();

        addBtn = (Button) findViewById(R.id.addPandits);
        addKundli = (Button) findViewById(R.id.addKundliPandit);
        viewPandits = (Button) findViewById(R.id.viewPandit);

        addSponsorImageBtn = (Button) findViewById(R.id.addSponsorImageBtn);

        findViewById(R.id.addPandits).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addSponsorImageBtn) {
            startActivity(new Intent(AdminActivity.this, AddSponsorPhotosActivity.class));
        }
        if(v.getId() == R.id.addPandits){
            Intent intent = new Intent(AdminActivity.this, AddPanditsActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.addKundliPandit){
            startActivity(new Intent(AdminActivity.this, AddKundliPanditActivity.class));
        }
        if(v.getId() == R.id.viewPandit)
        {
            startActivity(new Intent(AdminActivity.this, ViewPanditActivity.class));
        }

    }
}
