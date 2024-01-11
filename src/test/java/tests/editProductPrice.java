package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class editProductPrice {

    private Restaurant restaurant;
    private Product productToEdit;

    @Given("the restaurant owner has a product called {string} with a price of {double} euros")
    public void the_restaurant_owner_has_a_product_called_with_a_price_of_euros(String productName, double initialPrice) {
        restaurant = new Restaurant("The Bistro", "Nice", null,  0 , 0 ,0,20);
        productToEdit = new Product(restaurant, productName, initialPrice);
        restaurant.addProductToMenu(productToEdit);
    }

    @When("he wants to edit the price of the product to {double} euros")
    public void he_wants_to_edit_the_price_of_the_product_to_euros(double newPrice) {
        productToEdit.setPrice(newPrice);
    }

    @Then("the product price is changed to {double} euros")
    public void the_product_price_is_changed_to_euros(double expectedPrice) {
        assertEquals(expectedPrice, productToEdit.getPrice());
    }
}
