package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;

import static org.junit.Assert.assertEquals;
public class addProductRestaurant {
    Restaurant restaurant;
    Product product;
    @Given("a new restaurant called {string} based in {string}")
    public void aNewRestaurantWhichOpened(String arg0, String arg1) {
        restaurant = new Restaurant(arg0, arg1, null, 3,5,5, 20);
    }

    @When("I want to add a new product called {string} which cost 15 euros")
    public void iWantToAddANewProductCalled(String arg0) {
        product = new Product(restaurant, arg0, 15);
    }

    @Then("my product's list is correctly updated")
    public void myProductSListIsCorrectlyUpdated() {
        assertEquals(1, restaurant.getProducts().size());
    }
}
