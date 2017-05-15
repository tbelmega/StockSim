package de.belmega.stocksim.trade;

import de.belmega.stocksim.account.BrokerAccount;
import org.javamoney.moneta.Money;

public class Ask extends Order{
    private final Money maxPrice;

    private Ask(BrokerAccount account, Money maxPrice) {
        super(account);
        this.maxPrice = maxPrice;
    }

    public Money getMaxPrice() {
        return maxPrice;
    }

    public static Ask create(BrokerAccount account, Money money) {
        return new Ask(account, money);
    }
}
