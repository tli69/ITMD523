package com.book.backend.dto.books;


import com.book.backend.model.Books;

import javax.validation.constraints.NotNull;

public class BooksDto {

    private Integer id;
    private @NotNull String title;
    private @NotNull String imageURL;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull Integer authorId;

    public BooksDto(Books book) {
        this.setId(book.getId());
        this.setTitle(book.getTitle());
        this.setImageURL(book.getImageURL());
        this.setDescription(book.getDescription());
        this.setPrice(book.getPrice());
        this.setAuthorId(book.getAuthor().getId());
    }

    public BooksDto(@NotNull String title, @NotNull double price, @NotNull String description, @NotNull Integer authorId) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.authorId = authorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public BooksDto() {
    }

}
