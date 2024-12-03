package model;

public class PerUnitItem extends BakeryItem {

    public PerUnitItem(String name, double price) {
        super(name, price);
    }

    @Override
    public double calculatePrice() {
        return getPrice();
    }
}
