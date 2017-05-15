package de.belmega.stocksim.trade.transaction;

import de.belmega.stocksim.account.BrokerAccount;
import de.belmega.stocksim.stock.Stock;

import javax.money.MonetaryAmount;

public class MoneyTransfer extends Transfer {

    private final MonetaryAmount money;


    public MoneyTransfer(BrokerAccount account, MonetaryAmount money, Type operation) {
        super(account, operation);
        this.money = money;
    }


    @Override
    public void execute() {
        if (this.operation == Type.DEPOSIT) {
            this.account.deposit(money);
        } else {
            this.account.withdraw(money);
        }
    }
}
