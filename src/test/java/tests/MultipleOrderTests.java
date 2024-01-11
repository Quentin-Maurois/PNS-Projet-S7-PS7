package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.AppUsers.CampusAdministrator;
import sophiatech.AppUsers.Customer;
import sophiatech.AppUsers.UserType;
import sophiatech.Order.GroupOrder;
import sophiatech.Order.MultipleOrder;
import sophiatech.Order.OrderComponent;
import sophiatech.Restaurant.Hours;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;
import sophiatech.System;

import java.time.LocalTime;

import static org.junit.Assert.*;


public class MultipleOrderTests {
    System system;
    CampusAdministrator ca;
    Restaurant r1;
    Product p1r1;
    Product p2r1;
    Restaurant r2;
    Product p1r2;
    Product p2r2;
    Customer c;
    GroupOrder go;
    OrderComponent o;

    @Given("a customer who makes an order with products from multiple restaurants")
    public void a_customer_who_makes_an_order_with_products_from_multiple_restaurants() {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        ca = new CampusAdministrator();

        Hours hours = new Hours(LocalTime.of(9,30), LocalTime.of(23,45));
        r1 = new Restaurant("r1", "rue du park", hours, 5, 5, 5, 5);
        r2 = new Restaurant("r2", "avenue du campus", hours, 5, 5, 5, 5);
        ca.addRestaurant(r1);
        ca.addRestaurant(r2);

        p1r1 = new Product(r1, "potatoes", 5.55);
        p2r1 = new Product(r1, "coca-cola", 3.75);
        p1r2 = new Product(r2, "cheese burger", 7.35);
        p2r2 = new Product(r2, "nuggets", 4.50);

        c = new Customer("Madeline", "Badeline", UserType.EXTERNAL);
        c.addProductToPendingOrder(p1r1);
        c.addProductToPendingOrder(p2r1);
        c.addProductToPendingOrder(p1r2);
        c.addProductToPendingOrder(p2r2);




    }
    @When("they pay for their new order")
    public void they_pay_for_their_new_order() {
        c.payForOrder("polytech, batiment A",null);
        go = c.getHistory().get(0);
        o = go.orders.get(0);
    }
    @Then("the order is an instance of MultipleOrder")
    public void the_order_is_an_instance_of_multiple_order() {
        assertEquals(MultipleOrder.class, o.getClass());
        assertEquals(1, go.orders.size());
    }
    @Then("all the restaurants have received the order")
    public void all_the_restaurants_have_received_the_order() {
        assertTrue(r1.getActiveOrders().contains(go));
        assertTrue(r2.getActiveOrders().contains(go));
    }
    @Then("the total price for each restaurant is coherent")
    public void the_total_price_for_each_restaurant_is_coherent() {
        MultipleOrder mo = (MultipleOrder) o;
        assertEquals(p1r1.getPrice() + p2r1.getPrice(), mo.getPriceForRestaurants().get(r1), 0.0);
        assertEquals(p1r2.getPrice() + p2r2.getPrice(), mo.getPriceForRestaurants().get(r2), 0.0);
    }
}
