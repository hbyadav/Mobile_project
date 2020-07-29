package com.hbyadav.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.android.tourguide.R;
public class Student extends AppCompatActivity {
    public String username;
    EditText editTextname, editTextpassword;
    StudentbaseAdapter studentAdapter;

    // This is the login screen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        getSupportActionBar().setTitle(R.string.login);
        studentAdapter = new StudentbaseAdapter(this);
        studentAdapter = studentAdapter.open();
        editTextname = (EditText) findViewById(R.id.editname);
        editTextpassword = (EditText) findViewById(R.id.password);
    }

    public void enter(View view) {
        // get the References of views
                // get The User name and Password
                username = editTextname.getText().toString();
                String password = editTextpassword.getText().toString();
                String storedPassword = studentAdapter.getpassword(username);

                if (password.equals(storedPassword)) {          // direct to user main hub if login is correct
                    Toast.makeText(Student.this, "Login Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Student.this, Student_display.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                } else {                                        // else, show error message
                    Toast.makeText(Student.this, "Username or Password is incorrect", Toast.LENGTH_LONG).show();
                }
        }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        studentAdapter.close();
    }

    public String Username() {
        username = editTextname.getText().toString();
        return username;
    }
}