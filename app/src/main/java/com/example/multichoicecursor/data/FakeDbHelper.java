package com.example.multichoicecursor.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.multichoicecursor.data.FakeContract.FakeEntry;

public class FakeDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "accounting.db";
    String SQL_CREATE_ENTRIES_CLIMBERS =
            "CREATE TABLE " + FakeEntry.TABLE_NAME + "(" +
                    FakeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FakeEntry.COLUMN_NAME + " TEXT NOT NULL," +
                    FakeEntry.COLUMN_IS_CHECKED + " INTEGER NOT NULL DEFAULT 0);";

    public FakeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table Fake
        db.execSQL(SQL_CREATE_ENTRIES_CLIMBERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
