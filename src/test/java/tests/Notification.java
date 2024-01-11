package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.AppUsers.DeliveryPerson;
import sophiatech.Order.GroupOrder;
import sophiatech.Order.Order;
import sophiatech.Order.Status;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;
import sophiatech.Restaurant.RestaurantEmployee;
import sophiatech.AppUsers.Customer;
import sophiatech.AppUsers.UserType;


import static org.junit.Assert.*;

public class Notification {
    private DeliveryPerson deliveryPerson;

    private Customer customer;
    private Product product;
    private RestaurantEmployee restaurantEmployee;
    private sophiatech.System system = sophiatech.System.getInstance();
    private Restaurant restaurant;


    private Order order;
    private GroupOrder groupOrder;

    @Given("a delivery person")
    public void a_delivery_person() {
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        deliveryPerson = new DeliveryPerson("Lorenzo", "Froment");
        customer = new Customer("patate", "brocoli", UserType.STUDENT);
        restaurant = new Restaurant("au bon sushi", "3 rue du temple", null,3,5,5,20);
        product = new Product(restaurant, "carottes rappées", 5);
    }
    @When("the order is marked as ready")
    public void the_order_is_marked_as_ready() {

        String location = "polytech Nice Sophia, ... Biot";
//        Date date = new Date();
//        ArrayList<Product> productList = new ArrayList<Product>();
//        productList.add(product);
//
//        order = new Order(location, date, productList, customer);
//        groupOrder = new GroupOrder();
//        groupOrder.orders.add(order);


        customer.setFavouriteLocation(location);
        customer.addProductToPendingOrder(product);

        this.customer.payForOrder();
//        deliveryPerson.addOrder(groupOrder);


    }
    @Then("they are no longer available")
    public void they_recieve_a_notification() {
        assertFalse(deliveryPerson.getIsAvailable());
    }
    @Then("they are able to read informations like : the venue, the customer's name, the order's id....")
    public void they_are_able_to_read_informations_like_the_venue_the_customer_s_name_the_order_s_id() {
        //java.lang.System.out.println(deliveryPerson.getActiveOrder().orders.get(0));
        assertEquals(deliveryPerson.getActiveOrder().getId(), customer.getActiveOrder().getId());
        assertEquals(deliveryPerson.getActiveOrder().orders.get(0).getId(), customer.getActiveOrder().orders.get(0).getId());
        assertEquals(deliveryPerson.getActiveOrder().orders.get(0).getLocation(), customer.getActiveOrder().orders.get(0).getLocation());
        assertEquals(deliveryPerson.getActiveOrder().orders.get(0).getCustomer().getCustomerName(), customer.getActiveOrder().orders.get(0).getCustomer().getCustomerName());
    }

    @Given("a restaurantEmployee finishing an order")
    public void a_restaurant_employee_finishing_an_order() {
        Restaurant restaurant = new Restaurant("test restaurant", "restaurant location", null,3,5,5,20);
        product = new Product(restaurant, "test burger", 7);

        customer = new Customer("test", "customer",UserType.EXTERNAL);
        customer.addProductToPendingOrder(product);
        restaurantEmployee = new RestaurantEmployee("Beurel", "Simon", false, null);
        customer.payForOrder();
    }
    @When("they validate the end of the preparation of an order")
    public void they_validate_the_end_of_the_preparation_of_an_order() {
        restaurantEmployee.finishOrder(customer.getActiveOrder().orders.get(0));
    }
    @Then("the deliveryPerson receive a notification")
    public void the_delivery_person_receive_a_notification() {
        assertTrue(customer.getActiveOrder().orders.get(0).getStatus() == Status.PREPARED);
    }
}
