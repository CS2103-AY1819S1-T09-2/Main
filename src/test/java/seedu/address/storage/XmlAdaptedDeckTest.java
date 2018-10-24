package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static seedu.address.storage.AnakinXmlAdaptedDeck.MISSING_FIELD_MESSAGE_FORMAT;
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
    private static final List<AnakinXmlAdaptedCard> VALID_CARDS = DECK_A.getCards()
            .internalList.stream()
            .map(AnakinXmlAdaptedCard::new).collect(Collectors.toList());

    @Test
    public void toModelType_validDeckDetails_returnsDeck() throws Exception {
        AnakinXmlAdaptedDeck deck = new AnakinXmlAdaptedDeck(DECK_A);
        assertEquals(DECK_A, deck.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        AnakinXmlAdaptedDeck deck = new AnakinXmlAdaptedDeck(INVALID_NAME, VALID_CARDS);
        String expectedMessage = Name.MESSAGE_NAME_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, deck::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        AnakinXmlAdaptedDeck deck = new AnakinXmlAdaptedDeck(null, VALID_CARDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, deck::toModelType);
    }
}
