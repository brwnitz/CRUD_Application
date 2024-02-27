package com.example.crud_application.database;

import static com.example.crud_application.database.DatabaseHelper.COLUMN_AUTHOR;
import static com.example.crud_application.database.DatabaseHelper.COLUMN_ID;
import static com.example.crud_application.database.DatabaseHelper.COLUMN_PAGES;
import static com.example.crud_application.database.DatabaseHelper.COLUMN_TITLE;
import static com.example.crud_application.database.DatabaseHelper.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.crud_application.Models.Book;

public class BookDAO{
    private SQLiteDatabase writer;
    private SQLiteDatabase reader;
    private DatabaseHelper helper;

    public BookDAO(Context context) {
        helper = new DatabaseHelper(context);
        writer = helper.getWritableDatabase();
        reader = helper.getReadableDatabase();
    }

    public boolean addBook(Book book){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE,book.getTitle());
        cv.put(COLUMN_AUTHOR,book.getAuthor());
        cv.put(COLUMN_PAGES,book.getPageNumber());
        try {
            long result = writer.insert(TABLE_NAME, null, cv);
            return result != -1;
        }
        catch (Exception e) {
            Log.i("ERRO",String.valueOf(e));
            return false;
        }
    }

    public boolean updateBook(Book book, String row_id){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE,book.getTitle());
        cv.put(COLUMN_AUTHOR,book.getAuthor());
        cv.put(COLUMN_PAGES,book.getPageNumber());
        try {
            long result = writer.update(TABLE_NAME,cv,"_id=?",new String[]{row_id});
            return result != -1;
        }
        catch (Exception e){
            Log.d("ERRO",String.valueOf(e));
            return false;
        }
    }

    public boolean deleteBook(String row_id){
        try {
            long result = writer.delete(TABLE_NAME,"_id=?", new String[]{row_id});
            return result != -1;
        }
        catch (Exception e){
            Log.d("ERRO", String.valueOf(e));
            return false;
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}

