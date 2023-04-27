package com.book.backend.dto.checkout;

public class CheckoutItemDto {

    private String bookTitle;
    private int  quantity;
    private double price;
    private long bookId;
    private int userId;

    public CheckoutItemDto() {}

    public CheckoutItemDto(String bookTitle, int quantity, double price, long bookId, int userId) {
        this.bookTitle = bookTitle;
        this.quantity = quantity;
        this.price = price;
        this.bookId = bookId;
        this.userId = userId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
