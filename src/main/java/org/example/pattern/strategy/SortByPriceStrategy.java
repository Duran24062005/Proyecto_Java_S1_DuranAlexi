package org.example.pattern.strategy;

import java.util.List;

import org.example.Interfaces.patternInterfaces.strategy.ISortingStrategy;
import org.example.Model.PhoneModel;

// Estrategia 1: Ordenado por precio menor a mayor
public class SortByPriceStrategy implements ISortingStrategy {

    @Override
    public void sort(List<PhoneModel> phones) {
        phones.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
    }

    @Override
    public String getStrategyName() {
        return "Ordenado por precio (menor a mayor)";
    }
    
}
