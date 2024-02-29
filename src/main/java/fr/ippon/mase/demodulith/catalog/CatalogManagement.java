package fr.ippon.mase.demodulith.catalog;

import fr.ippon.mase.demodulith.catalog.impl.Offers;
import fr.ippon.mase.demodulith.catalog.impl.OfferStatus;
import fr.ippon.mase.demodulith.user.User;
import fr.ippon.mase.demodulith.user.UserDeleted;
import org.jmolecules.ddd.annotation.Service;
import org.jmolecules.ddd.types.Association;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogManagement {

    private final Offers catalogRepository;

    private final ApplicationEventPublisher events;


    public CatalogManagement(Offers catalogRepository, ApplicationEventPublisher events) {
        this.catalogRepository = catalogRepository;
        this.events = events;
    }

    public Offer add(Offer offer) {
        return catalogRepository.save(offer);
    }

    @Transactional
    public void accept(List<Offer.OfferId> ids) {
        ids.forEach(id -> {
            catalogRepository.updateStatus(id, OfferStatus.SOLD);
        });

    }

    public Offer get(Offer.OfferId id) {
        return catalogRepository.findById(id).orElse(null);
    }

    @ApplicationModuleListener
    public void on(UserDeleted event){
        catalogRepository.deleteByOwnerId(Association.forId((User.UserId) event.getSource()));
    }

    public List<Offer> findByOwnerId(Association<User, User.UserId> ownerId){
        return catalogRepository.findByOwnerId(ownerId);
    }
}
