package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Order.GroupOrder;
import sophiatech.Order.Order;
import sophiatech.Restaurant.Product;
import sophiatech.System;
import sophiatech.AppUsers.Customer;
import sophiatech.AppUsers.UserType;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckHistory {

    private System system;
    private Customer customer;
    private Order order;
    private GroupOrder groupOrder;
    private Order order2;
    private GroupOrder groupOrder2;

    @Given("A customer")
    public void a_customer() {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        customer = new Customer("Simon", "Beurel", UserType.STUDENT);
    }
    @When("I view my order history for food orders")
    public void view_history_food_orders() {

        order = new Order(customer,"Lausanne", LocalTime.now(), new ArrayList<Product>());
        groupOrder = new GroupOrder();
        groupOrder.orders.add(order);
        order2= new Order(customer,"Le petit manger", LocalTime.now(), new ArrayList<Product>());
        groupOrder2 = new GroupOrder();
        groupOrder2.orders.add(order2);
        ArrayList<GroupOrder> orderHistory = new ArrayList<>();
        orderHistory.add(groupOrder);
        orderHistory.add(groupOrder2);
        customer.setHistory(orderHistory);
    }
    @Then("I can keep track of my past orders and preferences")
    public void product_is_correctly_stored() {
        assertTrue(customer.getOrderAtIndexHistory(0).orders.get(0)==order);
        assertFalse(customer.getOrderAtIndexHistory(0).orders.get(0).getLocation().equals("Le petit manger"));
    }
}
