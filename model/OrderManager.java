package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The OrderManager class manages a collection of orders, allowing for the addition of orders
 * and retrieval of the order history.
 * Its responsibilities include storing orders in a list and providing methods to interact with this collection.
 *
 * @author Yin Ting Chan, Priscilla Wettlén
 */
public class OrderManager {
    private List<Order> orders;

    /**
     * This method constructs a new OrderManager with an empty list of orders.
     *
     * @author Yin Ting Chan, Priscilla Wettlén
     */
    public OrderManager() {
        this.orders = new ArrayList<>();
    }

    /**
     * This method adds a new order to the list of orders managed by this OrderManager.
     *
     * @param order The order to be added
     * @author Yin Ting Chan, Priscilla Wettlén
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * This method gets the history of all orders managed by this OrderManager.
     *
     * @return A list of all orders
     * @author Yin Ting Chan, Priscilla Wettlén
     */
    public List<Order> getOrderHistory() {
        return orders;
    }
}
