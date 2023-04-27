package com.book.backend.model;

import com.book.backend.dto.items.BooksDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private @NotNull String title;
    private @NotNull String imageURL;
    private @NotNull double price;
    private @NotNull String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    Author author;

    @JsonIgnore
    @OneToMany(mappedBy = "item",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<WishList> wishLists = new HashSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item",  cascade = CascadeType.ALL)
    private List<Cart> carts;

    public Items(ItemsDto itemsDto, Author author) {
        this.title = itemsDto.getTitle();
        this.imageURL = itemsDto.getImageURL();
        this.description = itemsDto.getDescription();
        this.price = itemsDto.getPrice();
        this.author = author;
    }

    public Items(String title, String imageURL, double price, String description, Author author) {
        super();
        this.title = title;
        this.imageURL = imageURL;
        this.price = price;
        this.description = description;
        this.author = author;
    }

    public Items() {
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<WishList> getWishListList() {
        return wishLists;
    }

    public void setWishListList(Set<WishList> wishListList) {
        this.wishLists = wishLists;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", title='" + title + '\'' +
              ", imageURL='" + imageURL + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
