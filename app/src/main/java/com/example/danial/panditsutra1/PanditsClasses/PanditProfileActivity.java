package com.example.danial.panditsutra1.PanditsClasses;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.danial.panditsutra1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class PanditProfileActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imageView;
    Button chngProfileImgBtn;
    Button addPanditGalleryBtn;

    private FirebaseStorage mStorage;
    private StorageReference storageRef;

    FirebaseAuth mAuth;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pandit_profile);

        imageView = (ImageView) findViewById(R.id.profileImage);
        chngProfileImgBtn = (Button) findViewById(R.id.changePanditProfileImgBtn);
        addPanditGalleryBtn = (Button) findViewById(R.id.galleryBtn);

        mStorage = FirebaseStorage.getInstance();
        storageRef = mStorage.getReference();

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        String userId = mUser.getUid();
        storageRef.child("PanditProfileImages/" + userId).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(PanditProfileActivity.this).load(uri)
                        .fit()
                        .centerInside()
                        .into(imageView);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.changePanditProfileImgBtn:
                startActivity(new Intent(PanditProfileActivity.this, PanditProfileImageActivity.class));
                break;
            case R.id.galleryBtn:
                startActivity(new Intent(PanditProfileActivity.this, AddPanditGalleryActivity.class));
                break;

        }
    }
}

