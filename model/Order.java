package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<BakeryItem> currentOrderArray;
    double costCurrentOrder = 0;

    // public Order(List<String> currentOrderArray, double totalPrice) {
    public Order() {
        currentOrderArray = new ArrayList<>();
        // this.currentOrderArray = currentOrderArray;
        // this.totalPrice = totalPrice;
    }

    public List<String> getOrderItems() {
        List<String> items = new ArrayList<>();
        for (BakeryItem item : currentOrderArray) {
            items.add(item.toString());
        }
        return items;
    }

    public int getCurrentOrderLength() {
        return currentOrderArray.size();
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;

        for (BakeryItem item : currentOrderArray) {
            totalPrice += item.getPrice();
        }

        return totalPrice;
    }

    public void addItemToCurrentOrderArray(BakeryItem bakeryItem) {
        currentOrderArray.add(bakeryItem);
    }

    @Override
    public String toString() {
        return "Total Price = " + getTotalPrice();
    }
}
