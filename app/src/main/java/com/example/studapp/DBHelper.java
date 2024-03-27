package com.example.studapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "student.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Userdetails";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the database
        Log.d("DBHelper", "Creating database: " + DATABASE_NAME);
        // This method is called when the database is created for the first time
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + "name TEXT PRIMARY KEY, "
                + "contact TEXT, "
                + "dob TEXT)");
        Log.d("DBHelper", "Table " + TABLE_NAME + " created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed and create fresh table
        Log.d("DBHelper", "Upgrading database from version " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUserData(String name, String contact, String dob) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("contact", contact);
        values.put("dob", dob);
        long result = database.insert(TABLE_NAME, null, values);
        database.close();
        return result != -1;
    }

    public boolean updateUserData(String name, String contact, String dob) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("contact", contact);
        values.put("dob", dob);
        int rowsAffected = database.update(TABLE_NAME, values, "name=?", new String[]{name});
        database.close();
        return rowsAffected > 0;
    }

    public boolean deleteData(String name) {
        SQLiteDatabase database = this.getWritableDatabase();
        int rowsAffected = database.delete(TABLE_NAME, "name=?", new String[]{name});
        database.close();
        return rowsAffected > 0;
    }

    public Cursor getData() {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
