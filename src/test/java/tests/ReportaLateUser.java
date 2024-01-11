package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.AppUsers.DeliveryPerson;
import sophiatech.Order.Order;
import sophiatech.AppUsers.Customer;
import sophiatech.AppUsers.UserType;
import sophiatech.Restaurant.Product;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReportaLateUser {
    private DeliveryPerson deliveryPerson;
    private Order order;
    private Customer customer;
    private ArrayList<Product> products;

    @Given("a delivery person observes a delay by a user for an order")
    public void a_delivery_person_observes_a_delay_by_a_user_for_an_order() {
        deliveryPerson = new DeliveryPerson("Ivan","Ridier");
        customer = new Customer("Sara", "Dahman", UserType.STUDENT);
        products = new ArrayList<>();
        products.add(new Product("Tajine", 30));
        order = new Order(customer,"55 Avenue de Cannes", LocalTime.now(),products);
        deliveryPerson.assignOrder(order);
        customer.decrementerDelayCounter();
        customer.decrementerDelayCounter();
        deliveryPerson.observeUserDelay();

    }
    @When("the delivery person reports the delay in the system and the delay counter of the customer has reached 0pts")
    public void the_delivery_person_reports_the_delay_in_the_system_and_the_delay_counter_of_the_customer_has_reached_0pts() {
        deliveryPerson.reportUserDelay(order,customer);

    }

    @Then("customer get banned.")
    public void customer_get_banned() {
        assertFalse(customer.isActive());
    }


    @Given("aaa delivery person observes a delay by a user for an order")
    public void aaa_delivery_person_observes_a_delay_by_a_user_for_an_order() {
        deliveryPerson = new DeliveryPerson("Ivan","Ridier");
        customer = new Customer("Sara", "Dahman",UserType.STUDENT);
        products = new ArrayList<>();
        products.add(new Product("Tajine", 30));
        order = new Order(customer,"55 Avenue de Cannes", LocalTime.now(),products);
        deliveryPerson.assignOrder(order);
        customer.decrementerDelayCounter();
        deliveryPerson.observeUserDelay();
    }


    @When("the delivery person reports the delay in the system, and the delay counter of the customer has not reached 0pts yet")
    public void the_delivery_person_reports_the_delay_in_the_system_and_the_delay_counter_of_the_customer_has_not_reached_0pts_yet() {
        deliveryPerson.reportUserDelay(order,customer);
    }

    @Then("the the delay counter is decremented by one point.")
    public void the_the_delay_counter_is_decremented_by_one_point() {
        assertTrue(customer.isActive());
        assertEquals(customer.getDelayCounter(),1);
    }




}
