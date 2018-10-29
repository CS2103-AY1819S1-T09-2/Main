package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.deck.Card;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.Name;

/**
 * Exportable verion of deck.
 */

@XmlRootElement(name = "deck")
public class XmlExportableDeck {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Deck's %s field is missing!";

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private List<XmlAdaptedCard> cards;


    /**
     * Creates an empty XmlExportableDeck
     * This empty constructor is required for marshalling.
     */

    public XmlExportableDeck() {
        name = "Generic Deck Name";
        cards = new ArrayList<>();
    }

    /**
     * Creates an XmlExportableDeck with the specified name and Card list
     * This empty constructor is required for marshalling.
     */
    public XmlExportableDeck(String strName, List<XmlAdaptedCard> cardsList) {
        this();
        name = strName;
        cards = cardsList;
    }

    /**
     * Converts a given Deck into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlExportableDeck
     */
    public XmlExportableDeck(Deck source) {
        this();
        name = source.getName().fullName;
        cards = source.getCards().internalList.stream()
            .map(XmlAdaptedCard::new)
            .collect(Collectors.toList());
    }

    /**
     * Converts this XmlExportableDeck into the model's {@code Deck} object.
     *
     * @throws IllegalValueException if there were any data constraints violated or missing values.
     */

    public Deck toModelType() throws IllegalValueException {
        final List<Card> deckCards = new ArrayList<>();
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
        return new Deck(deckName, deckCards);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlExportableDeck)) {
            return false;
        }

        XmlExportableDeck otherDeck = (XmlExportableDeck) other;
        return Objects.equals(name, otherDeck.name)
            && cards.equals(otherDeck.cards);
    }
}
