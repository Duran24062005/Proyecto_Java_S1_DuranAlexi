package org.example.pattern.factory;

import org.example.Interfaces.patternInterfaces.factory.IDiscount;

public class PremiumDiscount implements IDiscount {
    @Override
    public double apply(double price) {
        return price * 0.90; // 10% descuento
    }

    @Override
    public String getName() {
        return  "Descuento premium (10%)";
    }
}
