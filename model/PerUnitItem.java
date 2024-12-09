package model;

public class PerUnitItem extends Item {

    public PerUnitItem(String name, double price) {
        super(name, price);
    }

    @Override
    public double calculatePrice() {
        return getPrice();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}