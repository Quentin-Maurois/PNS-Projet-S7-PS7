package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Restaurant.Hours;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;

import java.time.LocalTime;

import static org.junit.Assert.assertFalse;

public class DeleteProdFromMenu {
    private Restaurant restaurant;
    private Product productToRemove;

    @Given("the restaurant owner wants to remove a product from the menu")
    public void the_restaurant_owner_wants_to_remove_a_product_from_the_menu() {
        restaurant = new Restaurant("Calade Rooftop", "Nice", new Hours(LocalTime.of(9, 0),LocalTime.of(17, 0)), 0 ,0 ,0,20);
        productToRemove = new Product(restaurant, "Couscous", 10.99);
        restaurant.addProductToMenu(productToRemove);

    }
    @When("they select the deletion option for that product")
    public void they_select_the_deletion_option_for_that_product() {
        restaurant.removeProductFromMenu(productToRemove);
    }
    @Then("the product is removed from the menu")
    public void the_product_is_removed_from_the_menu() {
        assertFalse(restaurant.getMenu().contains(productToRemove));
    }

}
