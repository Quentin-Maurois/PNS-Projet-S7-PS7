package sophiatech.Restaurant;

import sophiatech.Order.GroupOrder;

import java.time.LocalTime;

public class RestaurantProxy implements RestaurantSubject{
    private RestaurantSubject restaurantRealSubject;

    public RestaurantProxy(RestaurantSubject restaurant) {
        this.restaurantRealSubject = restaurant;
    }

    @Override
    public void addOrder(GroupOrder groupOrder) {
        LocalTime borne_inf = groupOrder.getHour().withMinute((LocalTime.now().getMinute()/10)*10).withSecond(0).withNano(0);
        LocalTime borne_sup;
        if(borne_inf.getMinute()+10<=59){
            borne_sup = groupOrder.getHour().withMinute(borne_inf.getMinute()+10).withSecond(0).withNano(0);
        } else {
            borne_sup = groupOrder.getHour().withHour(borne_inf.getHour()+1).withMinute(0).withSecond(0);
        }

        //Restaurant restaurant = groupOrder.getRestaurant();

        int slot_capacity = ((Restaurant) restaurantRealSubject).getCapacity();
        //int slot_capacity = restaurantRealSubject.getCapacity();
        for (GroupOrder go : ((Restaurant) restaurantRealSubject).getActiveOrders()) {
            if (go.getHour().isAfter(borne_inf) && go.getHour().isBefore(borne_sup)) {
                slot_capacity--;
            }
        }
        java.lang.System.out.print("LA CAPACITE EST DE : " + slot_capacity);


        boolean check_if_slot_available = slot_capacity > 0;
        java.lang.System.out.println("BOOLEAN EST DE : " + check_if_slot_available);

        if (check_if_slot_available) {
            restaurantRealSubject.addOrder(groupOrder);
        }
    }


   /* @Override
    public boolean checkAvailableSlot(LocalTime borne_inf, LocalTime borne_sup) {

        int slot_capacity = restaurant.getCapacity();
        for (GroupOrder go : restaurant.getActiveOrders()) {
            if (go.getHour().isAfter(borne_inf) && go.getHour().isBefore(borne_sup)) {
                slot_capacity--;
            }
        }
        java.lang.System.out.print("LA CAPACITE EST DE : " + slot_capacity);
        return slot_capacity > 0;

    }*/
}
