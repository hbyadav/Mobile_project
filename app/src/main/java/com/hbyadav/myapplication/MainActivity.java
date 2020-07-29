package com.hbyadav.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.android.tourguide.R;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(R.string.Student_Home);
    }

    // Sign up for the app
    public void sign(View view) {
        Intent intent = new Intent(this, Student_sign.class);
        startActivity(intent);
    }
    // Log into the app
    public void login(View view) {
        Intent intent = new Intent(this, Student.class);
        startActivity(intent);
    }
    // View weather API activity
    public void weather(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);

    }


}
