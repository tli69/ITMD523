package com.item.backend.dto.checkout;

public class CheckoutItemDto {

    private String itemTitle;
    private int  quantity;
    private double price;
    private long itemId;
    private int userId;

    public CheckoutItemDto() {}

    public CheckoutItemDto(String itemTitle, int quantity, double price, long itemId, int userId) {
        this.itemTitle = itemTitle;
        this.quantity = quantity;
        this.price = price;
        this.itemId = itemId;
        this.userId = userId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
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

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
