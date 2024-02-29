package fr.ippon.mase.demodulith.catalog;

import fr.ippon.mase.demodulith.SingletonDatabaseContainer;
import fr.ippon.mase.demodulith.catalog.impl.OfferStatus;
import fr.ippon.mase.demodulith.catalog.impl.PokemonCard;
import fr.ippon.mase.demodulith.user.User;
import fr.ippon.mase.demodulith.user.UserDeleted;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest
class CatalogManagementIT extends SingletonDatabaseContainer {

    @Autowired
    private CatalogManagement catalogManagement;

    private PostgreSQLContainer postgresqlContainer = SingletonDatabaseContainer.getInstance();

    private Offer offer;

    @BeforeAll
    public void initFixtures(){
        PokemonCard pc = new PokemonCard("Bulbasaur", PokemonCard.CardType.COMMON, PokemonCard.CardSerie.SCARLET_AND_VIOLET, "046/151", "EN");
        User.UserId id = new User.UserId(UUID.randomUUID());
        this.offer = new Offer(id, pc, 3.0, OfferStatus.CREATED);
    }

    @Test
    public void shouldAddOffer(){

        Offer savedOffer = catalogManagement.add(offer);

        Offer effectiveOffer = catalogManagement.get(savedOffer.getId());

        assertThat(savedOffer.getId()).isEqualTo(effectiveOffer.getId());
    }

    @Test
    public void shouldAcceptOffer(){
        Offer savedOffer = catalogManagement.add(offer);
        catalogManagement.accept(List.of(offer.getId()));
        Offer updatedOffer = catalogManagement.get(offer.getId());

        assertThat(updatedOffer.getStatus()).isEqualTo(OfferStatus.SOLD);
    }

    @Test
    public void shouldRemoveOfferWhenUserIsRemoved(Scenario scenario){
        Offer savedOffer = catalogManagement.add(offer);
        List<Offer> initialOffer = catalogManagement.findByOwnerId(offer.getOwnerId());
        assertThat(initialOffer).isNotEmpty();

        scenario.publish(new UserDeleted(offer.getOwnerId().getId()))
                .andWaitForStateChange(() -> catalogManagement.findByOwnerId(offer.getOwnerId()))
                .andVerify(result -> {
                    assert result.isEmpty();
                });

    }
}
