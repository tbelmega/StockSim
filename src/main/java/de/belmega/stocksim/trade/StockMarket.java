package de.belmega.stocksim.trade;

import de.belmega.stocksim.stock.Stock;
import de.belmega.stocksim.trade.transaction.ShareTransaction;
import org.apache.commons.lang.NotImplementedException;
import org.javamoney.moneta.Money;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class StockMarket implements Observer {

    private LocalDateTime lastCheck = LocalDateTime.MIN;
    private HashMap<OrderId, Bid> bids = new HashMap<>();
    private HashMap<OrderId, Ask> asks = new HashMap<>();
    private final Stock stock;

    public StockMarket(Stock stock) {
        this.stock = stock;
    }

    public void place(Ask order) {
        if (!order.isExpired())
            this.put(order.getId(), order);
        executeTransactions(order);
    }

    public void place(Bid order) {
        if (!order.isExpired())
            this.put(order.getId(), order);
        executeTransactions(order);
    }

    private void executeTransactions(Ask ask) {
        Bid bestMatch = findBestMatch(ask);
        if (bestMatch != null)
            execute(ask, bestMatch, bestMatch.getMinPrice());
    }

    private Bid findBestMatch(Ask ask) {
        Bid bestMatch = null;
        for (Bid bid: this.bids.values()) {
            if (bestMatch == null && ask.getMaxPrice().isGreaterThanOrEqualTo(bid.getMinPrice()))
                bestMatch = bid;
            else if (bestMatch != null && bid.getMinPrice().isLessThan(bestMatch.getMinPrice()))
                bestMatch = bid;
        }
        return bestMatch;
    }

    private void executeTransactions(Bid bid) {
        Ask bestMatch = findBestMatch(bid);
        if (bestMatch != null)
            execute(bestMatch, bid, bestMatch.getMaxPrice());
    }

    private Ask findBestMatch(Bid bid) {
        Ask bestMatch = null;
        for (Ask ask: this.asks.values()) {
            if (bestMatch == null && bid.getMinPrice().isGreaterThanOrEqualTo(ask.getMaxPrice()))
                bestMatch = ask;
            else if (bestMatch != null && ask.getMaxPrice().isLessThan(bestMatch.getMaxPrice()))
                bestMatch = ask;
        }
        return bestMatch;
    }

    private void execute(Ask ask, Bid bid, Money money) {
        long numberOfSharesTraded = Math.min(ask.getNumberOfShares(), bid.getNumberOfShares());

        ShareTransaction transaction = new ShareTransaction();
        transaction.transfer(money.multiply(numberOfSharesTraded), ask.getAccount(), bid.getAccount());
        transaction.transfer(numberOfSharesTraded, this.stock, bid.getAccount(), ask.getAccount());
        transaction.commit();

        ask.setTransaction(transaction);
        bid.setTransaction(transaction);

        ask.reduceNumberOfSharesBy(numberOfSharesTraded);
        bid.reduceNumberOfSharesBy(numberOfSharesTraded);
    }

    private void put(OrderId id, Order order) {
        order.addObserver(this);

        if (order instanceof Bid)
            this.bids.put(id, (Bid)order);
        else if (order instanceof Ask)
            this.asks.put(id, (Ask)order);
        else throw new NotImplementedException("Unexpected logical case.");
    }


    public Collection<Bid> getBids() {
        if (lastCheckMoreThanOneMinuteAgo())
            checkExpiry();
        return bids.values();
    }

    public Collection<Ask> getAsks() {
        if (lastCheckMoreThanOneMinuteAgo())
            checkExpiry();
        return asks.values();
    }

    private boolean lastCheckMoreThanOneMinuteAgo() {
        return LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).isAfter(lastCheck);
    }

    private void checkExpiry() {
        for (Order order: bids.values())
            if (order.isExpired())
                bids.remove(order.getId());
        for (Order order: asks.values())
            if (order.isExpired())
                asks.remove(order.getId());
        lastCheck = LocalDateTime.now();
    }

    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof Order) cancel((Order) obs);
    }

    public void cancel(Order order) {
        order.deleteObserver(this);
        this.asks.remove(order.getId());
        this.bids.remove(order.getId());
    }

    public Stock getStock() {
        return stock;
    }



    // an instance of stock market belongs to a specific stock.
    // stockholders can place bids and asks to buy or sell shares of this stock
    // if there is already a matching offer when the order is placed, the transaction will be executed
}
