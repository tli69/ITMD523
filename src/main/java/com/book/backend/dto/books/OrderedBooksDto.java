package com.book.backend.dto.books;

import com.book.backend.model.OrderedBooks;

import javax.validation.constraints.NotNull;

public class OrderedBooksDto {
    private Integer id;
    private @NotNull String title;
    private @NotNull String imageURL;
    private @NotNull double price;
    private @NotNull String description;

    public OrderedBooksDto(OrderedBooks book) {
        this.setId(book.getId());
        this.setTitle(book.getTitle());
        this.setImageURL(book.getImageURL());
        this.setDescription(book.getDescription());
        this.setPrice(book.getPrice());
    }

    public OrderedBooksDto(@NotNull String title, @NotNull double price, @NotNull String description) {
        this.title = title;
        this.price = price;
        this.description = description;
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

    public OrderedBooksDto() {
    }
}
