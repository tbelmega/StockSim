package de.belmega.stocksim.trade;

import org.javamoney.moneta.Money;

import java.util.Optional;

public class Bid extends Order {
    private Optional<Money> upperLimit;

    public void setUpperLimit(Money amount) {
        this.upperLimit = Optional.of(amount);
    }

    public Optional<Money> getLowerLimit() {
        return upperLimit;
    }
}
