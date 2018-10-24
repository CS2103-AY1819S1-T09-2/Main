package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Anakin;
import seedu.address.model.ReadOnlyAnakin;
import seedu.address.model.anakindeck.Deck;

/**
 * An Immutable Anakin that is serializable to XML format
 */
@XmlRootElement(name = "anakin")
public class AnakinXmlSerializableAnakin {

    public static final String MESSAGE_DUPLICATE_DECK = "Deck list contains duplicate deck(s).";

    @XmlElement
    private List<AnakinXmlAdaptedDeck> decks;

    /**
     * Creates an empty XmlSerializableAnakin.
     * This empty constructor is required for marshalling.
     */
    public AnakinXmlSerializableAnakin() {
        decks = new ArrayList<>();
    }

    /**
     * Conversion
     */
    public AnakinXmlSerializableAnakin(ReadOnlyAnakin src) {
        this();
        decks.addAll(src.getDeckList().stream().map(AnakinXmlAdaptedDeck::new).collect(Collectors.toList()));
    }

    /**
     * Converts this anakin into the model's {@code Anakin} object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     *                               {@code AnakinXmlAdaptedDeck}.
     */
    public Anakin toModelType() throws IllegalValueException {
        Anakin anakin = new Anakin();
        for (AnakinXmlAdaptedDeck d : decks) {
            Deck deck = d.toModelType();
            if (anakin.hasDeck(deck)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DECK);
            }
            anakin.addDeck(deck);
        }
        return anakin;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AnakinXmlSerializableAnakin)) {
            return false;
        }
        return decks.equals(((AnakinXmlSerializableAnakin) other).decks);
    }
}
