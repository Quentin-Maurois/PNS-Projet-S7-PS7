package sophiatech.Restaurant;


import sophiatech.Order.BuffetOrder;
import sophiatech.Order.GroupOrder;
import sophiatech.Order.OrderComponent;
import sophiatech.Order.Status;
import sophiatech.System;
import sophiatech.AppUsers.Customer;
import sophiatech.Statistics.RestaurantStatistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Restaurant implements RestaurantSubject{
    System system;
    private RestaurantStatistics statistics;
    private ArrayList<Product> products;

    private String name;
    private String location;
    private ArrayList<GroupOrder> activeOrders;
    private ArrayList<GroupOrder> orderHistory;
    private Hours hours;
    private Menu menu;
    private int discountDuration;
    private int discount;
  //  private RestaurantProxy proxy = new RestaurantProxy(new RestaurantRealSubject(this));

    private double discountV1 = 0;     //discountV1 -> no time limit, works for all further orders
    private int discountV1Requirement = -1;  //nb of orders by the customer required to get this discount

    private int streakForDiscount;

    private int slot_duration = 10;
    private int capacity;
    public Restaurant(String name, String location, Hours hours, int discountDuration, int discount, int streakForDiscount, int capacity) {
        this.name = name;
        this.location = location;
        this.hours = hours;

        this.system = System.getInstance();

        this.products = new ArrayList<>();

        this.activeOrders = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
        this.menu = new Menu();
        this.discountDuration = discountDuration;
        this.discount = discount;
        this.streakForDiscount = streakForDiscount;
        this.capacity = capacity;

        this.statistics = new RestaurantStatistics(this);
    }

    public Hours getHours() {
        return this.hours;
    }

    public ArrayList<GroupOrder> getOrderHistory() {
        return this.orderHistory;
    }

    public RestaurantStatistics getStatistics() {
        return this.statistics;
    }

    public String getName() {
        return this.name;
    }

    public void addProduct(Product product) {
        if (product.getRestaurant() != this) {
            throw new RuntimeException("the product that you sent is not linked to this restaurant");
        }
        this.products.add(product);
        this.statistics.addProduct(product);
    }

    @Override
    public void addOrder(GroupOrder groupOrder) {
        acceptOrder(groupOrder);
        this.activeOrders.add(groupOrder);
        this.orderHistory.add(groupOrder);
        this.statistics.addGroupOrder(groupOrder);
    }

  /*  public void addOrder(GroupOrder groupOrder) {

        LocalTime borne_inf = groupOrder.getHour().withMinute((LocalTime.now().getMinute()/10)*10).withSecond(0).withNano(0);
        LocalTime borne_sup;
        if(borne_inf.getMinute()+10<=59){
            borne_sup = groupOrder.getHour().withMinute(borne_inf.getMinute()+10).withSecond(0).withNano(0);
        }else{
            borne_sup = groupOrder.getHour().withHour(borne_inf.getHour()+1).withMinute(0).withSecond(0);
        }

        boolean check_if_slot_available = proxy.checkAvailableSlot(borne_inf, borne_sup);
        java.lang.System.out.println("BOOLEAN EST DE : " + check_if_slot_available);
        if (check_if_slot_available) {
            acceptOrder(groupOrder);
            this.activeOrders.add(groupOrder);
            this.orderHistory.add(groupOrder);
            this.statistics.addGroupOrder(groupOrder);
        }
    }*/

    public ArrayList<GroupOrder> getActiveOrders() {
        return this.activeOrders;
    }

    public int getCapacity(){ return capacity;}

   /* public boolean checkAvailableSlot(LocalTime borne_inf, LocalTime borne_sup){
        int slot_capacity = getCapacity();
        for(GroupOrder go : getActiveOrders()){
            if(go.getHour().isAfter(borne_inf) && go.getHour().isBefore(borne_sup)){
                slot_capacity--;
            }
        }
        java.lang.System.out.print("LA CAPACITE EST DE : " +slot_capacity);
        return slot_capacity > 0;
    }*/

    public void acceptOrder(GroupOrder groupOrder) {
        for (OrderComponent order : groupOrder.orders) {
            order.changeStatus(Status.IN_PREPARATION);

        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }

    public void denyOrder(GroupOrder groupOrder) {
        for (OrderComponent order : groupOrder.orders) {
            order.changeStatus(Status.CANCELED);
        }
    }

    public void finishOrder(GroupOrder groupOrder) {
        for (OrderComponent order : groupOrder.orders) {
            order.changeStatus(Status.PREPARED);
        }
    }

    public String getLocation(){
        return this.location;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Restaurant){
            Restaurant r = (Restaurant) obj;
            return r.getLocation().equals(getLocation()) && r.getName().equals(getName());
        }
        return false;
    }
    public void addProductToMenu(Product newProduct) {
        menu.addProduct(newProduct);
        java.lang.System.out.println("New product was added to the menu");
    }

    public void removeProductFromMenu(Product oldProduct) {
        menu.removeProduct(oldProduct);
        java.lang.System.out.println("This product has been successfully deleted from the menu");
    }

    public void editProductInMenu(Product editedProduct) {
        return;
    }

    public List<Product> getMenu() {
        return menu.getProducts();
    }

    public void displayMenu() {
        menu.showMenu();
    }


    public void setName(String name){
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

    public long getDiscountDuration() {
        return discountDuration;
    }

    public int getDiscount() {
        return  discount;
    }
    public void setDiscountDuration(int discountDuration) {
        this.discountDuration = discountDuration;
    }

    public int getStreakForDiscount() {
        return streakForDiscount;
    }
    public void setStreakForDiscount(int streakForDiscount) {
        this.streakForDiscount = streakForDiscount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setDiscountV1 (double d) {
        this.discountV1 = d;
    }

    public void setDiscountV1Requirement (int requirement) {
        this.discountV1Requirement = requirement;
    }

    public double getCustomerDiscountV1 (Customer customer) {     //returns the percentage of discount for the customer (either the discount amount or 0)
        if (this.discountV1Requirement < 0) return 0;

        ArrayList<OrderComponent> customerOrders = new ArrayList<>();    //will contain every orders the customer made in this restaurant. (not groupOrders)
        for (GroupOrder groupOrder : orderHistory) {
            for(OrderComponent order : groupOrder.orders) {
                if (order.getCustomer().equals(customer)) {
                    customerOrders.add(order);
                }
            }
        }

        if (customerOrders.size() >= discountV1Requirement) {
            return this.discountV1;
        }

        return 0;
    }

    public void editCapacity(int new_capacity){
        this.capacity = new_capacity;
    }


}