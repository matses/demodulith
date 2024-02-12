package fr.ippon.mase.demodulith.catalog.impl;

import fr.ippon.mase.demodulith.catalog.Offer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatalogRepository {

    public Offer save(Offer offer) {
        return offer;
    }

    public Offer passToSold(List<Offer.OfferId> offer) {
        throw new UnsupportedOperationException("not implemented yet");
    }


}
