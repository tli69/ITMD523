package com.book.backend.dto.cart;

import com.book.backend.model.Cart;
import com.book.backend.model.Books;

import javax.validation.constraints.NotNull;

public class CartItemDto {
    private Integer id;
    private @NotNull Integer quantity;
    private @NotNull Items item;

    public CartItemDto() {
    }

    public CartItemDto(Cart cart) {
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setBook(cart.getItem());
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", ItemTitle=" + item.getTitle() +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

}
