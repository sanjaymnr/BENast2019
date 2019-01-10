package com.example.user.benast.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//My SQL DB Helper
public class MySqlDbHelper extends SQLiteOpenHelper {
    public final Context context;
    public static final String DATABASE_NAME = "myDatabase";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "myTable"; // Table Name
    public static final String UID = "_id"; // Column I (Primary Key)
    public static final String NAME = "Name"; //Column II
    public static final String MY_PASSWORD = "Password";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) ," +
            MY_PASSWORD + " VARCHAR(225));";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public MySqlDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
    }
}
