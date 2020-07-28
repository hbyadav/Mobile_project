package com.hbyadav.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.android.tourguide.R;
public class Student_display extends AppCompatActivity {
    private static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_display);  //set base display
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("username");        // variable to keep track of user logged in
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        SimpleFragmentAdapter adapter = new SimpleFragmentAdapter(this, getSupportFragmentManager());
// Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);            // create tabbed view
    }

    // utility function to get currently logged in user
    public static String Username() {
        return name;
    }
}