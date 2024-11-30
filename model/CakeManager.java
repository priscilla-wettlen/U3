package model;

import java.util.ArrayList;
import java.util.List;

public class CakeManager {
    private List<Topping> availableToppings = new ArrayList<>();
    private List<Cake> cakesMenu = new ArrayList<>();

    public CakeManager() {
        loadToppings();
        loadCakes();
    }

    public void loadToppings() {
        String[][] toppings = {
            {"chocolate ganache", "5"},
            {"lemon glaze", "7"},
            {"vanilla cream", "8"},
            {"caramel sauce", "6"},
            {"toasted nuts", "4"},
            {"coconut flakes", "6"},
            {"toffee bits", "5"},
            {"dried fruits", "9"},
            {"pistachios", "10"},
            {"sprinkles", "12"}
        };

        for (String[] topping : toppings) {
            availableToppings.add(new Topping(topping[0], Integer.parseInt(topping[1])));
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
                Topping topping = findToppingByName(cake[i]);
                if (topping != null) {
                    toppings.add(topping);
                }
            }

            cakesMenu.add(new Cake(name, size, toppings));
        }
    }

    private Topping findToppingByName(String name) {
        for (Topping topping : availableToppings) {
            if (topping.getName().equals(name)) {
                return topping;
            }
        }
        return null;
    }

    public List<Cake> getCakesMenu() {
        return cakesMenu;
    }
}