package model;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> orders;

    public OrderManager() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    // public int getOrderLength() {
        // return orders.size();
    // }

    public List<Order> getOrderHistory() {
        return orders;
    }
}
