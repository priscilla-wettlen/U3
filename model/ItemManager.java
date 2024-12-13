package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The ItemManager class is responsible for managing the available menu items,
 * including cakes, toppings, and per-unit items. It provides methods to load
 * these items, get cake menu & per-unit items, and add new cakes to the menu.
 *
 * @author Yin Ting Chan, Priscilla Wettlén
 */
public class ItemManager {
    private List<Topping> availableToppings = new ArrayList<>();
    private List<Cake> cakesMenu = new ArrayList<>();
    private List<PerUnitItem> perUnitItems = new ArrayList<>();

    /**
     * This method constructs an ItemManager and loads the available toppings, cakes, and per-unit items.
     * This constructor is responsible for initialising the collections of available items.
     *
     * @author Yin Ting Chan, Priscilla Wettlén
     */
    public ItemManager() {
        loadToppings();
        loadCakes();
        loadPerUnitItems();
    }

    /**
     * This method loads all available toppings into the list of available toppings by iterating over the Topping enum.
     *
     * @author Yin Ting Chan
     */
    public void loadToppings() {
        for (Topping topping : Topping.values()) {
            availableToppings.add(topping);
        }
    }

    /**
     * This method loads the cakes into the cakes menu using predefined cake data. Each cake is created with a name, size,
     * and associated toppings, and is added to the cakesMenu list.
     *
     * @author Yin Ting Chan
     */
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

    /**
     * This method loads per-unit items into the list of perUnitItems. Each item is created with a name and price.
     *
     * @author Priscilla Wettlén
     */
    public void loadPerUnitItems() {
        String[][] items = {
            {"Cinnamon roll", "35"},
            {"Gingerbread cookie", "10"},
            {"Muffin", "25"},
            {"Donut", "20"}
        };

        for (String[] item : items) {
            String name = item[0];
            double price = Double.parseDouble(item[1]);
            perUnitItems.add(new PerUnitItem(name, price));
        }
    }

    /**
     * This method gets the list of available cakes from the cakes menu.
     *
     * @return A list of cakes in the menu.
     * @author Yin Ting Chan
     */
    public List<Cake> getCakesMenu() {
        return cakesMenu;
    }

    /**
     * This method gets the list of available per-unit items.
     *
     * @return A list of per-unit items.
     * @author Priscilla Wettlén
     */
    public List<PerUnitItem> getPerUnitItems() {
        return perUnitItems;
    }

    /**
     * This method adds a new cake to the cakes menu.
     *
     * @param newCake The cake to be added to the menu.
     * @author Yin Ting Chan
     */
    public void addCakeToMenu(Cake newCake) {
        cakesMenu.add(newCake);
    }
}