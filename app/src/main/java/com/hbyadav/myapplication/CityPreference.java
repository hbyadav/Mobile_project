package com.hbyadav.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;

import java.util.Locale;

public class CityPreference {

    SharedPreferences prefs;

    public CityPreference(Activity activity){
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }


    // If the user has not chosen a city yet, return
    // Kansas City, USA as the default city
    String getCity(){
        return prefs.getString("city", "Kansas City, USA");
    }

    void setCity(String city){
        prefs.edit().putString("city", city).commit();
    }

}
