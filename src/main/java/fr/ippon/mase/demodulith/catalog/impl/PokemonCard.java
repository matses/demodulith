package fr.ippon.mase.demodulith.catalog.impl;

import fr.ippon.mase.demodulith.catalog.Offer;
import org.jmolecules.ddd.types.Identifier;

import java.util.UUID;

public class PokemonCard implements org.jmolecules.ddd.types.Entity<Offer, PokemonCard.PokemonCardId> {

    private final PokemonCardId id;
    private final String name;
    private final CardType type;
    private final CardSerie extension;

    private final String number;
    private final String lang;

    @Override
    public PokemonCardId getId() {
        return null;
    }

    public enum CardRarity {
        COMMON, LESS_COMMON, RARE
    }

    public enum CardSerie {
        SCARLET_AND_VIOLET, SWORD_AND_SHIELD, SUN_AND_MOON, XY, BLACK_AND_WHITE, CALL_OF_LEGENDS, HEARTGOLD_SOULSILVER, PLATINUM, DIAMOND_AND_PEARL, EX,
    }

    public enum CardType {
        EX, STAR, IV_X, LEGEND, PRIME, BREAK, GX, PRISM_STAR, V, VSTAR
    }

    public record PokemonCardId(UUID id) implements Identifier {
    }

    public PokemonCard(String name, CardType type, CardSerie extension, String number, String lang) {
        this.name = name;
        this.type = type;
        this.extension = extension;
        this.number = number;
        this.lang = lang;
        this.id = new PokemonCardId(UUID.randomUUID());
    }

    public String getName() {
        return name;
    }

    public CardType getType() {
        return type;
    }

    public CardSerie getExtension() {
        return extension;
    }

    public String getNumber() {
        return number;
    }

    public String getLang() {
        return lang;
    }


}