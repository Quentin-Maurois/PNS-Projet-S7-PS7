package sophiatech.Order;

import sophiatech.AppUsers.Customer;
import sophiatech.Restaurant.Formule;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;

import java.time.LocalTime;
import java.util.ArrayList;

public class OrderFactory {

    public static OrderComponent createOrder (Customer customer, Customer recipientUser, String location, LocalTime hour, ArrayList<Product> productList, int nbGuests) {
        if (nbGuests < 1)   return null;    //cannot serve a negative number of persons
        if(nbGuests > 1 && customer != null && recipientUser != null){
            Formule formule = new Formule(productList);
            return new BuffetOrder(customer, recipientUser, nbGuests, hour, location, formule);
        }
      
        if (nbGuests > 1)   return new AfterWorkOrder(customer, location, hour, productList, nbGuests);

        Restaurant r = productList.get(0).getRestaurant();
        for (Product p : productList) {
            if (p.getRestaurant() != r) {
                return new MultipleOrder(customer, location, hour, productList);
            }
        }
        return new Order(customer, location, hour, productList);
    }

    public static GroupOrder createGroupOrder (ArrayList<OrderComponent> orders) {
        return new GroupOrder(orders);
    }
}
