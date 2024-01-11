package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Restaurant.Hours;
import sophiatech.Restaurant.Restaurant;
import sophiatech.System;
import sophiatech.AppUsers.Customer;
import sophiatech.AppUsers.UserType;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RetrieveRestaurantHours {
    private Restaurant restaurant;
    private Customer customer;
    private System system = System.getInstance();

    @Given("a Customer") //TO CHANGE WITH CONTAINER
    public void a_customer() {
        customer = new Customer("Beurel","Simon", UserType.FACULTY);
    }
    @When("I want to check the operating hours of a restaurant")
    public void check_hours_restaurant() {
        Hours hours = new Hours(LocalTime.of(9,30), LocalTime.of(23,45));
        restaurant = new Restaurant("McDonalds", "Paris", hours, 3,5,5,20);
        system.addRestaurant(restaurant);
    }
    @Then("I retrieve the restaurant's hours")
    public void we_get_correct_hours() {
        assertEquals(this.customer.getHours(restaurant), restaurant.getHours());
    }
}
