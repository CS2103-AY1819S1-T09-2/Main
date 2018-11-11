package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static seedu.address.storage.XmlAdaptedCard.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.TypicalCards.CARD_A;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.deck.Answer;
import seedu.address.model.deck.Question;
import seedu.address.testutil.Assert;

public class XmlAdaptedCardTest {
    private static final String INVALID_QUESTION = " ";
    private static final String INVALID_ANSWER = " ";
    private static final String VALID_QUESTION = CARD_A.getQuestion().toString();
    private static final String VALID_ANSWER = CARD_A.getAnswer().toString();

    @Test
    public void toModelType_validCardDetails_returnsDeck() throws Exception {
        XmlAdaptedCard card = new XmlAdaptedCard(CARD_A);
        assertEquals(CARD_A, card.toModelType());
    }

    @Test
    public void toModelType_invalidQuestion_throwsIllegalValueException() {
        XmlAdaptedCard card = new XmlAdaptedCard(INVALID_QUESTION, VALID_ANSWER);
        String expectedMessage = Question.MESSAGE_QUESTION_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
    }

    @Test
    public void toModelType_nullQuestion_throwsIllegalValueException() {
        XmlAdaptedCard card = new XmlAdaptedCard(null, VALID_ANSWER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Question.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
    }

    @Test
    public void toModelType_invalidAnswer_throwsIllegalValueException() {
        XmlAdaptedCard card = new XmlAdaptedCard(VALID_QUESTION, INVALID_ANSWER);
        String expectedMessage = Answer.MESSAGE_ANSWER_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
    }

    @Test
    public void toModelType_nullAnswer_throwsIllegalValueException() {
        XmlAdaptedCard card = new XmlAdaptedCard(VALID_QUESTION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
    }
}