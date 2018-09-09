package com.example.danial.panditsutra1.PanditsClasses;

import com.google.firebase.database.Exclude;

public class PanditGalleryImageUploads {

    private String mName;
    private String mImageUrl;

    private String mKey;

    //constructor for firebase storage
    public PanditGalleryImageUploads() {
        //empty constructor needed  named as Upload
    }

    //constructor for getting name and image
    public PanditGalleryImageUploads(String name, String imageUrl) {

        if(name.trim().equals("")) {
            name = "No Name";
        }

        mName = name;
        mImageUrl = imageUrl;
    }


    public String getName() {
        return mName;
    }

    public void setName(String name) {

        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        mKey = key;
    }

}
