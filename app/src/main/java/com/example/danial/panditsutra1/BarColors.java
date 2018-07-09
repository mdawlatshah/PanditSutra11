package com.example.danial.panditsutra1;

import android.app.Activity;
import android.os.Build;
import android.support.v4.content.ContextCompat;

public class BarColors {

    //for changing navigation bar color too
//    public static void colorBars(Activity activity, int statusColor, int navColor) {
    public static void colorBars(Activity activity, int statusColor) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().setStatusBarColor(
                    barColor(
                            ContextCompat.getColor(activity, statusColor)));

//            activity.getWindow().setNavigationBarColor(
//                    barColor(
//                            ContextCompat.getColor(activity, navColor)));
        }
    }

    private static int barColor(int color) {
        return color;
    }

}