package fr.ippon.mase.demodulith.order.impl;

import fr.ippon.mase.demodulith.order.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderRepository {

    public Order save(Order order) {
        return order;
    }
}
