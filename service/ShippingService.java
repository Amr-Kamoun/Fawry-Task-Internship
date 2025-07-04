package service;

import model.Shippable;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShippingService {
    private static final Logger LOGGER = Logger.getLogger(ShippingService.class.getName());

    // Private constructor to prevent instantiation
    private ShippingService() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void ship(List<Shippable> items) {
        double totalWeight = 0;

        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info("** Shipment notice **");

            for (Shippable item : items) {
                LOGGER.info(String.format("%s\t%.0fg", 
                        item.getName(), 
                        item.getWeight() * 1000));
                totalWeight += item.getWeight();
            }

            LOGGER.info(String.format("Total package weight %.1fkg", totalWeight));
        }
    }
}
