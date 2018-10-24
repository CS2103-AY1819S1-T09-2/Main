package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.model.anakindeck.AnakinDeck;
import seedu.address.model.anakindeck.Name;

/**
 * JAXB-friendly version of the Deck.
 */
public class XmlAdaptedDeck {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Deck's %s field is missing!";

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private List<XmlAdaptedCard> cards = new ArrayList<>();

    /**
     * Constructs an XmlAdaptedDeck.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedDeck() {
    }

    /**
     * Constructs an {@code XmlAdaptedDeck} with the given person details.
     */
    public XmlAdaptedDeck(String name, List<XmlAdaptedCard> cards) {
        this.name = name;
        this.cards = new ArrayList<>(cards);
    }

    /**
     * Converts a given AnakinDeck into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedDeck
     */
    public XmlAdaptedDeck(AnakinDeck source) {
        name = source.getName().fullName;
        cards = source.getCards().internalList.stream()
                .map(XmlAdaptedCard::new)
                .collect(Collectors.toList());
    }

    /**
     * Converts this jaxb-friendly adapted deck object into the model's Deck object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public AnakinDeck toModelType() throws IllegalValueException {
        final List<AnakinCard> deckCards = new ArrayList<>();
        for (XmlAdaptedCard card : cards) {
            deckCards.add(card.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_NAME_CONSTRAINTS);
        }
        final Name deckName = new Name(name);

        return new AnakinDeck(deckName, deckCards);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedDeck)) {
            return false;
        }

        XmlAdaptedDeck otherDeck = (XmlAdaptedDeck) other;
        return Objects.equals(name, otherDeck.name)
                && cards.equals(otherDeck.cards);
    }
}
