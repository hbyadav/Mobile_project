package com.hbyadav.myapplication;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class Helper extends SQLiteOpenHelper {
    SQLiteDatabase _db;
    Activity activity;

    public Helper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // helper creates database/tables
    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL(StudentbaseAdapter.DATABASE_CREATE);
        _db.execSQL(StudentbaseAdapter.DATABASE_SIGN);
        _db.execSQL(StudentbaseAdapter.DATABASE_SCHED);
    }

    // reset tables if changed
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        Log.w("TaskDBAdapter", "Upgrading from version " + _oldVersion + " to "
                + _newVersion + ", which will destroy all old data");

        _db.execSQL("DROP TABLE IF EXISTS " + StudentbaseAdapter.TABLE_LOGIN);
        _db.execSQL("DROP TABLE IF EXISTS " + StudentbaseAdapter.TABLE_DETAILS);
        _db.execSQL("DROP TABLE IF EXISTS " + StudentbaseAdapter.TABLE_SCHED);
        onCreate(_db);
    }
}
