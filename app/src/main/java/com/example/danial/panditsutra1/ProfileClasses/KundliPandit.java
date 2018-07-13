package com.example.danial.panditsutra1.ProfileClasses;

import android.telephony.SignalStrength;

import java.security.PublicKey;

public class KundliPandit {

    public String userType;
    public String horoType;
    public String name;
    public String email;
    public String phone;
    public String health;
    public String travel;
    public String professional;
    public String personal;
    public String luck;

    public KundliPandit(){

    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getHoroType() {
        return horoType;
    }

    public void setHoroType(String horoType) {
        this.horoType = horoType;
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

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getTravel() {
        return travel;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getLuck() {
        return luck;
    }

    public void setLuck(String luck) {
        this.luck = luck;
    }

    public KundliPandit(String userType, String horoType, String name, String email, String phone, String health, String travel, String professional, String personal, String luck) {
        this.userType = userType;
        this.horoType = horoType;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.health = health;
        this.travel = travel;
        this.professional = professional;
        this.personal = personal;
        this.luck = luck;
    }
}
