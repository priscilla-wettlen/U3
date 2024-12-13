package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Order class represents an individual order containing a list of items, an order number,
 * and methods to manage the items in the order and calculate the total price.
 * Each order is assigned a unique order number, which is automatically incremented.
 *
 * @author Yin Ting Chan, Priscilla Wettlén
 */
public class Order {
    private List<Item> currentOrderArray;
    private int orderNumber;
    private static int nextOrderNumber = 1;

    /**
     * This method constructs a new Order instance with an empty list of items and a unique order number.
     * The order number is automatically assigned and incremented for each new Order.
     *
     * @author Yin Ting Chan, Priscilla Wettlén
     */
    public Order() {
        currentOrderArray = new ArrayList<>();
        this.orderNumber = nextOrderNumber++;
    }

    /**
     * This method gets a list of string of the items in the order.
     *
     * @return A list of strings, where each string is the result of the toString() method of an item in the order.
     * @author Yin Ting Chan, Priscilla Wettlén
     */
    public List<String> getOrderItems() {
        List<String> items = new ArrayList<>();
        for (Item item : currentOrderArray) {
            items.add(item.toString());
        }
        return items;
    }

    /**
     * This method gets the number of items currently in the order.
     *
     * @return The number of items in the current order array list.
     * @author Yin Ting Chan
     */
    public int getCurrentOrderLength() {
        return currentOrderArray.size();
    }

    /**
     * This method calculates the total price of all items in the order.
     *
     * @return The total price of all items in the current order array list, as a double value.
     * @author Yin Ting Chan
     */
    public double getTotalPrice() {
        double totalPrice = 0.0;

        for (Item item : currentOrderArray) {
            totalPrice += item.getPrice();
        }

        return totalPrice;
    }

    /**
     * This method adds an item to the current order.
     *
     * @param item The item to be added to the current order array list.
     * @author Priscilla Wettlén
     */
    public void addItemToCurrentOrderArray(Item item) {
        currentOrderArray.add(item);
    }

    /**
     * This method gets the order number of this order.
     *
     * @return the order number as an integer
     * @author Priscilla Wettlén
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * This method returns a string of the order, including the order number and the total price of the order.
     *
     * @return a string of the order in the format: "Order [OrderNumber] Total Price = [TotalPrice]"
     * @author Priscilla Wettlén
     */
    @Override
    public String toString() {
        return "Order " + getOrderNumber() + "Total Price = " + getTotalPrice();
    }
}
