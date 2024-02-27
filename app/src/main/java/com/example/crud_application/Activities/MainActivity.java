package com.example.crud_application.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.crud_application.Adapters.BookAdapter;
import com.example.crud_application.R;
import com.example.crud_application.database.BookDAO;
import com.example.crud_application.database.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton addButton;

    DatabaseHelper myDb;
    BookDAO bookDAO;
    ArrayList<String> book_id, book_author, book_title;
    ArrayList<Integer> book_pages;
    BookAdapter bookAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvBooks);
        addButton = findViewById(R.id.btnAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        myDb = new DatabaseHelper(MainActivity.this);
        bookDAO = new BookDAO(MainActivity.this);
        book_id = new ArrayList<>();
        book_author = new ArrayList<>();
        book_title = new ArrayList<>();
        book_pages = new ArrayList<>();

        displayData();
        bookAdapter = new BookAdapter(MainActivity.this,book_id, book_title, book_author, book_pages, this);
        recyclerView.setAdapter(bookAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void displayData(){
        Cursor cursor = bookDAO.readAllData();
        if(cursor.getCount() == 0){
            Log.d("BANCO", "SEM DADOS");
        }
        else{
            while(cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_author.add(cursor.getString(1));
                book_title.add(cursor.getString(2));
                book_pages.add(cursor.getInt(3));
            }
        }
    }
}