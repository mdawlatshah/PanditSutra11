package com.example.danial.panditsutra1.ProfileClasses;

public class UserProfile {


    public  String userName;
    public String userSureName;
    public String userEmail;
    public  String userPhone;

    public UserProfile(){

    }
    public UserProfile(String userName, String userSureName, String userEmail, String userPhone) {
        this.userName = userName;
        this.userSureName = userSureName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSureName() {
        return userSureName;
    }

    public void setUserSureName(String userSureName) {
        this.userSureName = userSureName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
