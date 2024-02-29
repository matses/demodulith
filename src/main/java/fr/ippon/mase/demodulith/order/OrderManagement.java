package fr.ippon.mase.demodulith.order;

import fr.ippon.mase.demodulith.catalog.CatalogManagement;
import fr.ippon.mase.demodulith.order.impl.Orders;
import org.jmolecules.ddd.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class OrderManagement {

    private final Orders orderRepository;

    private final CatalogManagement catalogManagement;

    public OrderManagement(Orders orderRepository, CatalogManagement catalogManagement) {
        this.orderRepository = orderRepository;
        this.catalogManagement = catalogManagement;
    }

    @Transactional
    public void complete(Order order) {
        Order persistedOrder = orderRepository.save(order);
        catalogManagement.accept(
                persistedOrder.getLineItems().stream()
                        .map(li -> li.getOfferId().getId())
                        .collect(Collectors.toList()));
    }

    public Order get(Order.OrderId id){
        return orderRepository.findById(id).orElse(null);
    }



}
