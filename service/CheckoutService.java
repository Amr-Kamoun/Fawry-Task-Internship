package service;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckoutService {
    private static final Logger LOGGER = Logger.getLogger(CheckoutService.class.getName());

    private CheckoutService() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty!");
        }

        double subtotal = 0;
        double shippingFee = 0;
        List<Shippable> toShip = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (product.isExpired()) {
                throw new IllegalStateException(product.getName() + " is expired!");
            }
            if (item.getQuantity() > product.getQuantity()) {
                throw new IllegalStateException(product.getName() + " out of stock!");
            }

            subtotal += item.getTotalPrice();

            if (product.requiresShipping()) {
                toShip.add((Shippable) product);
                shippingFee += 15; 
            }
        }

        double total = subtotal + shippingFee;
        if (customer.getBalance() < total) {
            throw new IllegalStateException("Insufficient balance!");
        }

        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        customer.deduct(total);

        if (!toShip.isEmpty()) {
            ShippingService.ship(toShip);
        }

        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info("** Checkout receipt **");
            for (CartItem item : cart.getItems()) {
                LOGGER.info(String.format("%dx %-12s %.0f",
                        item.getQuantity(),
                        item.getProduct().getName(),
                        item.getTotalPrice()));
            }
            LOGGER.info("----------------------");
            LOGGER.info(String.format("Subtotal         %.0f", subtotal));
            LOGGER.info(String.format("Shipping         %.0f", shippingFee));
            LOGGER.info(String.format("Amount           %.0f", total));
            LOGGER.info(String.format("Remaining balance %.0f", customer.getBalance()));
        }
    }
}
