package model;

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

    Topping(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public static Topping findToppingByName(String name) {
        for (Topping topping : values()) {
            if (topping.getName().equalsIgnoreCase(name)) {
                return topping;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name + " (" + price + " kr)";
    }
}