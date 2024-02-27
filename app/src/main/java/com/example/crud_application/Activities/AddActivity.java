package com.example.crud_application.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.crud_application.Models.Book;
import com.example.crud_application.R;
import com.example.crud_application.database.BookDAO;

public class AddActivity extends AppCompatActivity {

    EditText txtTitle, txtAuthor, numPages;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        txtTitle = findViewById(R.id.txtTitle);
        txtAuthor = findViewById(R.id.txtAuthor);
        numPages = findViewById(R.id.numPages);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDAO bookDb = new BookDAO(AddActivity.this);
                Book book = new Book(txtTitle.getText().toString(),txtAuthor.getText().toString(),Integer.parseInt(numPages.getText().toString()));
                bookDb.addBook(book);
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}