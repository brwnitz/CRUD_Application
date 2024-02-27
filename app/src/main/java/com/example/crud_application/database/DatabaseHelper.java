package com.example.crud_application.database;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
     static final String DATABASE_NAME = "TesteCRUD.db";
     static final int DATABASE_VERSION = 1;
     static final String TABLE_NAME = "my_library";
     static final String COLUMN_ID = "_id";
     static final String COLUMN_TITLE = "book_title";
     static final String COLUMN_AUTHOR = "book_author";
     static final String COLUMN_PAGES = "book_pages";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME+
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "+
                COLUMN_AUTHOR + " TEXT, "+
                COLUMN_TITLE + " TEXT, "+
                COLUMN_PAGES + " INTEGER);";
        try{
            db.execSQL(query);
            Log.i("TABELA","CRIOU TABELA");
        }
        catch (Exception e){
            e.printStackTrace();
            Log.i("TABELA","NAO CRIOU TABELA");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
