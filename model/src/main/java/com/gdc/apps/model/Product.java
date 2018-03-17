package com.gdc.apps.model;

public class Product {

    private Long id;
    private String category;
    private String name;
    private int price;
    private short rating;

    public Product(String category, String name, int price, short rating) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public Product(Long id, String category, String name, int price, short rating) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public short getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }
}
