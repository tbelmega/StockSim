package de.belmega.stocksim.trade;

import java.time.LocalDateTime;
import java.util.Optional;

public abstract class Order {
    private final OrderId id;
    private Optional<LocalDateTime> expiryDate = Optional.empty();
    private long numberOfShares;

    public Order() {
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
        this.numberOfShares = numberOfShares;
    }

    public long getNumberOfShares() {
        return numberOfShares;
    }
}
