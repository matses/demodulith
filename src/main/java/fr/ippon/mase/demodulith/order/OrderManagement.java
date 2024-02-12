package fr.ippon.mase.demodulith.order;

import fr.ippon.mase.demodulith.catalog.impl.CatalogRepository;
import fr.ippon.mase.demodulith.order.impl.LineItem;
import fr.ippon.mase.demodulith.order.impl.OrderRepository;
import fr.ippon.mase.demodulith.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderManagement {

    private OrderRepository orderRepository;

    private CatalogRepository catalogRepository;

    public void complete(User.UserId userId, List<LineItem> lineItems) {
        Order order = new Order(userId, lineItems);
        orderRepository.save(order);
        catalogRepository.passToSold(
                lineItems.stream()
                        .map(li -> li.getOfferId().getId())
                        .collect(Collectors.toList()));
    }



}
