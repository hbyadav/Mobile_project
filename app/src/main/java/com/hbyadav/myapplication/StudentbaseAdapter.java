package com.hbyadav.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentbaseAdapter {
    public static final String DATABASE_NAME = "login.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_DETAILS = "DETAILS";
    public static final String TABLE_LOGIN = "LOGIN";
    public static final String TABLE_SCHED = "SCHEDULE";

    // SQL Statement to create a new database and tables
    // Table for storing user details
    public static final String DATABASE_CREATE = "create table " + TABLE_DETAILS +
            "( " + "ID" + " INTEGER PRIMARY KEY AUTOINCREMENT," + "USERNAME text,NAME text,FEES text,SCHOOL text,YEAR text,PHONE text,PROFILE blob);";
    // Table for storing login credentials
    public static final String DATABASE_SIGN = "create table " + TABLE_LOGIN +
            "( " + "ID" + " integer primary key autoincrement," + "USERNAME text,PASSWORD text); ";
    // Table for storing schedule/course information
    public static final String DATABASE_SCHED = "create table " + TABLE_SCHED + "(" + "USERNAME text,COURSE text,subject text,hour text,day_week text);";

    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private Helper dbHelper;

    public StudentbaseAdapter(Context _context) {
        context = _context;
        dbHelper = new Helper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public StudentbaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    // insert user into Login database
    public void insertEntry(String userName, String password) {
        db = dbHelper.getWritableDatabase();
        ContentValues newValues = new ContentValues();

        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD", password);
        // Insert the row into table
        db.insert(TABLE_LOGIN, null, newValues);
        Log.i("StudentbaseAdaptor", "User added to Login DB");
    }

    // insert course info into schedule database
    public void Schedule(String course, String subject, String time, String week, String Username) {
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", Username);
        contentValues.put("COURSE", course);
        contentValues.put("subject", subject);
        contentValues.put("hour", time);
        contentValues.put("day_week", week);
        db.insert(TABLE_SCHED,null,contentValues);
    }

    // insert user information into details database
    public void insertdetails(String user, String name, String fees, String phone, String school, String year, byte[] profile) {
        db = dbHelper.getWritableDatabase();
        ContentValues newValues = new ContentValues(); // Assign column values for each row.
        newValues.put("USERNAME", user);
        newValues.put("NAME", name);
        newValues.put("FEES", fees);
        newValues.put("SCHOOL", school);
        newValues.put("YEAR", year);
        newValues.put("PHONE", phone);
        newValues.put("PROFILE", profile);
        db.insert(TABLE_DETAILS, null, newValues);
        Log.i("StudentbaseAdapter", "User details added");
    }

    // update user information in details database
    public void updateDetails(String user, String name, String fees, String phone, String school, String year, byte[] profile ) {
        db = dbHelper.getWritableDatabase();
        ContentValues newValues = new ContentValues(); // Assign column values for each row.
        newValues.put("NAME", name);
        newValues.put("FEES", fees);
        newValues.put("SCHOOL", school);
        newValues.put("YEAR", year);
        newValues.put("PHONE", phone);
        newValues.put("PROFILE", profile);
        // update DB
        if ((db.update(TABLE_DETAILS, newValues, "USERNAME=?", new String[] {user}))>0) {
            Log.i("StudentbaseAdapter", "Row updated");
        }
    }

    // method to return password of a given user to validate a login attempt
    public String getpassword(String username) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_LOGIN, null, "USERNAME=?", new String[]{username}, null, null, null);
        if (cursor.getCount() < 1) // if UserName does not exist
        {
            cursor.close();
            return "DOES NOT EXIST";
        }
        cursor.moveToFirst();       // otherwise, return password for validation checking
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        Log.i("StudentbaseAdapter", "Password Retrieved");
        return password;
    }

    // return user information from details table
    public Cursor getSingleEntry(String username) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from DETAILS where USERNAME = ?", new String[]{username});
        cursor.moveToFirst();

        return cursor;
    }

    // get schedule for user
    public Cursor schedule(String username) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from SCHEDULE where USERNAME = ?", new String[]{username});
        return cursor;
    }

    public void close() {
        db.close();
    }
}
