package fr.ippon.mase.demodulith.catalog.impl;

import fr.ippon.mase.demodulith.catalog.Offer;
import org.jmolecules.ddd.integration.AssociationResolver;
import org.jmolecules.ddd.types.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface Offers extends Repository<Offer, Offer.OfferId>, AssociationResolver<Offer, Offer.OfferId> {

    public Offer save(Offer offer);

    public Optional<Offer> findById(Offer.OfferId id);

    //handling partial update...
    @Modifying
    @Transactional
    @Query("update Offer o set o.status = :status where o.id = :id")
    public void updateStatus(Offer.OfferId id, OfferStatus status);
}
