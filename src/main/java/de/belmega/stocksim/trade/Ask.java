package de.belmega.stocksim.trade;

import org.javamoney.moneta.Money;

import java.util.Optional;

public class Ask extends Order{
    private Optional<Money> lowerLimit;

    public void setLowerLimit(Money amount) {
        this.lowerLimit = Optional.of(amount);
    }

    public Optional<Money> getUpperLimit() {
        return lowerLimit;
    }
}
