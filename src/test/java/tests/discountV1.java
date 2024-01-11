package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.AppUsers.CampusAdministrator;
import sophiatech.AppUsers.DeliveryPerson;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;
import sophiatech.System;
import sophiatech.AppUsers.Customer;
import sophiatech.AppUsers.UserType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class discountV1 {

    System system;
    CampusAdministrator campusAdministrator;
    Customer customer;
    Restaurant restaurant;
    Product product;


    @Given("a customer who has a discountV1 in a restaurant")
    public void a_customer_who_has_a_discount_v1_in_a_restaurant() {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        restaurant = new Restaurant("test restaurant", "restaurant location", null, 3,5,5,20);
        restaurant.setDiscountV1(0.10);     //10% of discount
        restaurant.setDiscountV1Requirement(0); //no order is needed to get the discount

        campusAdministrator = new CampusAdministrator();
        campusAdministrator.addRestaurant(restaurant);

        product = new Product(restaurant, "potatoes", 4.5);

        customer = new Customer("test", "customer", UserType.EXTERNAL); //needs to be external to get no other discount
        customer.addProductToPendingOrder(product);

        new DeliveryPerson("test", "delivery person");  //adds delivery person to the system in the constructor
    }

    @Given("a customer who does not have access to the discountsV1 in a restaurant")
    public void a_customer_who_does_not_have_access_to_the_discounts_v1_in_a_restaurant() {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        restaurant = new Restaurant("test restaurant", "restaurant location", null, 3,5,5,20);
        restaurant.setDiscountV1(0.10);     //10% of discount
        restaurant.setDiscountV1Requirement(10); //10 orders are needed to get the discount

        campusAdministrator = new CampusAdministrator();
        campusAdministrator.addRestaurant(restaurant);

        product = new Product(restaurant, "potatoes", 4.5);

        customer = new Customer("test", "customer", UserType.EXTERNAL); //needs to be external to get no other discount
        customer.addProductToPendingOrder(product);

        new DeliveryPerson("test", "delivery person");  //adds delivery person to the system in the constructor
    }

    @When("they order in the restaurant")
    public void theyOrderInTheRestaurant() {
        this.customer.payForOrder();
    }

    @Then("their order is cheaper")
    public void theirOrderIsCheaper() {
        assertEquals(4.05, this.customer.getActiveOrder().orders.get(0).getTotalPrice(), 0.0);
    }

    @Then("the customer does not get any discount")
    public void theCustomerDoesNotGetAnyDiscount() {
        assertEquals(4.5, this.customer.getActiveOrder().orders.get(0).getTotalPrice(), 0.0);    //4.5 is the price of the product
    }
}
