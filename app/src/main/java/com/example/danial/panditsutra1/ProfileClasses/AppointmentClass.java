package com.example.danial.panditsutra1.ProfileClasses;

import com.example.danial.panditsutra1.MainPageFiles.PanditsFragment;

public class AppointmentClass {
   public String userName;
    public  String userEmail;
    public String userPhone;
    public  String panditName;
    public  String panditEmail;
    public  String panditPhone;
    public  String timeAndDate;
    public  String address;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPanditName() {
        return panditName;
    }

    public void setPanditName(String panditName) {
        this.panditName = panditName;
    }

    public String getPanditEmail() {
        return panditEmail;
    }

    public void setPanditEmail(String panditEmail) {
        this.panditEmail = panditEmail;
    }

    public String getPanditPhone() {
        return panditPhone;
    }

    public void setPanditPhone(String panditPhone) {
        this.panditPhone = panditPhone;
    }

    public String getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(String timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AppointmentClass(String userName, String userEmail, String userPhone, String panditName, String panditEmail, String panditPhone, String timeAndDate, String address) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.panditName = panditName;
        this.panditEmail = panditEmail;
        this.panditPhone = panditPhone;
        this.timeAndDate = timeAndDate;
        this.address = address;
    }
    public AppointmentClass ()
    {

    }
}
