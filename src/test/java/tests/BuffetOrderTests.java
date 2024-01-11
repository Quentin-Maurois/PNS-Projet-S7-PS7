package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import sophiatech.AppUsers.*;
import sophiatech.Order.*;
import sophiatech.Restaurant.Formule;
import sophiatech.Restaurant.Hours;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;
import sophiatech.System;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BuffetOrderTests {

    System system;
    CampusAdministrator ca;

    private Customer universityStaff;
    private Customer recipientUser;
    private int numberOfPeople;
    private BuffetOrder bo;
    private GroupOrder go;
    private Restaurant restaurant;
    private Formule formule;

    private ArrayList<Product> productList = new ArrayList<>();
    private DeliveryPerson deliveryPerson;

    @Given("a member of the university staff initiating a buffet order")
    public void a_member_of_the_university_staff_initiating_a_buffet_order() {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        ca = new CampusAdministrator();

        Hours hours = new Hours(LocalTime.of(9,30), LocalTime.of(23,45));
        restaurant = new Restaurant("r1", "rue du park", hours, 5, 5, 5, 5);
        ca.addRestaurant(restaurant);

        recipientUser = new Customer("Jean", "Dujardin", UserType.STAFF);
        universityStaff = new Customer("Michel", "Legrand", UserType.STAFF);
        productList.add(new Product(restaurant, "p1", 10));
        productList.add(new Product(restaurant, "p2", 15));
        productList.add(new Product(restaurant, "p3", 20));
        productList.add(new Product(restaurant, "p4", 18));
        productList.add(new Product(restaurant, "p5", 17));
        formule= new Formule(restaurant, "f1", 50, productList, 5);


      //  bo = new BuffetOrder(universityStaff, recipientUser, 5, null, null, formule);


        universityStaff.addFormuleToPendingOrder(formule);

    }

    @When("they pay for the new order")
    public void they_pay_for_the_new_order() {

        universityStaff.payForOrder("rue des patates", 2, recipientUser);
        go = universityStaff.getActiveOrder();
        bo = (BuffetOrder) universityStaff.getActiveOrder().orders.get(0);
        // universityStaff.addBuffetOrder(bo);

        deliveryPerson = new DeliveryPerson("Jean", "Dujardin");
    //   deliveryPerson.assignOrder(bo);

    }

    @Then("the order is an instance of BuffetOrder")
    public void the_order_is_an_instance_of_buffet_order() {
        assertEquals(BuffetOrder.class, bo.getClass());
    }

    @Then("the restaurants have received the order")
    public void the_restaurants_have_received_the_order() {
        assertTrue(restaurant.getActiveOrders().contains(go));
        assertTrue(restaurant.getOrderHistory().contains(go));
    }

    @Then("The maximum number of attendees for the event is equal to the capacity of the formula")
    public void the_maximum_number_of_attendees_for_the_event_is_equal_to_the_capacity_of_the_formula() {
        BuffetOrder buffetOrder = (BuffetOrder) bo; // Assuming 'bo' is an instance of BuffetOrder

        int formulaCapacity = buffetOrder.getFormule().getMaxCapacity();
        assertEquals(formulaCapacity, 5);
    }

}
