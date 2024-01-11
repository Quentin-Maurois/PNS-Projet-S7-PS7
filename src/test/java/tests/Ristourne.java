package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Services.Discount;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;
import sophiatech.AppUsers.Customer;
import sophiatech.AppUsers.UserType;

import static org.junit.Assert.*;

public class Ristourne {
    private Customer customer;
    private Restaurant restaurant=new Restaurant("test restaurant", "test address", null,3,5,5,20);
    private Product product;

    @Given("a customer who has a discount in a restaurant")
    public void a_customer_who_has_a_discount_in_a_restaurant() {
        customer = new Customer("Beurel","Simon", UserType.EXTERNAL);
        customer.addDiscount(restaurant);

    }
    @When("they order in this restaurant")
    public void they_order_in_this_restaurant() {
        product = new Product(restaurant, "test burger", 7);
        customer.addProductToPendingOrder(product);
        customer.payForOrder();
    }
    @Then("the order is cheaper")
    public void the_order_is_cheaper() {
        assertNotEquals(customer.getHistory().get(0).orders.get(0).getTotalPrice(), product.getPrice() , 0);
        assertEquals(customer.getHistory().get(0).orders.get(0).getTotalPrice(), product.getPrice() - product.getPrice() * 0.05, 0);
    }

    @Given("a customer who misses a single order to get access to the discounts in a restaurant")
    public void a_customer_who_misses_a_single_order_to_get_access_to_the_discounts_in_a_restaurant() {
        customer = new Customer("Beurel","Simon", UserType.EXTERNAL);
        for(int i=0;i<4;i++){
            product = new Product(restaurant, "test burger", 7);
            customer.addProductToPendingOrder(product);
            customer.payForOrder();
        }
        //System.out.println("taille historique: "+customer.getHistory().size());
        //System.out.println("taille discountList: "+customer.getDiscounts().size());
    }
    @Then("the customer gets the discount in this restaurant \\(not for this order)")
    public void the_customer_gets_the_discount_in_this_restaurant_not_for_this_order() {
        assertTrue(customer.getDiscounts().size()==1);
    }

    @When("the discount end date is reached")
    public void the_discount_end_date_is_reached() {
        Discount d=customer.getDiscounts().get(0);
        //System.out.println("date expiration: "+d.getExpirationDate());
        d.setExpirationDate(d.getExpirationDate().minusDays(d.getRestaurant().getDiscountDuration()+1));
        //System.out.println("date expiration: "+d.getExpirationDate());
        product = new Product(restaurant, "test burger", 7);
        customer.addProductToPendingOrder(product);
        customer.payForOrder();
    }
    @Then("the customer does not get the discount in this restaurant")
    public void the_customer_does_not_get_the_discount_in_this_restaurant() {
        //System.out.println("taille discountList: "+customer.getDiscounts().size());
        assertTrue(customer.getDiscounts().size()==0);
        assertTrue(customer.getHistory().get(0).orders.get(0).getTotalPrice()==product.getPrice());
    }



}
