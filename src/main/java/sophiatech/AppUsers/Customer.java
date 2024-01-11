package sophiatech.AppUsers;

import sophiatech.Order.*;
import sophiatech.Restaurant.Formule;
import sophiatech.Restaurant.Hours;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;
import sophiatech.Services.Discount;
import sophiatech.Statistics.CustomerStatistics;
import sophiatech.System;

import java.awt.font.TextHitInfo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;


public class Customer {
    private System system;

    private String firstName;
    private String lastName;
    private UserType userType;

    private String favouriteLocation;

    private ArrayList<Discount> discounts;

    private ArrayList<GroupOrder> orderHistory;

    private ArrayList<Product> pendingOrder;
    private CustomerStatistics statistics = new CustomerStatistics(this);

    private GroupOrder activeOrder;
    private int delayCounter;
    private boolean isBanned;

    private int orderNotDelayed;

    public Customer(String fn, String ln, UserType userType){
        this.firstName = fn;
        this.lastName = ln;
        this.userType = userType;
        this.system = System.getInstance();
        this.pendingOrder = new ArrayList<>();
        this.activeOrder = new GroupOrder();
        this.orderHistory = new ArrayList<>();
        this.delayCounter=3;
        this.orderNotDelayed=0;
        this.isBanned=false;
        discounts = new ArrayList<>();
    }

    public void checkNotDelayedPoints(){
        if(this.orderNotDelayed == 3){
            this.orderNotDelayed = 0;
            this.delayCounter++;
        }
    }

    public void resetOrderNotDelayed(){
        this.orderNotDelayed=0;
    }

    public void incrementOrderNotDelayed(){
        if(this.orderNotDelayed<3){
            this.orderNotDelayed++;
        }
    }

    public String getFavouriteLocation() {
        return this.favouriteLocation;
    }

    public void setFavouriteLocation(String favouriteLocation) {
        this.favouriteLocation = favouriteLocation;
    }


    public void addProductToPendingOrder(Product p) {
        /*
        if (pendingOrder.size() == 0) {
            this.pendingOrder.add(p);
            return;
        }
        //if all the orders are from the same restaurant
        if (pendingOrder.get(0).getRestaurant() == p.getRestaurant()) {
            this.pendingOrder.add(p);
        }*/
        this.pendingOrder.add(p);
    }

    public void addFormuleToPendingOrder(Formule f) {

        this.pendingOrder.addAll(f.getProducts());
    }

    public ArrayList<Product> getPendingOrder() {
        return this.pendingOrder;
    }

    public String getCustomerName(){
        return this.firstName + " " + this.lastName;
    }
    public int getSizePendingOrder(){
        return this.pendingOrder.size();
    }



    public void payForOrder() {
        this.payForOrder(this.favouriteLocation, 1, null);
    }

    public void payForOrder(Customer recipientUser) {
        this.payForOrder(this.favouriteLocation, 1, recipientUser);
    }

    public void payForOrder(String location, Customer recipientUser) {
        this.payForOrder(location, 1, recipientUser);
    }

    public void payForOrder(String location, int nbGuests) {
        this.payForOrder(location, nbGuests, null);
    }

    public void payForOrder(String location) {
        this.payForOrder(location, 1, null);
    }

