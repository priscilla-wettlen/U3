package model;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private List<Topping> availableToppings = new ArrayList<>();
    private List<Cake> cakesMenu = new ArrayList<>();
    private List<PerUnitItem> perUnitItems = new ArrayList<>();

    public ItemManager() {
        loadToppings();
        loadCakes();
        loadPerUnitItems();
    }

    public void loadToppings() {
        for (Topping topping : Topping.values()) {
            availableToppings.add(topping);
        }
    }

    public void loadCakes() {
        String[][] cakes = {
            {"Cake1", "4", "chocolate ganache", "lemon glaze"},
            {"Cake2", "6", "vanilla cream", "caramel sauce"},
            {"Cake3", "12", "toasted nuts", "coconut flakes", "toffee bits"},
            {"Cake4", "6", "vanilla cream", "sprinkles", "coconut flakes"}
        };

        for (String[] cake : cakes) {
            String name = cake[0];
            int size = Integer.parseInt(cake[1]);
            List<Topping> toppings = new ArrayList<>();

            for (int i = 2; i < cake.length; i++) {
                Topping toppingEnum = Topping.findToppingByName(cake[i]);
                if (toppingEnum != null) {
                    toppings.add(toppingEnum);
                }
            }

            cakesMenu.add(new Cake(name, size, toppings));
        }
    }

    public void loadPerUnitItems() {
        String[][] items = {
            {"Cinnamon roll", "35"},
            {"Gingerbread", "30"},
            {"Muffin", "25"},
            {"Cheese cake", "45"}
        };

        for (String[] item : items) {
            String name = item[0];
            double price = Double.parseDouble(item[1]);
            perUnitItems.add(new PerUnitItem(name, price));
        }
    }

    public List<Cake> getCakesMenu() {
        return cakesMenu;
    }

    public List<PerUnitItem> getPerUnitItems() {
        return perUnitItems;
    }
}