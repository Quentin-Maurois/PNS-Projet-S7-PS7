package sophiatech.Services;

import sophiatech.Restaurant.Restaurant;

import java.time.LocalDate;

public class Discount {
    private Restaurant restaurant;
    private int percentage;

    private LocalDate expirationDate;

    public Discount(Restaurant restaurant, int percentage, LocalDate expirationDate) {
        this.restaurant = restaurant;
        this.percentage = percentage;
        this.expirationDate = expirationDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public int getPercentage() {
        return percentage;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
