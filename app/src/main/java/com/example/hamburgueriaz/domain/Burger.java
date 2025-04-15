package com.example.hamburgueriaz.domain;

public class Burger {
    private final String name;
    private final String description;
    private final String price;
    private final int imageLocation;

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
