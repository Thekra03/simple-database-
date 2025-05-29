package com.example.app2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "messages.db";
    private static final String TABLE_NAME = "messages_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "MESSAGE_TITLE";
    private static final String COL3 = "MESSAGE_BODY";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + COL1 + " INTEGER PRIMARY KEY,"
                + COL2 + " TEXT NOT NULL,"
                + COL3 + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to add message to the database
    public boolean addMessage(int id, String messageBody, String messageTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, id);
        contentValues.put(COL2, messageTitle);
        contentValues.put(COL3, messageBody);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    // Method to get all messages
    public Cursor getItem() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
