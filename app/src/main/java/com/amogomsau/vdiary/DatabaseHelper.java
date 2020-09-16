package com.amogomsau.vdiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Diary.db";
    private static final int DATAVASE_VERSION = 1;

    // google id, date, post title, post description, image data, location data

    private static final String TABLE_NAME = "entries";
    private static final String COLUMN_ID = "google_id";
    private static final String COLUMN_DATE = "entry_date";
    private static final String COLUMN_TITLE = "entry_title";
    private static final String COLUMN_DESCRIPTION = "entry_description";
    private static final String COLUMN_IMAGE = "entry_image";
    private static final String COLUMN_LOCATION = "entry_location";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATAVASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_IMAGE + " TEXT, " +
                COLUMN_LOCATION + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addEntry(int id, String date, String title, String description, String image, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, id);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_IMAGE, image);
        cv.put(COLUMN_LOCATION, location);
        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
        }
    }
}
