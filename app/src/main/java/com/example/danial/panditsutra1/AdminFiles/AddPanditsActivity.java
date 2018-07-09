package com.example.danial.panditsutra1.AdminFiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.danial.panditsutra1.R;

import java.util.ArrayList;

public class AddPanditsActivity extends AppCompatActivity {
    Spinner paymentSpinner, typeSpinner;
    ArrayList<String> paymentArray, typeArray;
    ArrayAdapter<String> payment_adapter, type_adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_pandits);


        paymentArray = new ArrayList<String>();
        paymentArray.add("Premium");
        paymentArray.add("Paid");
        paymentArray.add("unPaid");

        typeArray = new ArrayList<String>();
        typeArray.add("Vastul");
        typeArray.add("Astrologal");
        typeArray.add("Sastri");
        typeArray.add("Byias");
        typeArray.add("Mohant");
        typeArray.add("Jyotish");

        paymentSpinner = (Spinner) findViewById(R.id.paymentSpinner);
        typeSpinner = (Spinner) findViewById(R.id.paditsType);



        payment_adapter =new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item, paymentArray);
        payment_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentSpinner.setAdapter(payment_adapter);

        type_adapter =new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item, typeArray);
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(type_adapter);

        paymentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String s = parent.getItemAtPosition(position).toString();
                    Toast.makeText(AddPanditsActivity.this,s, Toast.LENGTH_LONG).show();
                    typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String type = parent.getItemAtPosition(position).toString();
                            Toast.makeText(AddPanditsActivity.this, type, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
