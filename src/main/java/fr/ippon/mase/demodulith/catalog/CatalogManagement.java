package fr.ippon.mase.demodulith.catalog;

import fr.ippon.mase.demodulith.catalog.impl.Offers;
import fr.ippon.mase.demodulith.catalog.impl.OfferStatus;
import org.jmolecules.ddd.annotation.Service;

import java.util.List;

@Service
public class CatalogManagement {

    private final Offers catalogRepository;

    public CatalogManagement(Offers catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public Offer add(Offer offer) {
        return catalogRepository.save(offer);
    }

    public void accept(List<Offer.OfferId> ids) {
        ids.stream().forEach(id -> catalogRepository.updateStatus(id, OfferStatus.SOLD));
    }

    public Offer get(Offer.OfferId id) {
        return catalogRepository.findById(id).orElse(null);
    }
}
