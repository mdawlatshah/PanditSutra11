package com.example.danial.panditsutra1.ProfileClasses;

import android.telephony.SignalStrength;

import java.security.PublicKey;

public class KundliPandit {

    public String health;
    public String personalLife;
    public String profession;
    public String travel;
    public String luck;

    public KundliPandit(){

    }
    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getPersonalLife() {
        return personalLife;
    }

    public void setPersonalLife(String personalLife) {
        this.personalLife = personalLife;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getTravel() {
        return travel;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }

    public String getLuck() {
        return luck;
    }

    public void setLuck(String luck) {
        this.luck = luck;
    }

    public KundliPandit(String health, String personalLife, String profession, String travel, String luck) {
        this.health = health;
        this.personalLife = personalLife;
        this.profession = profession;
        this.travel = travel;
        this.luck = luck;
    }






}
