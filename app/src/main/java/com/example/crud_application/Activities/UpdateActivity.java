package com.example.crud_application.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crud_application.Models.Book;
import com.example.crud_application.R;
import com.example.crud_application.database.BookDAO;

public class UpdateActivity extends AppCompatActivity {

    EditText txtTitle, txtAuthor, numPages;
    String id, title, author, pages;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        txtTitle = findViewById(R.id.txtTitle2);
        txtAuthor = findViewById(R.id.txtAuthor2);
        numPages = findViewById(R.id.numPages2);
        btnUpdate = findViewById(R.id.btnUpdate2);
        getIntentData();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDAO bookDb = new BookDAO(UpdateActivity.this);
                Book book = new Book(txtTitle.getText().toString(),txtAuthor.getText().toString(),Integer.parseInt(numPages.getText().toString()));
                bookDb.updateBook(book, id);
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getIntentData(){
        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        author = getIntent().getStringExtra("author");
        pages = getIntent().getStringExtra("pages");
        txtTitle.setText(title);
        txtAuthor.setText(author);
        numPages.setText(pages);
    }
}