package sophiatech.Order;

import sophiatech.AppUsers.Customer;
import sophiatech.Restaurant.Formule;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BuffetOrder extends OrderComponent {
    private Restaurant restaurant;

    private Customer universityStaff;
    private Customer recipientUser;
    private int numberOfPeople;
    private Formule formule;


    public BuffetOrder(Customer universityStaff, Customer recipientUser, int numberOfPeople, LocalTime hour, String location, Formule formule) {
        this.universityStaff = universityStaff;
        this.recipientUser = recipientUser;
        this.numberOfPeople = numberOfPeople;
        this.hour_order = hour;
        this.location = location;
        this.formule= formule;
        this.status = Status.PENDING_PREPARATION;
        this.validationByDeliveryPerson = false;
        this.validationByCustomer = false;
        this.isAlreadyUsedForDiscount = false;
        this.productList = new ArrayList<>();
        fillProductList();
        this.totalPrice = calculateTotalPrice();
        this.restaurant = productList.get(0).getRestaurant();
    }


    private void fillProductList(){
        for(Product p : formule.getProducts()){
            productList.add(p);
        }
    }

    @Override
    public double calculateTotalPrice() {
        double ttp=0.0;
        for (Product p : productList) {
            ttp += p.getPrice();
        }
        return ttp;
    }

    public Formule getFormule() {
        return formule;
    }

    @Override
    public Restaurant getRestaurant() {
        return this.restaurant;
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public HashSet<Restaurant> getRestaurants() {
        HashSet<Restaurant> restaurants = new HashSet<>();
        restaurants.add(restaurant);
        return restaurants;
    }

    @Override
    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public void validDelivery() {
        validateDelivery(Status.DELIVERED);
        changeStatusValidation(Status.DELIVERY_CONFIRMED);
    }







}
