package model;
import java.util.List;

/**
 * This class represents a Cake item with a specific size and optional toppings.
 * The class is responsible for managing the properties of a Cake,
 * calculating its price, and providing a string of its details.
 *
 * @author Yin Ting Chan, Priscilla Wettlén
 */
public class Cake extends Item {
    private int cakeSize;
    private List<Topping> toppings;

    /**
     * This method constructs a Cake instance with the specified name, size, and toppings.
     *
     * @param name     The name of the cake
     * @param cakeSize The size of the cake in pieces
     * @param toppings The list of toppings for the cake
     * @author Yin Ting Chan
     */
    public Cake(String name, int cakeSize, List<Topping> toppings) {
        super(name, 0);
        this.cakeSize = cakeSize;
        this.toppings = toppings;
    }

    /**
     * This method gets the size of the cake.
     *
     * @return The size of the cake in pieces
     * @author Yin Ting Chan
     */
    public int getCakeSize() {
        return cakeSize;
    }

    /**
     * This method updates the size of the cake.
     *
     * @param cakeSize The new size of the cake in pieces
     * @author Yin Ting Chan
     */
    public void setCakeSize(int cakeSize) {
        this.cakeSize = cakeSize;
    }

    /**
     * This method gets the list of toppings added to the cake.
     *
     * @return The list of toppings
     * @author Yin Ting Chan
     */
    public List<Topping> getToppings() {
        return toppings;
    }

    /**
     * This method updates the list of toppings for the cake.
     *
     * @param toppings The new list of toppings
     * @author Yin Ting Chan
     */
    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    /**
     * This method calculates the price of the cake based on its size and toppings.
     *
     * @return The total price of the cake
     * @author Yin Ting Chan
     */
    @Override
    public double calculatePrice() {
        double basePrice = 0;

        switch (cakeSize) {
            case 4:
                basePrice = 100;
                break;
            case 6:
                basePrice = 150;
                break;
            case 12:
                basePrice = 300;
                break;
            default:
                basePrice = 50;
        }

        for (Topping topping : toppings) {
            basePrice += topping.getPrice();
        }

        super.setPrice(basePrice);
        return basePrice;
    }

    /**
     * This method gets a list of topping names.
     *
     * @return A string of toppings
     * @author Yin Ting Chan
     */
    public String getToppingsList() {
        String toppingsList = "";
        //List<Topping> toppings = getToppings();
        for (int i = 0; i < toppings.size(); i++) {
            toppingsList += toppings.get(i).getName();
            if (i < toppings.size() - 1) {
                toppingsList += ", ";
            }
        }
        return toppingsList;
    }

    /**
     * This method provides a string of the Cake object.
     *
     * @return A formatted string containing the cake's details
     * @author Yin Ting Chan, Priscilla Wettlén
     */
    @Override
    public String toString() {
        //return super.getName() + ", Size: " + getCakeSize() + " pieces, Toppings: " + getToppingsList() + ", Price: " + calculatePrice() + " kr %n%n";
        return String.format(
                "%s%n  Size: %s%n  Toppings: %s%n  Price: %.2f kr ",
                super.getName(), cakeSize, getToppingsList(), calculatePrice());
    }
}