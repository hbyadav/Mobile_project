package com.hbyadav.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.android.tourguide.R;

import java.io.IOException;

public class Profile extends AppCompatActivity {
    EditText editTextName,editTextfees,editschool,edityear,editphone;
    ImageView profilePic;
    Button btnCreateAccount, picBtn;
    StudentbaseAdapter studentAdapter;
    byte[] profileArr;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle(R.string.cProfile);
        studentAdapter=new StudentbaseAdapter(this); studentAdapter=studentAdapter.open();

        Intent intent = getIntent();
        final String user = intent.getStringExtra("user");      // get passed username to add to details database

        editTextName=(EditText)findViewById(R.id.edittextname);
        editTextfees=(EditText)findViewById(R.id.edittextfees);
        edityear=(EditText)findViewById(R.id.edittextyear);
        editschool=(EditText)findViewById(R.id.edittextschool);
        editphone=(EditText)findViewById(R.id.edittextphone);
        profilePic = (ImageView) findViewById(R.id.profilePic);

        btnCreateAccount=(Button)findViewById(R.id.create);
        picBtn = (Button) findViewById(R.id.pictureB);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {           // button to create new account
                // get all fields from edited views
                String name=editTextName.getText().toString();
                String fees=editTextfees.getText().toString();
                String phone=editphone.getText().toString();
                String school=editschool.getText().toString();
                String year=edityear.getText().toString();
                // get image bitmap and convert to byte array to store in DB
                Bitmap profileBit = ((BitmapDrawable)profilePic.getDrawable()).getBitmap();
                try {
                    profileArr =ImageConversion.getBytes(profileBit);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // check if any of the fields are vacant
                if(user.equals("")||fees.equals("")||name.equals("")||phone.equals("")||school.equals("") ||year.equals(" "))
                {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields.",
                            Toast.LENGTH_LONG).show(); }
                else
                {
                    // Save the Data in Database
                    studentAdapter.insertdetails(user,name,fees,phone,school,year,profileArr); Toast.makeText(getApplicationContext(), "Account Details Successfully Added!",
                        Toast.LENGTH_SHORT).show(); }
                // direct to login page
                Intent intent = new Intent(Profile.this, Student.class);
                startActivity(intent);
            } });

        picBtn.setOnClickListener(new View.OnClickListener() {      // button to take picture and assign to profile view
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    takePictureIntent.putExtra("user", user);   // add username for passing back to activity on result
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // set taken picture to image view
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            profilePic.setImageBitmap(imageBitmap);
            profilePic.clearColorFilter();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        studentAdapter.close(); }
}