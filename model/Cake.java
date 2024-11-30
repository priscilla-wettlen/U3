package model;
import java.util.List;

public class Cake extends BakeryItem {
    private int size;
    private List<Topping> toppings;

    public Cake(String name, int size, List<Topping> toppings) {
        super(name, 0);
        this.size = size;
        this.toppings = toppings;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

        switch (size) {
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

    @Override
    public String toString() {
        StringBuilder toppingsList = new StringBuilder();
        for (int i = 0; i < toppings.size(); i++) {
            toppingsList.append(toppings.get(i).getName());
            if (i < toppings.size() - 1) {
                toppingsList.append(", ");
            }
        }
        return super.getName() + ", Size: " + size + " pieces, Toppings: " + toppingsList + ", Price: " + calculatePrice();
    }
}