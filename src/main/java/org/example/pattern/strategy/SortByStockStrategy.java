package org.example.pattern.strategy;

import java.util.List;

import org.example.Interfaces.patternInterfaces.strategy.ISortingStrategy;
import org.example.Model.PhoneModel;

// Estrategia 2: Ordenado por stock menor a mayor
public class SortByStockStrategy implements ISortingStrategy {

    @Override
    public void sort(List<PhoneModel> phones) {
        phones.sort((p1, p2) -> Integer.compare(p1.getStock(), p2.getStock()));
    }

    @Override
    public String getStrategyName() {
        return "Ordenado por stock (menor a mayor)";
    }
    
}
