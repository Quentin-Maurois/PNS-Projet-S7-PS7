package tests;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import sophiatech.Order.GroupOrder;
import sophiatech.Order.Order;
import sophiatech.Order.OrderComponent;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;
import sophiatech.Restaurant.RestaurantProxy;

import java.lang.System;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SlotsRestaurant {
    private Restaurant restaurant;
    private RestaurantProxy proxy;
    @Given("a Restaurant called {string}")
    public void a_restaurant_called(String arg0) {
        restaurant = new Restaurant(arg0, "Biot", null, 0, 0 , 0, 0);
        proxy = new RestaurantProxy(restaurant);
    }

    @When("I specify my production's capacity")
    public void i_specify_my_production_s_capacity() {
        restaurant.editCapacity(1);
        //Now the restaurant got a capacity of 1 menus every 10 minutes
    }

    @Then("I cant accept more order than my production's capacity")
    public void i_cant_accept_more_order_than_my_production_s_capacity() {
        ArrayList<Product> pdlist = new ArrayList<>();
        pdlist.add(new Product(restaurant, "Pizza", 10));
        Order o1 = new Order("Langast", LocalTime.now(),pdlist);
        ArrayList<OrderComponent> list = new ArrayList<>();
        list.add(o1);

        GroupOrder gp = new GroupOrder(list);

        //We add a first order to our slot
        proxy.addOrder(gp);
        assertEquals(1, restaurant.getActiveOrders().size()); //Success because the restaurant cas make one order every 10 minutes

        proxy.addOrder(gp);
        assertNotEquals(2, restaurant.getActiveOrders().size()); //False because the restaurant's capacity is empty

    }
}
