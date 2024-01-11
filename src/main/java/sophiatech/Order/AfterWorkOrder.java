package sophiatech.Order;

import sophiatech.AppUsers.Customer;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/*
* AfterWorkOrder is an order made by one customer to one Restaurant
* The customer selects a list of products and tells the number of guests
* */
public class AfterWorkOrder extends OrderComponent {
    int nbGuests;

    public AfterWorkOrder(Customer customer, String location, LocalTime hour, ArrayList<Product> productList, int nbGuests) {
        this.customer = customer;
        this.location = location;
        this.hour_order = hour;
        this.productList = productList;
        this.nbGuests = nbGuests;

        this.calculateTotalPrice();
    }

    public int getNbGuests() {
        return this.nbGuests;
    }

    @Override
    public double calculateTotalPrice() {
        double res = 0.0;

        for (Product p : productList) {
            res += p.getPrice();
        }

        res = res * nbGuests;

        this.totalPrice = res;
        return res;
    }

    @Override
    public Restaurant getRestaurant() {
        return this.productList.get(0).getRestaurant();
    }

    @Override
    public HashSet<Restaurant> getRestaurants() {
        HashSet<Restaurant> r = new HashSet<>();
        r.add(this.getRestaurant());
        return r;
    }

    @Override
    public List<Product> getProductList() {
        return this.productList;
    }

    @Override
    public void validDelivery() {
        validateDelivery(Status.DELIVERED);
        changeStatusValidation(Status.DELIVERY_CONFIRMED);
    }
}
