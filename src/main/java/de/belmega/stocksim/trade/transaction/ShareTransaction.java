package de.belmega.stocksim.trade.transaction;

import de.belmega.stocksim.account.BrokerAccount;
import de.belmega.stocksim.stock.Stock;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShareTransaction {

    private Set<Transfer> transfers = new HashSet<>();
    private MonetaryAmount monetaryAmount;
    private long shareAmount;

    public void transfer(long amount, Stock stock, BrokerAccount from, BrokerAccount to) {
        shareAmount = amount;
        this.transfers.add(new StockTransfer(from, amount, stock, Transfer.Type.WITHDRAW));
        this.transfers.add(new StockTransfer(to, amount, stock, Transfer.Type.DEPOSIT));
    }

    public void transfer(MonetaryAmount money, BrokerAccount from, BrokerAccount to) {
        this.monetaryAmount = money;
        this.transfers.add(new MoneyTransfer(from, money, Transfer.Type.WITHDRAW));
        this.transfers.add(new MoneyTransfer(to, money, Transfer.Type.DEPOSIT));
    }

    public void commit() {
        transfers.forEach((transfer -> transfer.execute()));
    }

    public MonetaryAmount getMonetaryAmount() {
        return monetaryAmount;
    }

    public long getShareAmount() {
        return this.shareAmount;
    }
}
