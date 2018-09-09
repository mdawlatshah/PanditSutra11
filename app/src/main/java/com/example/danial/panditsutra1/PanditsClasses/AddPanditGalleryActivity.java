package com.example.danial.panditsutra1.PanditsClasses;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danial.panditsutra1.AdminSponsorFiles.ShowSponsorImages;
import com.example.danial.panditsutra1.AdminSponsorFiles.SponsorImageUploads;
import com.example.danial.panditsutra1.BarColors;
import com.example.danial.panditsutra1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddPanditGalleryActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private TextView mTextViewShowUploads;
    private EditText mEditTextFileName;
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    //fixing any multiple same file uploading
    private Uri mImageUri;

    //firebase storage
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    FirebaseAuth firebaseAuth;
    String userId;

    private StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pandit_gallery);

        BarColors.colorBars(this, R.color.status_bar);

        mButtonChooseImage = findViewById(R.id.btn_choose_image);
        mButtonUpload = findViewById(R.id.btn_upload);
        mTextViewShowUploads = findViewById(R.id.txt_view_show_uploads);
        mEditTextFileName = findViewById(R.id.edt_text_file_name);
        mImageView = findViewById(R.id.imag_view);
        mProgressBar = findViewById(R.id.mprogress_barr);

        ProgressBar progressbar = (ProgressBar) findViewById(R.id.mprogress_barr);
        int color = getResources().getColor(R.color.subtitle);
        progressbar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        progressbar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);

        //firebase storage
        mStorageRef = FirebaseStorage.getInstance().getReference("PanditGallery");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("PanditGallery");

        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getUid();
//        Toast.makeText(getApplicationContext(), userId, Toast.LENGTH_SHORT).show();

        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(AddPanditGalleryActivity.this, "Upload in Progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }

            }
        });

        mTextViewShowUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagesActivity();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.with(this)
                    .load(mImageUri)
                    .fit()
                    .centerInside()
                    .into(mImageView);
            //in case picasso wasnt used
            //mImageView.setImageURI(mImageUri);
        }
    }

    //to retrieve the file extension
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    private void uploadFile() {
        if (mImageUri != null) {

            StorageReference fileReference = mStorageRef.child("" + userId).child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //too quick that wont b easily visible
                            //mProgressBar.setProgress(0);

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 3000);

                            Toast.makeText(AddPanditGalleryActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();
                            SponsorImageUploads upload = new SponsorImageUploads(mEditTextFileName.getText().toString().trim(),
                                    taskSnapshot.getDownloadUrl().toString());

                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddPanditGalleryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int)progress);
                        }
                    });

        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }


    private void openImagesActivity() {
        Intent intent = new Intent(this, ShowPanditGalleryActivity.class);
        startActivity(intent);
    }

}
