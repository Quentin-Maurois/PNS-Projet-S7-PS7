package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Restaurant.Hours;
import sophiatech.Restaurant.Restaurant;

import java.time.LocalTime;

import static org.junit.Assert.*;

public class editHoursRestaurant {
    Restaurant restaurant;
    Hours hours;
    @Given("a restaurant called {string}")
    public void aRestaurantCalled(String arg0) {
        hours = new Hours(LocalTime.of(9,30), LocalTime.of(23,45));
        restaurant = new Restaurant("Au bon manger", "Paris", hours, 3,5,5,20);
    }

    @When("I want to change my hours")
    public void iWantToChangeMyHours() {
        hours = new Hours(LocalTime.of(10,30), LocalTime.of(00,45));
        restaurant.setHours(hours);
    }

    @Then("My new hours are all changed")
    public void myNewHoursAreAllChanged() {
        assertEquals(restaurant.getHours(), hours);
    }
}
