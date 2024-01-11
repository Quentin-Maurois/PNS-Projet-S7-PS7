package sophiatech.Order;

import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GroupOrder extends OrderComponent {

    public ArrayList<OrderComponent> orders = new ArrayList();

    private static int idCounter = 1; // Static counter for generating unique IDs
    private boolean isOpen;


    public GroupOrder(ArrayList<OrderComponent> orders) {
        this.id = generateUniqueId();
        this.orders = orders;
        this.isOpen = false;
        calculateTotalPrice();

        if (!orders.isEmpty()) this.hour_order = orders.get(0).getHour();
        else this.hour_order = null;
    }

    public GroupOrder() {
        this(new ArrayList<>());
        this.hour_order = LocalTime.now();
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    private synchronized int generateUniqueId() {
        return idCounter++;
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (OrderComponent o : orders) {
            total += o.calculateTotalPrice();
        }
        return total;
    }

    @Override
    public Restaurant getRestaurant() {
    return orders.get(0).getRestaurant();
    }

    @Override
    public HashSet<Restaurant> getRestaurants() {
        HashSet<Restaurant> restaurants = new HashSet<>();

        // Get all the restaurants from all the orders
        for (OrderComponent o : orders) {
            for (Restaurant r : o.getRestaurants()) {
                if (!restaurants.contains(r)) {
                    restaurants.add(r);
                }
            }
        }

        return restaurants;
    }

    @Override
    public ArrayList<Product> getProductList() {
        ArrayList<Product> products = new ArrayList<>();
        for (OrderComponent order : orders) {
            products.addAll(order.getProductList());
        }
        return products;
    }

    @Override
    public void validDelivery() {
        for (OrderComponent order : orders) {
            order.validateDelivery(Status.DELIVERED);
            order.changeStatusValidation(Status.DELIVERY_CONFIRMED);
        }

    }

}
