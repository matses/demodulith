package fr.ippon.mase.demodulith.catalog;

import fr.ippon.mase.demodulith.SingletonDatabaseContainer;
import fr.ippon.mase.demodulith.catalog.impl.OfferStatus;
import fr.ippon.mase.demodulith.catalog.impl.PokemonCard;
import fr.ippon.mase.demodulith.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest
class CatalogManagementIT extends SingletonDatabaseContainer {

    @Autowired
    private CatalogManagement catalogManagement;

    private PostgreSQLContainer postgresqlContainer = SingletonDatabaseContainer.getInstance();

    @Test
    public void shouldAddOffer(){
        PokemonCard pc = new PokemonCard("Bulbasaur", PokemonCard.CardType.COMMON, PokemonCard.CardSerie.SCARLET_AND_VIOLET, "046/151", "EN");
        User.UserId id = new User.UserId(UUID.randomUUID());
        Offer offer = new Offer(id, pc, 3.0, OfferStatus.CREATED);

        Offer savedOffer = catalogManagement.add(offer);

        Offer effectiveOffer = catalogManagement.get(savedOffer.getId());

        assertThat(savedOffer.getId()).isEqualTo(effectiveOffer.getId());
    }

    @Test
    public void shouldAcceptOffer(){
        PokemonCard pc = new PokemonCard("Bulbasaur", PokemonCard.CardType.COMMON, PokemonCard.CardSerie.SCARLET_AND_VIOLET, "046/151", "EN");
        User.UserId id = new User.UserId(UUID.randomUUID());
        Offer offer = new Offer(id, pc, 3.0, OfferStatus.CREATED);

        Offer savedOffer = catalogManagement.add(offer);
        catalogManagement.accept(List.of(offer.getId()));
        Offer updatedOffer = catalogManagement.get(offer.getId());

        assertThat(updatedOffer.getStatus()).isEqualTo(OfferStatus.SOLD);
    }
}
