package sophiatech.Order;

import sophiatech.AppUsers.Customer;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;

import java.time.LocalTime;
import java.util.*;


/*
* MultipleOrder
* Order with a single Customer and multiple Restaurants
*/
public class MultipleOrder extends OrderComponent {
    private HashSet<Restaurant> restaurants;
    private Map<Restaurant, Double> priceForRestaurants;


    public MultipleOrder (Customer customer, String location, LocalTime hour, ArrayList<Product> productList) {
        this.customer = customer;
        this.location = location;
        this.hour_order = hour;
        this.productList = productList;


        restaurants = getAllRestaurants();
        priceForRestaurants = calculatePriceForRestaurants();
        calculateTotalPrice();
    }

    public Map<Restaurant, Double> getPriceForRestaurants() {
        return this.priceForRestaurants;
    }
    @Override
    public double calculateTotalPrice() {
        double total = 0.0;

        for (Restaurant key : priceForRestaurants.keySet()) {
                total += priceForRestaurants.get(key);
        }

        this.totalPrice = total;
        return total;
    }

    @Override
    public HashSet<Restaurant> getRestaurants () {
        return this.restaurants;
    }

    @Override
    public Restaurant getRestaurant() { //can not get a single restaurant
        return null;
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


    private HashSet<Restaurant> getAllRestaurants() {
        HashSet<Restaurant> allRestaurants = new HashSet<>();

        for (Product p : productList) {
            if (! allRestaurants.contains(p.getRestaurant()))
                allRestaurants.add(p.getRestaurant());
        }

        return allRestaurants;
    }

    private Map<Restaurant, Double> calculatePriceForRestaurants() {
        Map<Restaurant, Double> allPricesForRestaurants = new HashMap<>();
        for (Product p : productList) {
            if (!allPricesForRestaurants.containsKey(p.getRestaurant())) {
                allPricesForRestaurants.put(p.getRestaurant(), p.getPrice());
            } else {
                allPricesForRestaurants.put(p.getRestaurant(), p.getPrice() + allPricesForRestaurants.get(p.getRestaurant()));
            }
        }

        return allPricesForRestaurants;
    }
}
