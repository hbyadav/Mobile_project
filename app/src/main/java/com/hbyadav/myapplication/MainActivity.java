package com.hbyadav.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.android.tourguide.R;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> rssLinks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(R.string.Student_Home);

	    Button btnRediff = findViewById(R.id.btnRediff);
        Button btnCinemaBlend = findViewById(R.id.btnCinemaBlend);
        btnRediff.setOnClickListener(this);
        btnCinemaBlend.setOnClickListener(this);

        rssLinks.add("http://www.rediff.com/rss/moviesreviewsrss.xml");
        rssLinks.add("http://www.cinemablend.com/rss_review.php");
    }

	@Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRediff:
                startActivity(new Intent(MainActivity.this, RSSFeedActivity.class).putExtra("rssLink", rssLinks.get(0)));
                break;

            case R.id.btnCinemaBlend:
                startActivity(new Intent(MainActivity.this, RSSFeedActivity.class).putExtra("rssLink", rssLinks.get(1)));
                break;
        }
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
