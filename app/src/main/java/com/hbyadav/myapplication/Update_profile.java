package com.hbyadav.myapplication;

import android.content.Intent;
import android.database.Cursor;
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

public class Update_profile extends AppCompatActivity {
    EditText editTextName,editTextfees,editschool,edityear,editphone;
    ImageView profilePic;
    Button updateBtn, picBtn;
    StudentbaseAdapter studentAdapter;
    byte[] profileArr;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().setTitle(R.string.uProfile);
        studentAdapter=new StudentbaseAdapter(this); studentAdapter=studentAdapter.open();

        Intent intent = getIntent();
        final String user = intent.getStringExtra("user");
        editTextName=(EditText)findViewById(R.id.edittextname);
        editTextfees=(EditText)findViewById(R.id.edittextfees);
        edityear=(EditText)findViewById(R.id.edittextyear);
        editschool=(EditText)findViewById(R.id.edittextschool);
        editphone=(EditText)findViewById(R.id.edittextphone);
        profilePic = (ImageView) findViewById(R.id.profilePic);
        picBtn = (Button) findViewById(R.id.pictureB);

        if (user != null) {
            Cursor _cursor = studentAdapter.getSingleEntry(user);
            String name = _cursor.getString(2);
            String fees = _cursor.getString(3);
            String school = _cursor.getString(4);
            String yr = _cursor.getString(5);
            String phone = _cursor.getString(6);
            profileArr = _cursor.getBlob(7);
                                     // initialize update form with current DB values for the user
            editTextName.setText(name);
            editTextfees.setText(fees);
            editschool.setText(school);
            edityear.setText(yr);
            editphone.setText(phone);
                                    // convert byte array to Bitmap to display current picture
            Bitmap profileBit = ImageConversion.getImage(profileArr);
            profilePic.setImageBitmap(profileBit);
        }

        updateBtn=(Button)findViewById(R.id.update);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {                           // button to update database
                // get field values for database
                String name=editTextName.getText().toString();
                String fees=editTextfees.getText().toString();
                String phone=editphone.getText().toString();
                String school=editschool.getText().toString();
                String year=edityear.getText().toString();

                // get image bitmap and convert to byte array to store in DB
                Bitmap profileBit = ((BitmapDrawable)profilePic.getDrawable()).getBitmap();
                try {
                    profileArr = ImageConversion.getBytes(profileBit);
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
                    // Update the Data in Database based on username
                    studentAdapter.updateDetails(user,name,fees,phone,school,year, profileArr); Toast.makeText(getApplicationContext(), "Account Details Successfully Updated!",
                        Toast.LENGTH_SHORT).show(); }
                    // after updating, return to home screen
                Intent intent = new Intent(Update_profile.this, Student_display.class);
                intent.putExtra("username", user);
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
// TODO Auto-generated method stub
        super.onDestroy();
        studentAdapter.close(); }
}