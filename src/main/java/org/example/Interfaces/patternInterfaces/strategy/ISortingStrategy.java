package org.example.Interfaces.patternInterfaces.strategy;

import java.util.List;

import org.example.Model.PhoneModel;

// Interface Strategy
public interface ISortingStrategy {
    void sort(List<PhoneModel> phones);
    String getStrategyName();
}
