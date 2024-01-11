package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;

import static org.junit.Assert.assertEquals;

public class editProductName {

    private Restaurant restaurant;
    private Product existingProduct;

    @Given("the restaurant owner has a product called {string}")
    public void the_restaurant_owner_has_a_product_called(String productName) {
        restaurant = new Restaurant("Le Bistro", "Nice", null, 0, 0, 0,20);
        existingProduct = new Product(restaurant, productName, 30);
        restaurant.addProductToMenu(existingProduct);
    }

    @When("he wants to edit the name of the product to {string}")
    public void he_wants_to_edit_the_name_of_the_product_to(String newProductName) {
        existingProduct.setName(newProductName);
    }

    @Then("the product name is changed to {string}")
    public void the_product_name_is_changed_to(String expectedProductName) {
        assertEquals(expectedProductName, existingProduct.getName());
    }


}
