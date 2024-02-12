package fr.ippon.mase.demodulith.catalog;

import fr.ippon.mase.demodulith.catalog.impl.PokemonCard;
import fr.ippon.mase.demodulith.catalog.impl.StatusOffer;
import fr.ippon.mase.demodulith.user.User;
import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Association;
import org.jmolecules.ddd.types.Identifier;

import java.util.UUID;

public class Offer implements AggregateRoot<Offer, Offer.OfferId> {

    private final OfferId id;

    private final Association<User, User.UserId> ownerId;

    private final double price;

    private final StatusOffer status;

    private final PokemonCard pokemonCard;

    public record OfferId(UUID id) implements Identifier {
    }

    public Offer(User.UserId ownerId, PokemonCard pokemonCard, double price, StatusOffer status) {
        this.id = new OfferId(UUID.randomUUID());
        this.ownerId = Association.forId(ownerId);
        this.pokemonCard = pokemonCard;
        this.price = price;
        this.status = status;
    }

    @Override
    public OfferId getId() {
        return id;
    }

    public Association<User, User.UserId> getOwnerId() {
        return ownerId;
    }
}
