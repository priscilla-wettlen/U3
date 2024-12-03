package model;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> orders;
    private List<PerUnitItem> perUnitItems;

    public OrderManager() {
        this.orders = new ArrayList<>();
        this.perUnitItems = new ArrayList<>();
        addPerUnitItems();
    }

    public void addPerUnitItems() {
        String[][] items = {
                {"Kanelbulle", "30"},
                {"Pepparkaka", "10"},
                {"Kopp kaffe", "15"},
                {"Kopp te", "13"}
        };

        for (String[] item : items) {
            String name = item[0];
            double price = Integer.parseInt(item[1]);

            perUnitItems.add(new PerUnitItem(name, price));
        }
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

//    public void addPerUnitItem(PerUnitItem item) {
//        perUnitItems.add(item);
//    }

    public List<Order> getOrderHistory() {
        return orders;
    }

    public List<PerUnitItem> getPerUnitItems(){
        return perUnitItems;
    }
}
