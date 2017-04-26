package de.belmega.stocksim.trade;

import org.apache.commons.lang.NotImplementedException;
import org.javamoney.moneta.Money;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;

public class StockMarket {

    private LocalDateTime lastCheck = LocalDateTime.MIN;
    private HashMap<OrderId, Bid> bids = new HashMap<>();
    private HashMap<OrderId, Ask> asks = new HashMap<>();

    public void place(Order order) {
        if (!order.isExpired())
            this.put(order.getId(), order);
        executeTransactions(order);
    }

    private void executeTransactions(Order order) {
        if (order instanceof Bid)
            for (Ask ask: this.asks.values()) {
                Bid bid = (Bid) order;
                if (ask.getUpperLimit().isPresent() &&
                        ask.getUpperLimit().get().isLessThanOrEqualTo(bid.getLowerLimit().get()))
                    execute(ask, bid, ask.getUpperLimit().get());
            }
        else if (order instanceof Ask)
            for (Bid bid: this.bids.values()) {
                Ask ask = (Ask) order;
                if (bid.getLowerLimit().isPresent() &&
                        bid.getLowerLimit().get().isGreaterThanOrEqualTo(ask.getUpperLimit().get()))
                    execute(ask, bid, bid.getLowerLimit().get());

            }
        else throw new NotImplementedException("Unexpected logical case.");

    }

    private void execute(Ask ask, Bid bid, Money money) {
        long numberOfSharesTraded = Math.max(ask.getNumberOfShares(), bid.getNumberOfShares());
        if (numberOfSharesTraded == ask.getNumberOfShares()) cancel(ask);
        if (numberOfSharesTraded == bid.getNumberOfShares()) cancel(bid);
    }

    private void put(OrderId id, Order order) {
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

    public void cancel(Order order) {
        this.bids.remove(order.getId());
        this.asks.remove(order.getId());
    }

    public void cancel(OrderId id) {
        this.bids.remove(id);
        this.asks.remove(id);
    }


    // an instance of stock market belongs to a specific stock.
    // stockholders can place bids and asks to buy or sell shares of this stock
    // if there is already a matching offer when the order is placed, the transaction will be executed
}
