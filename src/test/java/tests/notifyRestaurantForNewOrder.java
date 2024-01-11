package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Order.GroupOrder;
import sophiatech.Order.Status;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;
import sophiatech.Restaurant.RestaurantEmployee;
import sophiatech.AppUsers.Customer;
import sophiatech.AppUsers.UserType;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class notifyRestaurantForNewOrder {

    private Customer customer;
    private Restaurant restaurant = new Restaurant("McDonalds", "Nice", null,3,5,5,20);
    private int sizeActiveOrder;
    private Product product;
    private RestaurantEmployee restaurantEmployee;
    private ArrayList<GroupOrder> activeOrdersList;

    @Given("a Customer ordering")
    public void that_i_am_a_customer() {
        customer =new Customer("Aziki", "Tarik", UserType.STAFF);
    }
    @When("they validate an order")
    public void i_validate_an_order() {

        sizeActiveOrder = restaurant.getActiveOrders().size();
        product = new Product(restaurant, "test burger", 7);    //adds product to the corresponding restaurant in the constructor
        customer.addProductToPendingOrder(product);
        customer.payForOrder();
    }
    @Then("the restaurantEmployee receives a notification")
    public void the_the_restaurant_employee_receives_a_notification() {
        assertEquals(sizeActiveOrder + 1, restaurant.getActiveOrders().size());
    }


    @Given("a restaurant employee")
    public void a_restaurant_employee() {
        customer =new Customer("Aziki", "Tarik",UserType.FACULTY);
        restaurantEmployee = new RestaurantEmployee("Quentin", "Maurua", false, restaurant);
    }
    @When("they access a restaurant's to-do list")
    public void they_access_a_restaurant_s_to_do_list() {
        sizeActiveOrder = restaurant.getActiveOrders().size();
        product = new Product(restaurant, "test burger", 7);    //adds product to the corresponding restaurant in the constructor
        customer.addProductToPendingOrder(product);
        customer.payForOrder();
        activeOrdersList=restaurantEmployee.getRestaurant().getActiveOrders();
    }
    @Then("they can see the customer's order")
    public void they_can_see_the_customer_s_order() {
        assertEquals(activeOrdersList.get(0).orders.get(0).getProductList().get(0), product);
    }
    @Then("they will confirm the receipt of the order")
    public void they_will_confirm_the_receipt_of_the_order() {
        assertEquals(activeOrdersList.get(0).orders.get(0).getStatus(), Status.IN_PREPARATION);
    }

}
