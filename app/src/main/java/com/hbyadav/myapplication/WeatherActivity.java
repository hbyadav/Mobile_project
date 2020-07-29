package com.hbyadav.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.android.tourguide.R;

public class WeatherActivity extends AppCompatActivity {        // activity to hold weather fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getSupportActionBar().setTitle(R.string.weather);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new WeatherFragment())
                    .commit();
        }
    }
}
