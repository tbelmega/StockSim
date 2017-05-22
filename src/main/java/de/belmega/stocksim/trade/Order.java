package de.belmega.stocksim.trade;


import de.belmega.stocksim.account.BrokerAccount;
import de.belmega.stocksim.trade.transaction.ShareTransaction;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Optional;

public abstract class Order extends Observable {
    private final OrderId id;
    private Optional<LocalDateTime> expiryDate = Optional.empty();
    private long numberOfShares;
    private Optional<ShareTransaction> transaction;
    protected final BrokerAccount account;

    protected Order(BrokerAccount account) {
        this.account = account;
        this.id = OrderId.generateNew();
    }

    public OrderId getId() {
        return id;
    }

    public Optional<LocalDateTime> getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = Optional.of(expiryDate);
    }

    public boolean isExpired() {
        if (!this.getExpiryDate().isPresent()) return false;
        return LocalDateTime.now().isAfter(this.getExpiryDate().get());
    }

    public void setNumberOfShares(long numberOfShares) {
        if (numberOfShares >= 0) this.numberOfShares = numberOfShares;
        else throw new IllegalArgumentException("Number of shares must be positive, but was " + numberOfShares);

        setChanged();
        notifyObservers();
    }


    public long getNumberOfShares() {
        return numberOfShares;
    }

    public void reduceNumberOfSharesBy(long numberOfSharesTraded) {
        this.setNumberOfShares(this.numberOfShares - numberOfSharesTraded);
    }

    public Optional<ShareTransaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(ShareTransaction transaction) {
        this.transaction = Optional.of(transaction);
    }

    public BrokerAccount getAccount() {
        return account;
    }

}