  /*
  * by default, for any other type of order than AfterWork, the number of persons is 1 (the customer)
  * */
    public void payForOrder(String location, int nbpersons, Customer recipientUser) {
        /*double total = 0;
        for (Product p : pendingOrder) {
            total += p.getPrice();
        }
        switch (this.userType) {
            case STUDENT:
                total =total - total* 0.05;
                break;
            case FACULTY:
                total =total - total* 0.03;
                break;
            case STAFF:
                total = total - total* 0.04;
                break;
        }
        for (Discount d : discounts) {
            if (d.getExpirationDate().isBefore(LocalDate.now().plusDays(1))){
                discounts.remove(d);
                if(discounts.isEmpty()) break;
            } else if (d.getRestaurant() == pendingOrder.get(0).getRestaurant()) {
                java.lang.System.out.println("Discount applied: "+total);
                total = total - total * d.getPercentage() / 100;
                java.lang.System.out.println("Discount applied: "+total);
            }
        }*/

        GroupOrder generateListOrder = generateOrder(pendingOrder, location, nbpersons, recipientUser);
        //RE AJUST THE PRICE OF THE ORDERS
        for(OrderComponent indexOrder : generateListOrder.orders){
            java.lang.System.out.println(indexOrder);
            switch (this.userType) {
                case STUDENT:
                    indexOrder.setPrice(indexOrder.getTotalPrice()*0.95);
                    break;
                case FACULTY:
                    indexOrder.setPrice(indexOrder.getTotalPrice()*0.97);
                    break;
                case STAFF:
                    indexOrder.setPrice(indexOrder.getTotalPrice()*0.96);
                    break;
            }

            java.lang.System.out.println("0");

            for (Restaurant r : indexOrder.getRestaurants()) {
                java.lang.System.out.println(r);
            }

            if (indexOrder.getRestaurants().size() == 1) {  // if is a simple order, do as before
                java.lang.System.out.println("0.5");
                if (indexOrder.getRestaurant().getCustomerDiscountV1(this) != 0) {
                    java.lang.System.out.println("1");
                    indexOrder.setPrice(indexOrder.getTotalPrice() - indexOrder.getTotalPrice() * indexOrder.getRestaurant().getCustomerDiscountV1(this));
                }
            } else {    //else : get the total price for each restaurant and subtract it with the potential discount
                java.lang.System.out.println("2");
                MultipleOrder mo = (MultipleOrder) indexOrder;
                Map<Restaurant, Double> priceForRestaurants = mo.getPriceForRestaurants();

                for (Map.Entry<Restaurant, Double> entry : priceForRestaurants.entrySet()) {
                    Restaurant r = entry.getKey();
                    Double price = entry.getValue();

                    if (r.getCustomerDiscountV1(this) != 0) {
                        double discountV1 = r.getCustomerDiscountV1(this);
                        priceForRestaurants.put(r, price - price * discountV1);
                    }
                }
            }


            java.lang.System.out.println("4");
            for (Discount d : discounts) {
                if (d.getExpirationDate().isBefore(LocalDate.now().plusDays(1))){
                    discounts.remove(d);
                    if(discounts.isEmpty()) break;
                } else if (d.getRestaurant() == indexOrder.getRestaurant()) {
                    //java.lang.System.out.println("Discount applied: "+total);
                    indexOrder.setPrice(indexOrder.getTotalPrice() - indexOrder.getTotalPrice() * d.getPercentage() / 100);// = total - total * d.getPercentage() / 100;
                    //java.lang.System.out.println("Discount applied: "+total);
                }
            }
        }

        /*double discountV1 = pendingOrder.get(0).getRestaurant().getCustomerDiscountV1(this);
        java.lang.System.out.println("DiscountV1 = " + discountV1);
        total = total - total * discountV1;*/

        if ((this.system.getPaymentService().pay(generateAllPrice(generateListOrder.orders)))) { //if payment is successfull
            java.lang.System.out.println("5");
            //ArrayList<Order> generateListOrder = generateOrder(pendingOrder);

            //Order order = new Order(this, location, LocalTime.now(), pendingOrder);



            this.addOrder(generateListOrder);


            HashSet<Restaurant> restaurants = new HashSet<>();
            for (OrderComponent o : generateListOrder.orders) {
                restaurants.addAll(o.getRestaurants());
            }
            for (Restaurant r : restaurants) {
                java.lang.System.out.println("6");
                checkEligibleToDiscount(r);
                r.addOrder(generateListOrder);
            }

            system.addGroupOrder(generateListOrder);


            ArrayList<DeliveryPerson> availableDeliveryPersons = this.system.getAvailableDeliveryPerson();
            if (! availableDeliveryPersons.isEmpty())
                availableDeliveryPersons.get(0).addOrder(generateListOrder);    //gives the order to the first available delivery person.
            else {
                system.addOrderWithoutDeliveryPerson(generateListOrder);

            }


            this.pendingOrder = new ArrayList<>();  //flush the newly created order's products
            //return order;
        } else {
            generateListOrder.changeStatus(Status.CANCELED);
        }
    }

