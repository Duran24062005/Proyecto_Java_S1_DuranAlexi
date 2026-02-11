package org.example.pattern.factory;

import org.example.Interfaces.patternInterfaces.factory.IDiscount;

public class StandardDicount implements IDiscount {

    @Override
    public double apply(double price) {
        return price * 0.95;  // 5% descuento
    }
    
    @Override
    public String getName() {
        return "Descuento Estándar (5%)";
    }
    
}
