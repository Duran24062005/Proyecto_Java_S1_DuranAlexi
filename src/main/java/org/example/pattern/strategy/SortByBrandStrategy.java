package org.example.pattern.strategy;

import java.util.List;

import org.example.Interfaces.patternInterfaces.strategy.ISortingStrategy;
import org.example.Model.PhoneModel;

// Estrategia 3: Ordenar por marca
public class SortByBrandStrategy implements ISortingStrategy {

    @Override
    public void sort(List<PhoneModel> phones) {
        phones.sort((p1, p2) -> p1.getBrand().compareTo(p2.getBrand()));
    }

    @Override
    public String getStrategyName() {
        return "Ordenado por marca";
    }
    
}
