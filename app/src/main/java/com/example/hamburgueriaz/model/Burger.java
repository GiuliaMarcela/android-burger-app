package com.example.hamburgueriaz.model;

public class Burger {
    private String name;
    private String description;
    private String price;
    private int imageLocation;

    public Burger(String name, String description, String price, int imageLocation) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageLocation = imageLocation;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public int getImageLocation() {
        return imageLocation;
    }
}
