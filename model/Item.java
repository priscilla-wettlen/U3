package model;

import java.util.ArrayList;

public abstract class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract double calculatePrice();

    @Override
    public String toString() {
        return String.format(
                "%s%n: %.2f kr ", name, price);
    }
}

