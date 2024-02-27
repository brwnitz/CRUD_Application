package com.example.crud_application.Adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud_application.Activities.MainActivity;
import com.example.crud_application.Activities.UpdateActivity;
import com.example.crud_application.R;
import com.example.crud_application.database.BookDAO;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    public Context context;
    public ArrayList<String> book_id, book_title,book_author;
    public ArrayList<Integer> book_pages;
    Activity activity;

    public BookAdapter(Context context, ArrayList<String> book_id, ArrayList<String> book_title, ArrayList<String> book_author, ArrayList<Integer> book_pages, Activity activity){
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
        this.activity = activity;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView viewId, viewTitle, viewAuthor, viewPages;
        LinearLayout mainLayout;
        Button buttonUpdate, buttonDelete;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            viewId = itemView.findViewById(R.id.idView);
            viewTitle = itemView.findViewById(R.id.titleView);
            viewAuthor = itemView.findViewById(R.id.authorView);
            viewPages = itemView.findViewById(R.id.pagesView);
            mainLayout = itemView.findViewById(R.id.layoutBook);
            buttonUpdate = itemView.findViewById(R.id.btnUpdate2);
            buttonDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
    @NonNull
    @Override
    public BookAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.book_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.MyViewHolder holder, int position) {
        holder.viewId.setText(String.valueOf(book_id.get(position)));
        holder.viewTitle.setText(String.valueOf(book_title.get(position)));
        holder.viewAuthor.setText(String.valueOf(book_author.get(position)));
        holder.viewPages.setText(String.valueOf(book_pages.get(position)));
        holder.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(book_id.get(holder.getAdapterPosition())));
                intent.putExtra("title", String.valueOf(book_title.get(holder.getAdapterPosition())));
                intent.putExtra("author", String.valueOf(book_author.get(holder.getAdapterPosition())));
                intent.putExtra("pages", String.valueOf(book_pages.get(holder.getAdapterPosition())));
                activity.startActivityForResult(intent,1);
            }
        });
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog(String.valueOf(book_title.get(holder.getAdapterPosition())),String.valueOf(book_id.get(holder.getAdapterPosition())));
            }
        });

    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }
    public void confirmDialog(String title, String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Deletar  " + title + "?");
        builder.setMessage("Tem certeza que deseja deletar " + title + "?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BookDAO bookDb =  new BookDAO(context);
                bookDb.deleteBook(id);
                Intent intent = new Intent(context, MainActivity.class);
                activity.startActivityForResult(intent,2);
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

}


