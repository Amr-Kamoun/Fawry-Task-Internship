package product;

import model.Product;
import model.Shippable;

public class ShippableExpirableProduct extends Product implements Shippable {
    private double weight;
    private boolean expired;

    public ShippableExpirableProduct(String name, double price, int quantity, double weight, boolean expired) {
        super(name, price, quantity);
        this.weight = weight;
        this.expired = expired;
    }

    @Override
    public boolean isExpired() { return expired; }

    @Override
    public boolean requiresShipping() { return true; }

    @Override
    public double getWeight() { return weight; }
}
