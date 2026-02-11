package org.example.pattern.factory;

import org.example.Interfaces.patternInterfaces.factory.IDiscount;

/*
    Clase Factory, oquestador de descuetos
*/
public class DiscountFactory {

    public static IDiscount createDiscount(String type) {
        switch (type.toLowerCase()) {
            case "premium":
                return new PremiumDiscount();
            case "standard":
                return new StandardDicount();
            case "none":
            default:
                return new NoDiscount();
        }
    }
}
