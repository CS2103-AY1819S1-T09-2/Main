package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Anakin;
import seedu.address.model.AnakinReadOnlyAnakin;
import seedu.address.model.anakindeck.AnakinDeck;

/**
 * An Immutable Anakin that is serializable to XML format
 */
@XmlRootElement(name = "anakin")
public class XmlSerializableAnakin {

    public static final String MESSAGE_DUPLICATE_DECK = "Deck list contains duplicate deck(s).";

    @XmlElement
    private List<XmlAdaptedDeck> decks;

    /**
     * Creates an empty XmlSerializableAnakin.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableAnakin() {
        decks = new ArrayList<>();
    }

    /**
     * Conversion
     */
    public XmlSerializableAnakin(AnakinReadOnlyAnakin src) {
        this();
        decks.addAll(src.getDeckList().stream().map(XmlAdaptedDeck::new).collect(Collectors.toList()));
    }

    /**
     * Converts this anakin into the model's {@code Anakin} object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     *                               {@code XmlAdaptedDeck}.
     */
    public Anakin toModelType() throws IllegalValueException {
        Anakin anakin = new Anakin();
        for (XmlAdaptedDeck d : decks) {
            AnakinDeck deck = d.toModelType();
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

        if (!(other instanceof XmlSerializableAnakin)) {
            return false;
        }
        return decks.equals(((XmlSerializableAnakin) other).decks);
    }
}
