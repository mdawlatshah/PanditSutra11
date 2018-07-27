package com.example.danial.panditsutra1.MainPageFiles;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.danial.panditsutra1.PanditsClasses.DeleteAppointmentActivity;
import com.example.danial.panditsutra1.PanditsClasses.PanditProfileActivity;
import com.example.danial.panditsutra1.ProfileClasses.AppointmentClass;
import com.example.danial.panditsutra1.R;
import com.example.danial.panditsutra1.ViewAppointmentsActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.danial.panditsutra1.R.*;
import static com.example.danial.panditsutra1.R.id.userAppointmentTv;


/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFragment extends Fragment implements View.OnClickListener {


    public OtherFragment() {
        // Required empty public constructor
    }
        Button btn;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View InputFragmentView = inflater.inflate(layout.fragment_other, container, false);

        btn = (Button)InputFragmentView.findViewById(id.viewAppBtn);

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), ViewAppointmentsActivity.class));
//            }
//        });
        btn.setOnClickListener(this);
        // Inflate the layout for this fragment
        return InputFragmentView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == id.viewAppBtn)
        {
            startActivity(new Intent(getActivity(), ViewAppointmentsActivity.class));
        }
    }
}
