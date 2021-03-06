package de.belmega.stocksim.account;


import de.belmega.stocksim.stock.Stock;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.money.MonetaryCurrencies;
import java.util.HashMap;
import java.util.Map;

public class BrokerAccount {

    public static final CurrencyUnit EUR = MonetaryCurrencies.getCurrency("EUR");

    private MonetaryAmount balance = Money.of(0, EUR);
    private Map<Stock, Long> shares = new HashMap<>();

    public MonetaryAmount getBalance() {
        return balance;
    }


    public void deposit(MonetaryAmount amount) {
        balance = balance.add(amount);
    }

    public boolean withdraw(MonetaryAmount amount) {
        MonetaryAmount difference = balance.subtract(amount);
        if (difference.isNegative()) {
            return false;
        } else {
            balance = difference;
            return true;
        }
    }

    public void deposit(long amount, Stock stock) {
        Long currentAmount = this.shares.get(stock);
        if (currentAmount == null) {
            currentAmount = 0L;
        }
        this.shares.put(stock, amount + currentAmount);
    }

    public long getShares(Stock stock) {
        return shares.get(stock);
    }

    public boolean withdraw(long amount, Stock stock) {
        Long currentAmount = this.shares.get(stock);
        long newAmount = currentAmount - amount;

        if (newAmount >= 0) {
            this.shares.put(stock, newAmount);
            return true;
        } else {
            return false;
        }

    }
}
