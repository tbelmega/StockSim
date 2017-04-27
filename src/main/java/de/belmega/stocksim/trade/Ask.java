package de.belmega.stocksim.trade;

import org.javamoney.moneta.Money;

public class Ask extends Order{
    private final Money maxPrice;

    public Ask(Money maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Money getMaxPrice() {
        return maxPrice;
    }
}
