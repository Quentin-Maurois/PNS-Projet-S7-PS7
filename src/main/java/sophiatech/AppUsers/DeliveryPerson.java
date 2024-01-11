package sophiatech.AppUsers;
import sophiatech.Order.GroupOrder;
import sophiatech.Order.Order;
import sophiatech.Order.OrderComponent;
import sophiatech.Statistics.DeliveryPersonStatistics;
import sophiatech.System;

import java.util.ArrayList;

public class DeliveryPerson {

    private System system;
    private DeliveryPersonStatistics statistics;

    public String getFirstName() {
        return firstName;
    }

    private String firstName;
    private String lastName;
    private GroupOrder activeOrder;
    private ArrayList<GroupOrder> orderHistory;
    private boolean isAvailable;
    private Order currentOrder;

    @Override
    public boolean equals(Object obj){
        if(obj instanceof DeliveryPerson){
            DeliveryPerson dp = (DeliveryPerson) obj;
            return dp.firstName.equals(firstName) && dp.lastName.equals(lastName);
        }
        return false;
    }

    public DeliveryPerson(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.activeOrder = new GroupOrder();
        this.orderHistory = new ArrayList<>();
        this.isAvailable = true;
        this.system = System.getInstance();
        this.system.addDeliveryPerson(this);
        this.statistics = new DeliveryPersonStatistics(this);
    }
    public void addOrder(GroupOrder o){
        this.orderHistory.add(o);
        this.activeOrder = o;
        this.isAvailable = false;
        this.statistics.addGroupOrder(o);
    }

    public DeliveryPersonStatistics getStatistics() {
        return this.statistics;
    }

    public boolean getIsAvailable(){
        return isAvailable;
    }

    public GroupOrder getActiveOrder() {
        return this.activeOrder;
    }


    public void validDelivery(OrderComponent orderOrGroupOrder) {
        orderOrGroupOrder.validDelivery();
        this.isAvailable = true;
        activeOrder = new GroupOrder();
    }

    public void assignOrder(Order order) {
        this.currentOrder = order;
        this.currentOrder.setExpectedDeliveryTime(calculateExpectedDeliveryTime()); // Set the expected delivery time
        java.lang.System.out.println("Order assigned to delivery person.");
    }

    // Calculate and set the expected delivery time
    private long calculateExpectedDeliveryTime() {
        long preparationTime = 30 * 60 * 1000; // Example: 30 minutes in milliseconds for preparation
        long currentTime = java.lang.System.currentTimeMillis(); // Get current time in milliseconds
        return currentTime + preparationTime;
    }

    // In this method, the delay is detected when current Time > expected Delivery Time
    public void observeUserDelay() {
        if (currentOrder != null) {
            long currentTime = java.lang.System.currentTimeMillis(); // Get current time in milliseconds
            long expectedDeliveryTime = currentOrder.getExpectedDeliveryTime(); // Fetch expected delivery time of the order

            if (currentTime > expectedDeliveryTime) {
                currentOrder.setDelayRecorded(true);
                java.lang.System.out.println("Delay observed for the assigned order.");
            }
        }
    }

    public void reportUserDelay(Order order, Customer cs) {
        cs.decrementerDelayCounter();
        cs.resetOrderNotDelayed();
    }
}