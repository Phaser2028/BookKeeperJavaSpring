package ru.BookKeeper.models;

import org.hibernate.validator.constraints.NotEmpty;

public class Book {
    private int id;
    @NotEmpty(message = "название не должно быть пустым")
    private String title;
    @NotEmpty(message = "имя автора не должно быть пустым")
    private String author;
    private int publication_year;

    public Book(int id, String title, String author, int publication_year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publication_year = publication_year;
    }
    public Book(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }
}
