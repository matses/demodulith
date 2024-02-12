package fr.ippon.mase.demodulith.order.impl;

import fr.ippon.mase.demodulith.catalog.Offer;
import fr.ippon.mase.demodulith.order.Order;
import org.jmolecules.ddd.types.Association;
import org.jmolecules.ddd.types.Entity;
import org.jmolecules.ddd.types.Identifier;

import java.util.Objects;
import java.util.UUID;

public class LineItem implements Entity<Order, LineItem.LineItemId> {

    private final LineItemId id;
    private double price;
    private Association<Offer, Offer.OfferId> offerId;

    public LineItem(double price, Offer.OfferId offerId) {
        this.price = price;
        this.offerId = Association.forId(offerId);
        this.id = new LineItemId(UUID.randomUUID());
    }

    public LineItemId getId() {
        return id;
    }

    public static class LineItemId implements Identifier {
        private final UUID id;

        public static LineItemId create() {
            return LineItemId.of(UUID.randomUUID());
        }

        public static LineItemId of(UUID uuid) {
            return new LineItemId(uuid);
        }

        private LineItemId(UUID uuid) {
            this.id = uuid;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof LineItemId that)) return false;
            return Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public double getPrice() {
        return price;
    }

    public Association<Offer, Offer.OfferId> getOfferId() {
        return offerId;
    }
}
