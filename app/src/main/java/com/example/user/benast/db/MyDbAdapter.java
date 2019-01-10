package com.example.user.benast.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbAdapter {
    MySqlDbHelper sqlDbHelper;

    public MyDbAdapter(Context context) {
        sqlDbHelper = new MySqlDbHelper(context);
    }

    public long insertData(String name, String password) {
        SQLiteDatabase db = sqlDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();//Creates an empty
        // set of values using the default initial size
        contentValues.put(MySqlDbHelper.NAME, name);
        contentValues.put(MySqlDbHelper.MY_PASSWORD, password);
        long id = db.insert(MySqlDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getData() {
        SQLiteDatabase db = sqlDbHelper.getReadableDatabase();
        String[] columns = {MySqlDbHelper.UID, MySqlDbHelper.NAME, MySqlDbHelper.MY_PASSWORD};
        Cursor cursor = db.query(MySqlDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(MySqlDbHelper.UID));
            String name = cursor.getString(cursor.getColumnIndex(MySqlDbHelper.NAME));
            String password = cursor.getString(cursor.getColumnIndex(MySqlDbHelper.MY_PASSWORD));
            buffer.append(cid + " " + name + " " + password + " \n");
        }
        return buffer.toString();
    }

    public int deleteUser(String userName) {
        SQLiteDatabase db = sqlDbHelper.getWritableDatabase();
        String[] whereArgs = {userName};
        int count = db.delete(MySqlDbHelper.TABLE_NAME, MySqlDbHelper.NAME + " = ?", whereArgs);
        return count;
    }

    public int updateName(String updateOld, String updateNew) {
        SQLiteDatabase db = sqlDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqlDbHelper.NAME, updateNew);
        String[] whereArgs = {updateOld};
        int count = db.update(MySqlDbHelper.TABLE_NAME, contentValues, MySqlDbHelper.NAME + "=?", whereArgs);
        return count;
    }



}
