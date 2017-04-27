package de.belmega.stocksim.trade;

import org.javamoney.moneta.Money;

public class Bid extends Order {
    private final Money minPrice;

    public Bid(Money minPrice) {
        this.minPrice = minPrice;
    }

    public Money getMinPrice() {
        return minPrice;
    }
}
