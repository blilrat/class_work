package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Bob on 9/24/2016.
 */
public class HabitTrackerContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitTrackerContract() {}

    /**
     * Inner class that defines constant values for the habits database table.
     */
    public static final class HabitEntry implements BaseColumns {

        /** Name of database table for the habit */
        public final static String TABLE_NAME = "habits";

        /**
         * Unique ID number for the habit (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;


        public final static String COLUMN_HABIT_NAME ="name";

        public final static String COLUMN_DATE = "date";

        public final static String COLUMN_HOW_LONG = "howLong";

        public final static String COLUMN_HABIT_CLASS = "class";

        /**
         * Possible values for the habit class.
         */
        public static final int CLASS_EXERCISE = 0;
        public static final int CLASS_SOCIAL = 1;
        public static final int CLASS_OTHER = 2;

    }

}
