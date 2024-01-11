package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.AppUsers.CampusAdministrator;
import sophiatech.AppUsers.DeliveryPerson;
import sophiatech.System;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddDeliveryPerson {
    private CampusAdministrator CA ;
    private System system ;
    private System System ;
    private sophiatech.AppUsers.DeliveryPerson deliveryPerson;


    @Given("that I am a Campus Administrator")
    public void that_i_am_a_campus_administrator() {
        system = sophiatech.System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();
    }
    @When("I add a Delivery Person to the System")
    public void i_add_a_delivery_person_to_the_system() {
        deliveryPerson = new DeliveryPerson("Jean", "Dupont");
    }
    @Then("There is a new delivery person in the System's delivery person list")
    public void there_is_a_new_delivery_person_in_the_system_s_delivery_person_list() {
        assertEquals(1, system.getListDeliveryPerson().size());
        assertTrue(system.getListDeliveryPerson().contains(deliveryPerson));
    }

}
