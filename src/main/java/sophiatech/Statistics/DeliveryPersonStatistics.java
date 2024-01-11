package sophiatech.Statistics;

import sophiatech.AppUsers.DeliveryPerson;
import sophiatech.Order.GroupOrder;
import sophiatech.Order.Order;
import sophiatech.Order.OrderComponent;

import java.time.LocalTime;

public class DeliveryPersonStatistics {
    private DeliveryPerson deliveryPerson;
    private LocalTime creationDate;
    private int numberOrders;
    private double totalPriceOrders;
    private int[] ordersAtTime; //indexes the time at which the orders are placed

    public DeliveryPersonStatistics (DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
        this.creationDate = LocalTime.now();
        this.numberOrders = 0;
        this.totalPriceOrders = 0.0;
        this.ordersAtTime = new int[24];
    }

    public int getNumberOrders () {
        return this.numberOrders;
    }
    public double getAveragePriceForOrder() {
        if (this.numberOrders == 0)
            return 0;
        return this.totalPriceOrders / this.numberOrders;
    }
    public double getTotalPriceForOrders() {
        return this.totalPriceOrders;
    }
    public double[] getPercentageOrdersAtTime() {
        double [] percents = new double[24];

        for (int i = 0; i < 24; i++) {
            percents[i] += ordersAtTime[i];
            percents[i] = percents[i] / numberOrders;
        }
        return percents;
    }

    public void addOrder (OrderComponent order) {
        this.numberOrders ++;
        this.totalPriceOrders += order.getTotalPrice();
        this.ordersAtTime[order.getHour().getHour()] ++;    //adds an order to the corresponding time thus the corresponding index
    }

    public void addGroupOrder (GroupOrder groupOrder) {
        for (OrderComponent order : groupOrder.orders) {
            this.addOrder((Order) order);
        }
    }

}
