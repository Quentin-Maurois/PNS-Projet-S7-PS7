package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.AppUsers.CampusAdministrator;
import sophiatech.Restaurant.Restaurant;
import sophiatech.System;

import static org.junit.Assert.assertTrue;

public class AddRestaurant {
    private CampusAdministrator campusAdministrator;
    private System system;
    private Restaurant restaurant;

    @Given("a CampusAdministrator")
    public void a_CampusAdministrator() {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();
        campusAdministrator = new CampusAdministrator();
    }
    @When("I add a new restaurant to the System")
    public void I_add_a_new_restaurant_to_the_System() {
        restaurant = new Restaurant("Le petit repas", "8 rue de la République", null, 3,5,5, 20);
        campusAdministrator.addRestaurant(restaurant);
    }
    @Then("There is a new restaurant in the System's restaurant list")
    public void There_is_a_new_restaurant_in_the_System_s_restaurant_list() {
        assertTrue(system.getListRestaurant().contains(restaurant));
    }
}
