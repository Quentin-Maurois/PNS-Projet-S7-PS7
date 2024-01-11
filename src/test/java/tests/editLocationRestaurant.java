package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Restaurant.Restaurant;

import static org.junit.Assert.assertEquals;
public class editLocationRestaurant {
    private Restaurant restaurant;

    @Given("a Restaurant called {string} based in {string}")
    public void aRestaurantBasedIn(String arg0, String arg1) {
        restaurant = new Restaurant(arg0, arg1, null, 3,5,5,20);
    }

    @When("I want to change my location for {string}")
    public void iWantToChangeMyLocationFor(String arg0) {
        this.restaurant.setLocation(arg0);
    }

    @Then("my new location is {string}")
    public void myNewLocationIs(String arg0) {
        assertEquals(restaurant.getLocation(), arg0);
    }
}
