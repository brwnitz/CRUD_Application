package com.example.crud_application.Models;

public class Book {
    public String title;
    public String author;
    public int pageNumber;

    public Book(String title, String author, int pageNumber) {
        this.title = title;
        this.author = author;
        this.pageNumber = pageNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }


}
