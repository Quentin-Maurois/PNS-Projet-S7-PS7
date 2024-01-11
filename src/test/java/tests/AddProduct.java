package tests;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;
import sophiatech.AppUsers.Customer;
import sophiatech.AppUsers.UserType;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddProduct {
    private Product product;
    private Customer customer;
    private Restaurant restaurant;

    @Given("a customer")
    public void a_customer() {
        customer = new Customer("Beurel","Simon", UserType.STUDENT);
        restaurant = new Restaurant("test restaurant", "test address", null,3,5,5, 20);
        product = new Product(restaurant, "test burger", 7);
    }
    @When("they add a product to their pendingOrder")
    public void add_product_to_my_order() {
        customer.addProductToPendingOrder(product);
    }
    @Then("the product is stored in their pendingOrder")
    public void product_is_correctly_stored() {
        assertTrue(customer.getSizePendingOrder()==1);
    }
}