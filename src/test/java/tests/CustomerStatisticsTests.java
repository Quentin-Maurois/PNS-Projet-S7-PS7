package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.AppUsers.Customer;
import sophiatech.AppUsers.UserType;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;
import sophiatech.Statistics.CustomerStatistics;
import sophiatech.System;
import static org.junit.Assert.*;


public class CustomerStatisticsTests {
    System system;
    Customer customer;
    Restaurant restaurant;
    Product product;
    Product product2;
    CustomerStatistics customerStatistics;

    @Given("A customer who never ordered")
    public void a_customer_who_never_ordered() {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        customer = new Customer("a", "b", UserType.EXTERNAL);
    }
    @When("they access their statistics page")
    public void they_access_their_statistics_page() {
        customerStatistics = customer.getStatistics();
    }
    @Then("they should see that the statistics are at zero")
    public void they_should_see_that_the_statistics_are_at_zero() {
        assertEquals(0, customerStatistics.getNumberOrders());
        assertEquals(0, customerStatistics.getTotalPriceSpent(), 0);
    }



    @Given("A customer who has successfully placed an order")
    public void a_customer_who_has_successfully_placed_an_order() {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        restaurant = new Restaurant("Tacos de Sophia", "33 rue du campus", null, 3, 5, 5, 20);
        product = new Product(this.restaurant, "tacos double viande", 14.5);

        customer = new Customer("a", "b", UserType.EXTERNAL);
    }
    @When("they pay for their order")
    public void they_pay_for_their_order() {
        customer.addProductToPendingOrder(product);
        customer.payForOrder();
        customerStatistics = customer.getStatistics();
    }
    @Then("in their statistics the price is changed accordingly")
    public void in_their_statistics_the_price_is_changed_accordingly() {
        assertEquals(14.5, customerStatistics.getTotalPriceSpent(), 0);
        assertEquals(14.5, customerStatistics.getAverageSpent(), 0);
    }
    @Then("in their statistics the number of orders is set to {int}")
    public void in_their_statistics_the_number_of_orders_is_set_to(Integer int1) {
        assertEquals(1, customerStatistics.getNumberOrders());
    }



    @Given("A customer with a history of one order")
    public void a_customer_with_a_history_of_one_order() {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        restaurant = new Restaurant("Tacos de Sophia", "33 rue du campus", null, 3, 5, 5, 20);
        product = new Product(this.restaurant, "tacos double viande", 14.5);
        product2 = new Product(this.restaurant, "grande fritte", 4);

        customer = new Customer("a", "b", UserType.EXTERNAL);

        customer.addProductToPendingOrder(product);
        customer.payForOrder();
    }
    @When("they pay for a new order")
    public void they_pay_for_a_new_order() {
        customer.addProductToPendingOrder(product2);
        customer.payForOrder();
        customerStatistics = customer.getStatistics();
    }
    @Then("in their statistics the price is aggregated accordingly")
    public void in_their_statistics_the_price_is_aggregated_accordingly() {
        assertEquals(14.5 + 4, customerStatistics.getTotalPriceSpent(), 0);
        assertEquals((14.5 + 4) / 2, customerStatistics.getAverageSpent(), 0);
    }
    @Then("in their statistic the number of orders is set to {int}")
    public void In_their_statistic_the_number_of_orders_is_set_to(Integer int2) {
        assertEquals(2, customerStatistics.getNumberOrders());
    }



    @Given("a customer with a discountV1 in a restaurant")
    public void a_customer_with_a_discount_v1_in_a_restaurant() {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        restaurant = new Restaurant("Tacos de Sophia", "33 rue du campus", null, 3, 5, 5, 20);
        restaurant.setDiscountV1(0.10);     //discount of 10%
        restaurant.setDiscountV1Requirement(0); //no orders are needed to get this discount

        product = new Product(this.restaurant, "tacos double viande", 14.5);

        customer = new Customer("a", "b", UserType.EXTERNAL);
    }
    @When("they pay for the order")
    public void they_pay_for_the_order() {
        customer.addProductToPendingOrder(product);
        customer.payForOrder();
        customerStatistics = customer.getStatistics();
    }
    @Then("the price in the stats is changed accordingly to the discountV1")
    public void the_price_in_the_stats_is_changed_accordingly_to_the_discount_v1() {
        assertEquals(14.5 - (14.5 * 0.10), customerStatistics.getTotalPriceSpent(), 0);
    }



    @Given("a customer who has a discount in the restaurant")
    public void a_customer_who_has_a_discount_in_the_restaurant() {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        restaurant = new Restaurant("Tacos de Sophia", "33 rue du campus", null, 3, 5, 5, 20);

        product = new Product(this.restaurant, "tacos double viande", 14.5);

        customer = new Customer("a", "b", UserType.EXTERNAL);
        customer.addDiscount(restaurant);
    }
    @When("they pay for this order")
    public void they_pay_for_this_order() {
        customer.addProductToPendingOrder(product);
        customer.payForOrder();
        customerStatistics = customer.getStatistics();
    }
    @Then("the price in the stats is changed accordingly to the discount")
    public void the_price_in_the_stats_is_changed_accordingly_to_the_discount() {
        assertEquals(14.5 - (14.5 * 0.05), customerStatistics.getTotalPriceSpent(), 0.01);
    }



    @Given("a customer who is a student")
    public void a_customer_who_is_a_student() {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        restaurant = new Restaurant("Tacos de Sophia", "33 rue du campus", null, 3, 5, 5, 20);

        product = new Product(this.restaurant, "tacos double viande", 14.5);

        customer = new Customer("a", "b", UserType.STUDENT);
    }
    @When("they pay for an order")
    public void they_pay_for_an_order() {
        customer.addProductToPendingOrder(product);
        customer.payForOrder();
        customerStatistics = customer.getStatistics();
    }
    @Then("the price in the stats is less according to the student discount")
    public void the_price_in_the_stats_is_less_according_to_the_student_discount() {
        assertEquals(14.5 - (14.5 * 0.05), customerStatistics.getTotalPriceSpent(), 0.01);
    }
}
