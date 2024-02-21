package fr.ippon.mase.demodulith.order;

import fr.ippon.mase.demodulith.SingletonDatabaseContainer;
import fr.ippon.mase.demodulith.catalog.CatalogManagement;
import fr.ippon.mase.demodulith.catalog.Offer;
import fr.ippon.mase.demodulith.order.impl.LineItem;
import fr.ippon.mase.demodulith.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;

@ApplicationModuleTest
class OrderManagementIT extends SingletonDatabaseContainer {

    @Autowired
    private OrderManagement orderManagement;

    @MockBean
    CatalogManagement catalogManagement;

    private PostgreSQLContainer postgresqlContainer = SingletonDatabaseContainer.getInstance();
    @Test
    void shouldCompleteOrder() {
        Offer.OfferId id = new Offer.OfferId(UUID.randomUUID());
        Order order = new Order(
                new User.UserId(UUID.randomUUID()),
                List.of(new LineItem(12.3, id)));
        orderManagement.complete(order);

        Order effectiveOrder = orderManagement.get(order.getOrderId());
        assertThat(effectiveOrder.getLineItems().get(0).getPrice()).isEqualTo(12.3);
        verify(catalogManagement).accept(isA(List.class));
    }
}