package fr.ippon.mase.demodulith.order;

import fr.ippon.mase.demodulith.order.impl.LineItem;
import fr.ippon.mase.demodulith.user.User;
import jakarta.persistence.Table;
import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Association;
import org.jmolecules.ddd.types.Identifier;

import java.util.List;
import java.util.UUID;

@Table(name = "ORDER_DEMO")
public class Order implements AggregateRoot<Order, Order.OrderId> {

    private final OrderId orderId;
    private final Association<User, User.UserId> userId;
    private Status orderStatus;
    private List<LineItem> lineItems;

    public Order(User.UserId userId, List<LineItem> lineItems) {
        this.orderId = new OrderId(UUID.randomUUID());
        this.orderStatus = Status.OPEN;
        this.userId = Association.forId(userId);
        this.lineItems = lineItems;
    }

    public record OrderId(UUID id) implements Identifier {
    }

    enum Status {
        OPEN, COMPLETED, CANCELLED;
    }

    @Override
    public OrderId getId() {
        return orderId;
    }

    public Association<User, User.UserId> getUserId() {
        return userId;
    }

    public Status getOrderStatus() {
        return orderStatus;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }
}
