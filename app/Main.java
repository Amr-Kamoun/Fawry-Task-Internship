package app;

import model.*;
import product.*;
import service.Cart;
import service.CheckoutService;

import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        var cheese = new ShippableExpirableProduct("Cheese", 100, 10, 0.2, false);
        var biscuits = new ShippableExpirableProduct("Biscuits", 150, 5, 0.7, false);
        var customer = new Customer("Ali", 1000);
        var cart = new Cart();

        try {
            cart.add(cheese, 2);
            cart.add(biscuits, 1);

            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            LOGGER.severe("Error: " + e.getMessage());
        }
    }
}
