package sophiatech;

import sophiatech.AppUsers.DeliveryPerson;
import sophiatech.Order.GroupOrder;
import sophiatech.Restaurant.Restaurant;
import sophiatech.AppUsers.Customer;
import sophiatech.Services.PaymentService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class System {
    private static System instance; //stocks the only instance of the system

    private PaymentService paymentService;
    private ArrayList<Customer> listCustomer;
    private ArrayList<Restaurant> listRestaurant;
    private ArrayList<DeliveryPerson> listDeliveryPerson;

    private ArrayList<GroupOrder> ordersPendingDeliveryPersons;
    private ArrayList<GroupOrder> groupOrders;

    public ArrayList<GroupOrder> getOrdersPendingDeliveryPersons() {
        return ordersPendingDeliveryPersons;
    }

    private System(){
        this.listCustomer = new ArrayList<>();
        this.listRestaurant = new ArrayList<>();
        this.listDeliveryPerson = new ArrayList<>();
        this.paymentService = new PaymentService();
        this.ordersPendingDeliveryPersons = new ArrayList<>();
        this.groupOrders = new ArrayList<>();
    }

    public static System getInstance() {
        if (instance == null) {
            instance = new System();
        }
        return instance;
    }

    public void addCustomer(Customer c){
        this.listCustomer.add(c);
    }

    public void addRestaurant(Restaurant r){
        this.listRestaurant.add(r);
    }

    public void deleteRestaurant(Restaurant r) {
        Iterator<Restaurant> iterator = listRestaurant.iterator();
        while (iterator.hasNext()) {
            Restaurant restaurant = iterator.next();
            if (restaurant.equals(r)) {
                iterator.remove();
            }
        }
    }

    public void addDeliveryPerson(DeliveryPerson dp){
        this.listDeliveryPerson.add(dp);
    }

    public ArrayList<Customer> getListCustomer() {
        return listCustomer;
    }

    public ArrayList<Restaurant> getListRestaurant() {
        return listRestaurant;
    }

    public ArrayList<DeliveryPerson> getListDeliveryPerson() {
        return listDeliveryPerson;
    }
  
    public PaymentService getPaymentService() {
        return this.paymentService;
    }

    public ArrayList<DeliveryPerson> getAvailableDeliveryPerson() {
        ArrayList<DeliveryPerson> availableDeliveryPerson = new ArrayList<>();
        for (DeliveryPerson deliveryPerson : listDeliveryPerson) {
            if (deliveryPerson.getIsAvailable())
                availableDeliveryPerson.add(deliveryPerson);
        }

        //if there are orders pending for a delivery person, assigns the first one to the first available delivery person
        for (int i = 0; i < Math.min(ordersPendingDeliveryPersons.size(), availableDeliveryPerson.size()); i++) {
            availableDeliveryPerson.get(i).addOrder(ordersPendingDeliveryPersons.get(i));
            availableDeliveryPerson.remove(i);
            ordersPendingDeliveryPersons.remove(i);
        }

        return availableDeliveryPerson;
    }

    public void addOrderWithoutDeliveryPerson (GroupOrder groupOrder) {
        ordersPendingDeliveryPersons.add(groupOrder);
    }

    public GroupOrder getGroupOrder(int id) {
        for (GroupOrder groupOrder : groupOrders) {
            if (groupOrder.getId() == id) {
                return groupOrder;
            }
        }
        return null;
    }

    public void addGroupOrder(GroupOrder groupOrder) {
        this.groupOrders.add(groupOrder);
    }

    public ArrayList<GroupOrder> getListGroupOrders() {
        ArrayList<GroupOrder> openGroupOrders = new ArrayList<>();
        for (GroupOrder groupOrder : groupOrders) {
            if (groupOrder.isOpen())
                openGroupOrders.add(groupOrder);
        }
        return openGroupOrders;
    }


    public void removeDeliveryPerson(DeliveryPerson dp){ listDeliveryPerson.remove(dp);}
}