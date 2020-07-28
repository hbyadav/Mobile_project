package com.hbyadav.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.android.tourguide.R;
public class Profile extends AppCompatActivity {
    EditText editTextName,editTextfees,editschool,edityear,editphone;
    Button btnCreateAccount,btnlogout;
    StudentbaseAdapter studentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
// get Instance of Database Adapter
        studentAdapter=new StudentbaseAdapter(this); studentAdapter=studentAdapter.open();
// Get References of Views
        Intent intent = getIntent();
        final String user = intent.getStringExtra("user");
        editTextName=(EditText)findViewById(R.id.edittextname);
        editTextfees=(EditText)findViewById(R.id.edittextfees);
        edityear=(EditText)findViewById(R.id.edittextyear);
        editschool=(EditText)findViewById(R.id.edittextschool);
        editphone=(EditText)findViewById(R.id.edittextphone);

        btnCreateAccount=(Button)findViewById(R.id.create); btnlogout=(Button)findViewById(R.id.logout);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                String name=editTextName.getText().toString();
                String fees=editTextfees.getText().toString();
                String phone=editphone.getText().toString();
                String school=editschool.getText().toString();
                String year=edityear.getText().toString();
// check if any of the fields are vacant
                if(user.equals("")||fees.equals("")||name.equals("")||phone.equals("")||school.equals("") ||year.equals(" "))
                {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields.",
                            Toast.LENGTH_LONG).show(); }
                else
                {
                    // Save the Data in Database
                    studentAdapter.insertdetails(user,name,fees,phone,school,year); Toast.makeText(getApplicationContext(), "Account Details Successfully Updated!",
                        Toast.LENGTH_LONG).show(); }
                Intent intent = new Intent(Profile.this, Student.class);
                startActivity(intent);
            } });
    }
    //    public void update(View v)
//    {
//        Intent intent=new Intent(this,Profile.class);
//        startActivity(intent);
//    }
//    public void logout(View view)
//    {
//        Intent intent=new Intent(this,MainActivity.class);
//        startActivity(intent);
//        }

    @Override
    protected void onDestroy() {
// TODO Auto-generated method stub
        super.onDestroy();
        studentAdapter.close(); }
}