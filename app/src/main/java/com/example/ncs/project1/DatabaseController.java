package com.example.ncs.project1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PYOJIHYE on 2017-05-16.
 */


class DataBaseController extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "project.db";
    private static final int DATABASE_VERSION = 2;

    public DataBaseController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contacts (_id INTEGER PRIMARY KEY AUTOINCREMENT, subject TEXT, url TEXT, date TEXT); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

}
