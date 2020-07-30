package com.hbyadav.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.android.tourguide.R;

public class StHubWeatherActivity extends AppCompatActivity {        // activity to hold weather fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st_hub_weather);
        getSupportActionBar().setTitle(R.string.weather);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new StHubWeatherFragment())
                    .commit();
        }
    }
}
