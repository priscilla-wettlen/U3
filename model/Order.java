package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Item> currentOrderArray;
    private int orderNumber;
    private static int nextOrderNumber = 1;

    public Order() {
        currentOrderArray = new ArrayList<>();
        this.orderNumber = nextOrderNumber++;
    }

    public List<String> getOrderItems() {
        List<String> items = new ArrayList<>();
        for (Item item : currentOrderArray) {
            items.add(item.toString());
        }
        return items;
    }

    public int getCurrentOrderLength() {
        return currentOrderArray.size();
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;

        for (Item item : currentOrderArray) {
            totalPrice += item.getPrice();
        }

        return totalPrice;

    }

    public void addItemToCurrentOrderArray(Item item) {
            currentOrderArray.add(item);

    }

    public int getOrderNumber() {
        return orderNumber;
    }

    @Override
    public String toString() {
        return "Order " + getOrderNumber() + "Total Price = " + getTotalPrice();
    }
}
