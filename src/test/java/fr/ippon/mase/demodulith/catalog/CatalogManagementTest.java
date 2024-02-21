package fr.ippon.mase.demodulith.catalog;

import fr.ippon.mase.demodulith.catalog.impl.Offers;
import fr.ippon.mase.demodulith.catalog.impl.OfferStatus;
import fr.ippon.mase.demodulith.catalog.impl.PokemonCard;
import fr.ippon.mase.demodulith.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogManagementTest {

    @Mock
    private Offers catalogRepository;

    @InjectMocks
    private CatalogManagement catalogManagement;

    @Test
    public void shouldAddOffer() {
        PokemonCard pc = new PokemonCard("Bulbasaur", PokemonCard.CardType.COMMON, PokemonCard.CardSerie.SCARLET_AND_VIOLET, "046/151", "EN");
        User.UserId id = new User.UserId(UUID.randomUUID());
        Offer offer = new Offer(id, pc, 3.0, OfferStatus.CREATED);
        when(catalogRepository.save(isA(Offer.class))).thenReturn(offer);

        var effectiveProduct = catalogManagement.add(offer);

        assertThat(effectiveProduct).isEqualTo(effectiveProduct);
    }

}