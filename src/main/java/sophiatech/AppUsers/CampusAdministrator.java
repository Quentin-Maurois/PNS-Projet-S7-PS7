package sophiatech.AppUsers;

import sophiatech.Restaurant.Restaurant;
import sophiatech.System;

public class CampusAdministrator {

    private System system;

    public CampusAdministrator(){
        this.system = System.getInstance();
    }
    public CampusAdministrator(System sys){ this.system = sys;}

    public void addRestaurant(Restaurant r){
        this.system.addRestaurant(r);
    }
    public void addDeliveryPerson(DeliveryPerson dp){ this.system.addDeliveryPerson(dp);}

    public void deleteRestaurant(Restaurant r){this.system.deleteRestaurant(r);}
  
    public void removeDeliveryPerson(DeliveryPerson dp) { this.system.removeDeliveryPerson(dp);}

}