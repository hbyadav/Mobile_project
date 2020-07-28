package com.hbyadav.myapplication;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.tourguide.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
<<<<<<< HEAD
    // Sign up for the app
=======

>>>>>>> 6f8a191ae6d1d8f1f16fcb315b088b610062f70b
    public void sign(View view) {
        Intent intent = new Intent(this, Student_sign.class);
        startActivity(intent);
    }
    // Log into the app
    public void login(View view) {
        Intent intent = new Intent(this, Student.class);
        startActivity(intent);
    }

    public void weather(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);

    }


}
