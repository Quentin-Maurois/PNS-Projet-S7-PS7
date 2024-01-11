package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.AppUsers.CampusAdministrator;
import sophiatech.Restaurant.Restaurant;
import sophiatech.System;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteRestaurant {

    private CampusAdministrator campusAdministrator;
    private System system;
    private Restaurant restaurant;

    @Given("that I am an Campus Administrator viewing the list of restaurants")
    public void that_i_am_an_campus_administrator_viewing_the_list_of_restaurants() {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        campusAdministrator = new CampusAdministrator();
        restaurant = new Restaurant("Test Restaurant", "123 Test Street", null, 3,5,5,20);
        campusAdministrator.addRestaurant(restaurant);
    }
    @When("I choose to delete a specific restaurant")
    public void i_choose_to_delete_a_specific_restaurant() {
        campusAdministrator.deleteRestaurant(restaurant);
    }
    @Then("the chosen restaurant should be removed from the system's restaurant list")
    public void the_chosen_restaurant_should_be_removed_from_the_system_s_restaurant_list() {
        assertFalse(system.getListRestaurant().contains(restaurant));
    }

}
