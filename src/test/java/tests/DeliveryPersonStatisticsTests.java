package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.AppUsers.DeliveryPerson;
import sophiatech.Order.GroupOrder;
import sophiatech.Order.Order;
import sophiatech.Restaurant.Product;
import sophiatech.Statistics.DeliveryPersonStatistics;
import sophiatech.System;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DeliveryPersonStatisticsTests {
    System system;
    DeliveryPerson deliveryPerson;
    DeliveryPersonStatistics statistics;


    @Given("a new delivery person")
    public void a_new_delivery_person() {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        deliveryPerson = new DeliveryPerson("", "");
    }

    @Given("a delivery person with an order of {double}")
    public void aDeliveryPersonWithAnOrderOf(double val) {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        deliveryPerson = new DeliveryPerson("", "");

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(null, "product", val));

        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order(null, "", LocalTime.now(), products));
        GroupOrder go = new GroupOrder();
        go.orders.addAll(orders);

        deliveryPerson.addOrder(go);
    }

    @Given("a delivery person who received three orders at {int} and one at {int}")
    public void a_delivery_person_who_received_three_orders_at_and_one_at(int hour1, int hour2) {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        deliveryPerson = new DeliveryPerson("", "");

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(null, "product", 10.50));

        // order1
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order(null, "", LocalTime.of(hour1, 0, 0), products));
        GroupOrder go = new GroupOrder();
        go.orders.addAll(orders);
        deliveryPerson.addOrder(go);

        // order2
        orders = new ArrayList<>();
        orders.add(new Order(null, "", LocalTime.of(hour1, 0, 0), products));
        go = new GroupOrder();
        go.orders.addAll(orders);
        deliveryPerson.addOrder(go);

        // order3
        orders = new ArrayList<>();
        orders.add(new Order(null, "", LocalTime.of(hour1, 0, 0), products));
        go = new GroupOrder();
        go.orders.addAll(orders);
        deliveryPerson.addOrder(go);

        // order4
        orders = new ArrayList<>();
        orders.add(new Order(null, "", LocalTime.of(hour2, 0, 0), products));
        go = new GroupOrder();
        go.orders.addAll(orders);
        deliveryPerson.addOrder(go);
    }

    @When("they check their statistics")
    public void they_check_their_statistics() {
        statistics = deliveryPerson.getStatistics();
    }
    @Then("the total number of orders is at {int}")
    public void the_total_number_of_orders_is_at(int nb) {
        assertEquals(nb, statistics.getNumberOrders());
    }
    @Then("the total price for their orders is at {double}")
    public void the_total_price_for_their_orders_is_at(double total) {
        assertEquals(total, statistics.getTotalPriceForOrders(), 0.0);
    }
    @Then("the average price for their orders is at {double}")
    public void the_average_price_for_their_orders_is_at(double total) {
        assertEquals(total, statistics.getAveragePriceForOrder(), 0.0);
    }
    @Then("the percentage of orders assigned at {int} is {double}")
    public void the_percentage_of_orders_assigned_at_is(int hour, double percentage) {
        assertEquals(percentage, statistics.getPercentageOrdersAtTime()[hour], 0.0);

    }
    @Then("the percentage of orders assigned at any time but {int} and {int} is {double}")
    public void the_percentage_of_orders_assigned_at_any_time_but_and_is(int hour1, int hour2, double percentage) {
        double[] percents = statistics.getPercentageOrdersAtTime();
        for (int i = 0; i < 24; i++) {
            if (!(i == hour1 || i == hour2)) {
                assertEquals(percentage, percents[i], 0);
            }

        }
    }
}
