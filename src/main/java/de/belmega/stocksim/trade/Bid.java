package de.belmega.stocksim.trade;

import de.belmega.stocksim.account.BrokerAccount;
import org.javamoney.moneta.Money;

public class Bid extends Order {
    private final Money minPrice;

    private Bid(BrokerAccount account, Money minPrice) {
        super(account);
        this.minPrice = minPrice;
    }

    public Money getMinPrice() {
        return minPrice;
    }

    public static Bid create(BrokerAccount brokerAccount, Money money) {
        return new Bid(brokerAccount, money);
    }
}
