package com.hbyadav.myapplication;

import android.app.Activity;
import android.content.SharedPreferences;

public class StHubCityPreference {

    SharedPreferences sharedPreferences;

    public StHubCityPreference(Activity activity){
        sharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE);
    }


    // If the user has not chosen a city yet, return
    // Kansas City, USA as the default city
    String getCityForWeatherForcast(){
        return sharedPreferences.getString("city", "Kansas City, USA");
    }

    void setCityForWeatherForcast(String city){
        sharedPreferences.edit().putString("city", city).commit();
    }

}
