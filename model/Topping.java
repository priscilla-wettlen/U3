package model;

/**
 * The Topping enum represents various toppings available for an item, each with a specific
 * name and price. It provides methods to get the name, price, and find a topping by its name.
 * Its responsibilities include managing predefined toppings with their associated
 * attributes and providing utility methods for accessing and representing them.
 *
 * @author Yin Ting Chan, Priscilla Wettlén
 */
public enum Topping {
    CHOCOLATE_GANACHE("chocolate ganache", 5),
    LEMON_GLAZE("lemon glaze", 7),
    VANILLA_CREAM("vanilla cream", 8),
    CARAMEL_SAUCE("caramel sauce", 6),
    TOASTED_NUTS("toasted nuts", 4),
    COCONUT_FLAKES("coconut flakes", 6),
    TOFFEE_BITS("toffee bits", 5),
    DRIED_FRUITS("dried fruits", 9),
    PISTACHIOS("pistachios", 10),
    SPRINKLES("sprinkles", 12);

    private final String name;
    private final double price;

    /**
     * This method constructs a Topping with the specified name and price.
     *
     * @param name the name of the topping
     * @param price the price of the topping in kr (kroner)
     * @author Priscilla Wettlén
     */
    Topping(String name, int price) {
        this.name = name;
        this.price = price;
    }

    /**
     * This method gets the name of the topping.
     *
     * @return The name of the topping as a string
     * @author Priscilla Wettlén
     */
    public String getName() {
        return name;
    }

    /**
     * This method gets the price of the topping.
     *
     * @return The price of the topping as a double in kr
     * @author Priscilla Wettlén
     */
    public double getPrice() {
        return price;
    }

    /**
     * This method finds a topping by its name, ignoring the case.
     *
     * @param name The name of the topping to find
     * @return The Topping if found, or null if no topping matches the name
     * @author Yin Ting Chan
     */
    public static Topping findToppingByName(String name) {
        for (Topping topping : values()) {
            if (topping.getName().equalsIgnoreCase(name)) {
                return topping;
            }
        }
        return null;
    }

    /**
     * This method returns a string of the topping, including its name and price.
     *
     * @return A string of the topping in the format: "name (price kr)"
     * @author Yin Ting Chan
     */
    @Override
    public String toString() {
        return name + " (" + price + " kr)";
    }
}