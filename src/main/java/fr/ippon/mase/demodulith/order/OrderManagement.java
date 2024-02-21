package fr.ippon.mase.demodulith.order;

import fr.ippon.mase.demodulith.catalog.CatalogManagement;
import fr.ippon.mase.demodulith.order.impl.LineItem;
import fr.ippon.mase.demodulith.order.impl.Orders;
import fr.ippon.mase.demodulith.user.User;
import org.jmolecules.ddd.annotation.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderManagement {

    private final Orders orderRepository;

    private final CatalogManagement catalogManagement;

    public OrderManagement(Orders orderRepository, CatalogManagement catalogManagement) {
        this.orderRepository = orderRepository;
        this.catalogManagement = catalogManagement;
    }

    public void complete(Order order) {
        orderRepository.save(order);
        catalogManagement.accept(
                order.getLineItems().stream()
                        .map(li -> li.getOfferId().getId())
                        .collect(Collectors.toList()));
    }

    public Order get(Order.OrderId id){
        return orderRepository.findById(id).orElse(null);
    }



}