    private double generateAllPrice(ArrayList<OrderComponent> list){
        double ret = 0;
        for(OrderComponent o : list){
            ret+=o.getTotalPrice();
        }
        return ret;
    }

    private GroupOrder generateOrder(ArrayList<Product> pendingOrder, String location, int nbPersons, Customer recipientUser) {
        ArrayList<OrderComponent> ret = new ArrayList<>();
        ret.add(OrderFactory.createOrder(this,recipientUser, location, LocalTime.now(), pendingOrder, nbPersons));
        return OrderFactory.createGroupOrder(ret);
    }

    private void checkEligibleToDiscount(Restaurant restaurant) {
        int counter=0;
        for(GroupOrder o: this.orderHistory){
            if(o.orders.get(0).getRestaurant()==restaurant && !(o.orders.get(0).isAlreadyUsedForDiscount())){
                counter++;
                if(counter==restaurant.getStreakForDiscount()){
                    for(GroupOrder or: this.orderHistory) {
                        if (or.orders.get(0).getRestaurant() == restaurant && !(or.orders.get(0).isAlreadyUsedForDiscount())) {
                            or.orders.get(0).setAlreadyUsedForDiscount(true);
                        }
                    }
                    this.addDiscount(restaurant);
                    break;
                }
            }
        }
    }

    public void addOrder(GroupOrder groupOrder) {
        this.statistics.addGroupOrder(groupOrder);
        this.activeOrder = groupOrder;
        this.orderHistory.add(groupOrder);
    }

    public GroupOrder getActiveOrder() {
        return this.activeOrder;
    }

    public Hours getHours(Restaurant restaurant) {
        ArrayList<Restaurant> listRestaurant = this.system.getListRestaurant();
        for (Restaurant r : listRestaurant) {
            if (r.getName().equals(restaurant.getName())) {
                return r.getHours();
            }
        }
        return null;
    }


    public GroupOrder allowGroupOrder() {
        this.activeOrder.setIsOpen(true);
        return activeOrder;
    }


    public void setHistory(ArrayList<GroupOrder> orderHistory){
        this.orderHistory = orderHistory;
    }

    public ArrayList<GroupOrder> getHistory(){
        return this.orderHistory;
    }

    public GroupOrder getOrderAtIndexHistory(int i){
        return this.orderHistory.get(i);
    }

    public Restaurant searchRestaurant(String name, String location){
        ArrayList<Restaurant> listRestaurant = this.system.getListRestaurant();
        ArrayList<Restaurant> potentialTarget = new ArrayList<>();
        for(Restaurant r: listRestaurant){
            if(r.getName().equals(name)) potentialTarget.add(r);
        }
        if(potentialTarget.size()==1){
            return potentialTarget.get(0);
        }else{
            for(Restaurant r: potentialTarget){
                if(r.getLocation().equals(location)) return r;
            }
            return null;
        }
    }

    public void validDelivery(GroupOrder groupOrder){
        for (OrderComponent order : groupOrder.orders) {
            order.validateOrder();
            order.changeStatusValidation(Status.DELIVERY_CONFIRMED);
        }
        activeOrder = new GroupOrder();
    }

    public UserType getUserType() {
        return userType;
    }

    public int getDelayCounter(){
        return delayCounter;
    }

    public void decrementerDelayCounter(){
        this.delayCounter--;
        if(this.delayCounter<=0){
            isBanned=true;
            this.delayCounter=0;
        }
    }

    public boolean isActive() {
        return !isBanned;
    }

    public void addDiscount(Restaurant restaurant) {
        long discountDuration = restaurant.getDiscountDuration();
        LocalDate currentDate = LocalDate.now();
        LocalDate expirationDate = currentDate.plusDays(discountDuration);

        // Ajout du nouveau discount à la liste
        discounts.add(new Discount(restaurant, restaurant.getDiscount(), expirationDate));
    }


    public ArrayList<Discount> getDiscounts() {
        return discounts;
    }

    public CustomerStatistics getStatistics() {
        return this.statistics;
    }



}