package com.example.appweather.ultilties;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate {
    public static String convertValueDateToString(int value) {
        Date date = new java.util.Date(value * 1000L);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
}
