package model;

import java.util.ArrayList;

/**
 * The Item class is an abstract class representing an item with a name and price.
 * It provides getter and setter methods for getting and setting the name and price of the item,
 * as well as a method to calculate the price, which must be implemented by subclasses.
 *
 * @author Yin Ting Chan, Priscilla Wettlén
 */
public abstract class Item {
    private String name;
    private double price;

    /**
     * This method constructs an Item with a name and price.
     *
     * @param name  The name of the item.
     * @param price The price of the item.
     * @author Yin Ting Chan
     */
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * This method gets the name of the item.
     *
     * @return The name of the item.
     * @author Yin Ting Chan
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name of the item.
     *
     * @param name The new name for the item.
     * @author Yin Ting Chan
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method gets the price of the item.
     *
     * @return The price of the item.
     * @author Yin Ting Chan
     */
    public double getPrice() {
        return price;
    }

    /**
     * This method sets the price of the item.
     *
     * @param price The new price for the item.
     * @author Yin Ting Chan
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * This is an abstract method for calculating the price of the item.
     * Subclasses must implement this method to define their specific price calculation logic.
     *
     * @return The calculated price of the item.
     * @author Yin Ting Chan
     */
    public abstract double calculatePrice();

    /**
     * This method returns a string of the item, including its name and price.
     *
     * @return A formatted string containing the item's name and price where the price is rounded to two decimal places.
     * @author Priscilla Wettlén
     */
    @Override
    public String toString() {
        return String.format(
                "%s%n: %.2f kr", name, price);
    }
}

