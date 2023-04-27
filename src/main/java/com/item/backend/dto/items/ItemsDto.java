package com.item.backend.dto.items;


import javax.validation.constraints.NotNull;

import com.item.backend.model.Items;

public class ItemsDto {

    private Integer id;
    private @NotNull String title;
    private @NotNull String imageURL;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull Integer authorId;

    public ItemsDto(Items item) {
        this.setId(item.getId());
        this.setTitle(item.getTitle());
        this.setImageURL(item.getImageURL());
        this.setDescription(item.getDescription());
        this.setPrice(item.getPrice());
    }

    public ItemsDto(@NotNull String title, @NotNull double price, @NotNull String description, @NotNull Integer authorId) {
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

    public ItemsDto() {
    }

}
