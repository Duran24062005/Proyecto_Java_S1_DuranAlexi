package org.example.pattern.factory;

import org.example.Interfaces.patternInterfaces.factory.IDiscount;

public class NoDiscount implements IDiscount {

    @Override
    public double apply(double price) {
        return price;  // Sin descuento
    }
    
    @Override
    public String getName() {
        return "Sin descuento";
    }
    
}
