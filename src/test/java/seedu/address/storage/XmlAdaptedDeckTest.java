package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static seedu.address.storage.XmlAdaptedDeck.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.AnakinTypicalDecks.DECK_A;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.anakindeck.Name;
import seedu.address.testutil.Assert;

public class XmlAdaptedDeckTest {
    private static final String INVALID_NAME = " ";

    private static final String VALID_NAME = DECK_A.getName().toString();
    private static final List<XmlAdaptedCard> VALID_CARDS = DECK_A.getCards()
            .internalList.stream()
            .map(XmlAdaptedCard::new).collect(Collectors.toList());

    @Test
    public void toModelType_validDeckDetails_returnsDeck() throws Exception {
        XmlAdaptedDeck deck = new XmlAdaptedDeck(DECK_A);
        assertEquals(DECK_A, deck.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        XmlAdaptedDeck deck = new XmlAdaptedDeck(INVALID_NAME, VALID_CARDS);
        String expectedMessage = Name.MESSAGE_NAME_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, deck::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        XmlAdaptedDeck deck = new XmlAdaptedDeck(null, VALID_CARDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, deck::toModelType);
    }
}
