package org.example.pattern.strategy;

import java.util.List;

import org.example.Interfaces.patternInterfaces.strategy.ISortingStrategy;
import org.example.Model.PhoneModel;

// Estrategia 4: Sin ordenar
public class NoSortStrategy implements ISortingStrategy {

    @Override
    public void sort(List<PhoneModel> phones) {
        // No hace nada
    }

    @Override
    public String getStrategyName() {
        return "Sin ordenamiento.";
    }
    
}
