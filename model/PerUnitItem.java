package model;

/**
 * The PerUnitItem class represents an item priced per unit. This class extends the Item class
 * and inherits its properties and behaviours, while providing specific
 * implementation for items sold per unit.
 * Its responsibilities include calculating the price for a single unit of the item
 * and providing a string of the item.
 *
 * @author Priscilla Wettlén
 */
public class PerUnitItem extends Item {

    /**
     * This method constructs a PerUnitItem with the specified name and price.
     *
     * @param name  The name of the item
     * @param price The price per unit of the item
     * @author Priscilla Wettlén
     */
    public PerUnitItem(String name, double price) {
        super(name, price);
    }

    /**
     * This method calculates the price of the item.
     *
     * @return The price of the item as a double
     * @author Priscilla Wettlén
     */
    @Override
    public double calculatePrice() {
        return getPrice();
    }

    /**
     * This method returns a string of the PerUnitItem. This implementation
     * delegates to the toString method of the superclass.
     *
     * @return A string of the item, including its name and price
     * @author Priscilla Wettlén
     */
    @Override
    public String toString() {
        return super.toString();
    }
}