package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import sophiatech.AppUsers.CampusAdministrator;
import sophiatech.AppUsers.Customer;
import sophiatech.AppUsers.UserType;
import sophiatech.Order.GroupOrder;
import sophiatech.Order.OrderComponent;
import sophiatech.Restaurant.Hours;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;
import sophiatech.System;

import java.time.LocalTime;

import static org.junit.Assert.*;

public class AfterWorkOrderTests {
    System system;
    CampusAdministrator ca;
    Restaurant r;
    Product p;
    Customer c;
    int nbGuests;
    GroupOrder go;

    @Given("an external customer who wants to make an after work order for {int} person with a serving of pasta at {double} € for each guest")
    public void an_external_customer_who_wants_to_make_an_after_work_order_for_person_with_a_serving_of_pasta_at_€_for_each_guest(int nbPersons, double priceForProduct) {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        ca = new CampusAdministrator();

        Hours hours = new Hours(LocalTime.of(9,30), LocalTime.of(23,45));
        r = new Restaurant("Al Dente Tentation", "rue des tagliatelles", hours, 5, 5, 5, 10);
        p = new Product(r, "Spaghetti bowl", priceForProduct);

        ca.addRestaurant(r);

        c = new Customer("After Work", "Organiser", UserType.EXTERNAL);
        c.addProductToPendingOrder(p);

        nbGuests = nbPersons;


    }
    @When("they pay for their after work order")
    public void they_pay_for_their_after_work_order() {
        c.payForOrder("Rue de la companie", nbGuests);
        go = c.getActiveOrder();
    }
    @Then("the order is assigned to the customer")
    public void the_order_is_assigned_to_the_customer() {
        assertEquals(1, c.getActiveOrder().orders.size());
    }
    @Then("the order is assigned to the restaurant")
    public void the_order_is_assigned_to_the_restaurant() {
        assertTrue(r.getActiveOrders().contains(go));
    }
    @Then("the total price for the order is {double}")
    public void the_total_price_for_the_order_is(double val) {
        assertEquals(val, go.orders.get(0).getTotalPrice(), 0.0);

    }
}
