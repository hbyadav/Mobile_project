package com.hbyadav.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.android.tourguide.R;
public class Student_home extends AppCompatActivity {       // home activity to hold home fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new Home_fragment()).commit();
    }
}
