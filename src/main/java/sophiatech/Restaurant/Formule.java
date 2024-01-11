package sophiatech.Restaurant;

import java.text.Normalizer;
import java.util.List;

public class Formule {
    Restaurant restaurant;
    String name;
    double price;
    private List<Product> products;
    private int maxCapacity;


    public Formule(Restaurant restaurant, String name, double price, List<Product> products, int maxCapacity) {
        this.name = name;
        this.restaurant = restaurant;
        this.price = price;
        this.products = products;
        this.maxCapacity = maxCapacity;
    }
    public Formule(List<Product> products) {
        this.restaurant = products.get(0).getRestaurant();
        this.name = "";
        this.price = 0.0;

        for (Product p : products) {
            this.name = this.name + p.getName();
            this.price = this.price + p.getPrice();
        }

        this.products = products;
        this.maxCapacity = products.size();
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getName () {
        return this.name;
    }

    public double getPrice () {
        return this.price;
    }
}
