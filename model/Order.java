package model;

import java.util.List;

public class Order {
    private static int orderCounter = 0;
    private int orderId;
    private List<String> orderedItems;
    private double totalPrice;

    public Order(List<String> orderedItems, double totalPrice) {
        orderCounter++;
        this.orderId = orderCounter;
        this.orderedItems = orderedItems;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<String> getOrderedItems() {
        return orderedItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order " + getOrderId() + ": Total Price = " + getTotalPrice();
    }
}
