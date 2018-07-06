package com.example.danial.panditsutra1.AdminFiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.danial.panditsutra1.R;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addBtn;
    private Button viewBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toast.makeText(getApplicationContext(), "Hello to Admin View", Toast.LENGTH_LONG).show();

        addBtn = (Button) findViewById(R.id.addPandits);
        viewBtn = (Button) findViewById(R.id.viewPandits);





    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addPandits)
        {
            startActivity(new Intent(AdminActivity.this, AddPanditsActivity.class));
        }
        if(v.getId() == R.id.viewPandits)
        {
            startActivity(new Intent(AdminActivity.this, ViewPanditsActivity.class));
        }
    }
}
