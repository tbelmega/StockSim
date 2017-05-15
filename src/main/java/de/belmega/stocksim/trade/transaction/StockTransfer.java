package de.belmega.stocksim.trade.transaction;

import de.belmega.stocksim.account.BrokerAccount;
import de.belmega.stocksim.stock.Stock;

import javax.money.MonetaryAmount;

public class StockTransfer extends Transfer {

    private final long amount;
    private final Stock stock;

    public StockTransfer(BrokerAccount account, long amount, Stock stock, Type operation) {
        super(account, operation);
        this.amount = amount;
        this.stock = stock;
    }

    @Override
    public void execute() {
        if (this.operation == Type.DEPOSIT) {
            this.account.deposit(this.amount, this.stock);
        } else {
            this.account.withdraw(this.amount, this.stock);
        }
    }
}
