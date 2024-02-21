package fr.ippon.mase.demodulith.order.impl;

import fr.ippon.mase.demodulith.order.Order;
import org.jmolecules.ddd.integration.AssociationResolver;
import org.jmolecules.ddd.types.Repository;

import java.util.Optional;

public interface Orders extends Repository<Order, Order.OrderId>, AssociationResolver<Order, Order.OrderId> {

    public Order save(Order order);

    public Optional<Order> findById(Order.OrderId id);
}
