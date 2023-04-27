package com.item.backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.item.backend.dto.items.BooksDto;

@Entity
@Table(name = "ordered")
public class OrderedItems {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private @NotNull String title;
        private @NotNull String imageURL;
        private @NotNull double price;
        private @NotNull String description;


        public OrderedItems(ItemsDto itemsDto) {
            this.title = itemsDto.getTitle();
            this.imageURL = itemsDto.getImageURL();
            this.description = itemsDto.getDescription();
            this.price = itemsDto.getPrice();
        }

        public OrderedItems(String title, String imageURL, double price, String description) {
            super();
            this.title = title;
            this.imageURL = imageURL;
            this.price = price;
            this.description = description;
        }


        public OrderedItems() {
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
