package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;
import sophiatech.System;
import sophiatech.AppUsers.Customer;
import sophiatech.AppUsers.UserType;

import static org.junit.Assert.assertEquals;

public class NotificationRestaurant {
    private System system;
    private Restaurant restaurant;
    private Customer customer;
    @Given("a restaurant named {string} and a customer")
    public void aRestaurantNamedAndACustomer(String arg0) {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        restaurant = new Restaurant(arg0, "75 rue evariste galois", null, 3,5,5,20);
        customer = new Customer("Quentin", "Maurois", UserType.STUDENT);

        system.addRestaurant(restaurant);
    }

    @When("the customer makes a new order for {string}")
    public void theCustomerMakeANewOrderFor(String arg0) {
        Product product = new Product(restaurant, "Burger", 15);
        customer.addProductToPendingOrder(product);
        customer.payForOrder();
    }

    @Then("the restaurant receive a notification")
    public void theRestaurantReceiveANotification() {
        assertEquals(restaurant.getActiveOrders().size(),1);
    }
}
