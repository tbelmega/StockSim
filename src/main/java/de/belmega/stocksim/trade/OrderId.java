package de.belmega.stocksim.trade;

import java.util.UUID;

public class OrderId {

    private final UUID uuid;

    public OrderId() {
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "OrderId{" +
                "uuid=" + uuid +
                '}';
    }

    public static OrderId generateNew() {
        return new OrderId();
    }
}
