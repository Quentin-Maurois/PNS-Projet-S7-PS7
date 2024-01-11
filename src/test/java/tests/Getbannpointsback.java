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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Getbannpointsback {
    private DeliveryPerson deliveryPerson;
    private Order order;
    private Customer customer;
    private ArrayList<Product> products;


    @Given("a customer who has already been signaled")
    public void a_customer_who_has_already_been_signaled() {
        deliveryPerson = new DeliveryPerson("Ivan","Ridier");
        customer = new Customer("Sara", "Dahman", UserType.STUDENT);
        products = new ArrayList<>();
        products.add(new Product("Tajine", 30));
        order = new Order(customer,"55 Avenue de Cannes", LocalTime.now(),products);
        deliveryPerson.assignOrder(order);
        deliveryPerson.observeUserDelay();
        customer.decrementerDelayCounter();//2
        deliveryPerson.reportUserDelay(order,customer);//1
    }

    @Given("the customer has not reached full ban points")
    public void the_customer_has_not_reached_full_ban_points() {
        assertTrue(customer.isActive());
    }

    @When("they order their third order in a row")
    public void they_order_their_third_order_in_a_row() {
        Order order1,order2,order3;
        order1 = new Order(customer,"55 Avenue de Cannes", LocalTime.now(),products);
        deliveryPerson.assignOrder(order1);
        deliveryPerson.validDelivery(order1);
        order2 = new Order(customer,"55 Avenue de Cannes", LocalTime.now(),products);
        deliveryPerson.assignOrder(order2);
        deliveryPerson.validDelivery(order2);
        order3 = new Order(customer,"55 Avenue de Cannes", LocalTime.now(),products);
        deliveryPerson.assignOrder(order3);
        deliveryPerson.validDelivery(order3);

        customer.checkNotDelayedPoints();
    }

    @Then("they get their bann points back")
    public void they_get_their_bann_points_back() {
        assertEquals(customer.getDelayCounter(),2);
    }


}
