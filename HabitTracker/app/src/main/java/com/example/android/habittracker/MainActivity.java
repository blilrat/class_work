package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.habittracker.data.HabitTrackerContract.HabitEntry;
import com.example.android.habittracker.data.HabitTrackerDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitTrackerDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Insert new habit into the db using dummy data
    private void createHabit() {

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "walked the dog");
        values.put(HabitEntry.COLUMN_DATE, "09/24/2016");
        values.put(HabitEntry.COLUMN_HOW_LONG, 20);
        values.put(HabitEntry.COLUMN_HABIT_CLASS, HabitEntry.CLASS_EXERCISE);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

    }

   //display read only data from the db
    private void readHabit() {

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_DATE,
                HabitEntry.COLUMN_HOW_LONG,
                HabitEntry.COLUMN_HABIT_CLASS
        };

        // Filter results WHERE "class" = 'exercise'
        String selection = HabitEntry.COLUMN_HABIT_CLASS + " = ?";
        String[] selectionArgs = { "exercise" };

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

    }
    
//Update the habit db
    private void updateHabit() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "treadmill");

        // Which row to update, based on the name
        String selection = HabitEntry.COLUMN_HABIT_NAME + " LIKE ?";
        String[] selectionArgs = {"walk the dog"};

        int count = db.update(
                HabitEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    //delete from the habits db
    private void deleteHabit() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        // Define 'where' part of query.
        String selection = HabitEntry.COLUMN_HABIT_NAME + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {"walk the dog"};
        // Issue SQL statement.
        db.delete(HabitEntry.TABLE_NAME, selection, selectionArgs);

    }
}
