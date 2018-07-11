package com.example.danial.panditsutra1.ProfileClasses;

public class PanditProfile {
    public String userType;



    public String name;
    public String email;
    public String phone;
    public String location;
    public String paymentType;
    public String type;
    public PanditProfile(){

    }
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PanditProfile(String userType, String name, String email, String phone, String location, String paymentType, String type) {

        this.userType = userType;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.paymentType = paymentType;
        this.type = type;
    }
}
