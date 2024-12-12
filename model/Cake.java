package model;
import java.util.List;

public class Cake extends Item {
    private int cakeSize;
    private List<Topping> toppings;

    public Cake(String name, int cakeSize, List<Topping> toppings) {
        super(name, 0);
        this.cakeSize = cakeSize;
        this.toppings = toppings;
    }

    public int getCakeSize() {
        return cakeSize;
    }

    public void setCakeSize(int cakeSize) {
        this.cakeSize = cakeSize;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

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

    @Override
    public String toString() {
        //return super.getName() + ", Size: " + getCakeSize() + " pieces, Toppings: " + getToppingsList() + ", Price: " + calculatePrice() + " kr %n%n";
        return String.format(
                "%s%n  Size: %s%n  Toppings: %s%n  Price: %.2f kr ",
                super.getName(), cakeSize, getToppingsList(), calculatePrice());
    }
}