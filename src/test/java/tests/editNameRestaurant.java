package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Restaurant.Restaurant;
import static org.junit.Assert.assertEquals;
public class editNameRestaurant {
    private Restaurant restaurant;
    private String new_name;
    @Given("a restaurant called {string} based in {string}")
    public void aRestaurantCalled(String arg0, String arg1) {
        restaurant = new Restaurant(arg0, arg1, null, 3,5,5,20);
    }

    @When("I want to edit my name with {string}")
    public void iWantToEditMyNameWith(String arg2) {
        restaurant.setName(arg2);
        new_name = arg2;
    }

    @Then("my new name is correctly changed")
    public void myNewNameIsCorrectlyChanged() {
        //System.out.println(new_name);
        assertEquals(new_name, restaurant.getName());
    }
}
