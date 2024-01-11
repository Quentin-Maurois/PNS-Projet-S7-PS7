package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.AppUsers.CampusAdministrator;
import sophiatech.AppUsers.DeliveryPerson;
import sophiatech.Order.GroupOrder;
import sophiatech.Order.OrderComponent;
import sophiatech.Restaurant.Hours;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;
import sophiatech.System;
import sophiatech.AppUsers.Customer;
import sophiatech.AppUsers.UserType;

import java.time.LocalTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GroupOrderTests {
    System system;
    Customer customer1;
    Customer customer2;
    CampusAdministrator campusAdministrator;
    DeliveryPerson deliveryPerson;
    Hours hours;
    Restaurant restaurant;
    Product product1;
    Product product2;
    int id;
    @Given("a customer who payed for their order")
    public void a_customer_who_payed_for_their_order() {
        this.system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();


        this.campusAdministrator = new CampusAdministrator();
        this.hours = new Hours(LocalTime.of(9,30), LocalTime.of(23,45));
        this.restaurant = new Restaurant("test restaurant", "test location", hours, 3,5,5,20);
        this.campusAdministrator.addRestaurant(restaurant);

        this.product1 = new Product(restaurant, "test product", 10);
        this.product2 = new Product(restaurant, "burger", 12);

        this.deliveryPerson = new DeliveryPerson("delivery name", "delivery person");

        this.customer1 = new Customer("test first name", "test last name",UserType.EXTERNAL);
        this.customer1.addProductToPendingOrder(product1);
        this.customer1.payForOrder();

        this.customer2 = new Customer("customer 2", "customer 2 name", UserType.EXTERNAL);
        this.customer2.addProductToPendingOrder(this.product2);
        this.customer2.payForOrder();
    }
    @When("they want to create a group order")
    public void they_want_to_create_a_group_order() {
        this.id = customer1.allowGroupOrder().getId();
    }
    @Then("a new group order is created")
    public void a_new_group_order_is_created() {
        assertTrue(system.getListGroupOrders().size() > 0);
    }
    @When("The second customer want to join the group order")
    public void The_second_customer_want_to_join_the_group_order() {
        GroupOrder groupOrder = this.system.getGroupOrder(this.id);
        groupOrder.orders.add(this.customer2.getActiveOrder().orders.get(0));
    }
    @Then("their order is added to the group order")
    public void their_order_is_added_to_the_group_order() {
        GroupOrder groupOrder = system.getGroupOrder(this.id);
        assertTrue(groupOrder.orders.contains(this.customer2.getActiveOrder().orders.get(0)));
    }
    @When("A delivery person gets the notification to pick an order up")
    public void A_delivery_person_gets_the_notification_to_pick_an_order_up() {
        //no tests for notifications yet
    }
    @Then("They need to know all the individial orders")
    public void they_need_to_know_all_the_individual_orders() {
        GroupOrder activeOrder = this.deliveryPerson.getActiveOrder();

        //java.lang.System.out.println(activeOrder.orders.size());
        for (OrderComponent order : activeOrder.orders) {
            //java.lang.System.out.println(order);
        }
        assertTrue(activeOrder.orders.contains(customer1.getActiveOrder().orders.get(0)));
        assertTrue(activeOrder.orders.contains(customer2.getActiveOrder().orders.get(0)));
    }
}
