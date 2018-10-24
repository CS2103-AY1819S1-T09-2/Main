package seedu.address.storage;

import org.junit.Test;

import seedu.address.model.anakindeck.AnakinAnswer;
import seedu.address.model.anakindeck.AnakinQuestion;
import seedu.address.testutil.Assert;
import seedu.address.commons.exceptions.IllegalValueException;
import static seedu.address.testutil.AnakinTypicalCards.CARD_A;
import static org.junit.Assert.assertEquals;
import static seedu.address.storage.AnakinXmlAdaptedCard.MISSING_FIELD_MESSAGE_FORMAT;
public class AnakinXmlAdaptedCardTest {
    private static final String INVALID_QUESTION = " ";
    private static final String INVALID_ANSWER = " ";
    private static final String VALID_QUESTION = CARD_A.getQuestion().toString();
    private static final String VALID_ANSWER = CARD_A.getAnswer().toString();

    @Test
    public void toModelType_validCardDetails_returnsDeck() throws Exception {
        AnakinXmlAdaptedCard card = new AnakinXmlAdaptedCard(CARD_A);
        assertEquals(CARD_A, card.toModelType());
    }
//    TODO: There are no invalid questions yet.
//    @Test
//    public void toModelType_invalidQuestion_throwsIllegalValueException() {
//        AnakinXmlAdaptedCard card = new AnakinXmlAdaptedCard(INVALID_QUESTION, VALID_ANSWER);
//        String expectedMessage = AnakinQuestion.MESSAGE_QUESTION_CONSTRAINTS;
//        Assert.assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
//    }
    @Test
    public void toModelType_nullQuestion_throwsIllegalValueException() {
        AnakinXmlAdaptedCard card = new AnakinXmlAdaptedCard(null, VALID_ANSWER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, AnakinQuestion.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
    }
//    TODO: There are no invalid answers yet.
//    @Test
//    public void toModelType_invalidAnswer_throwsIllegalValueException() {
//        AnakinXmlAdaptedCard card = new AnakinXmlAdaptedCard(VALID_QUESTION, INVALID_ANSWER);
//        String expectedMessage = AnakinAnswer.MESSAGE_ANSWER_CONSTRAINTS;
//        Assert.assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
//    }
    @Test
    public void toModelType_nullAnswer_throwsIllegalValueException() {
        AnakinXmlAdaptedCard card = new AnakinXmlAdaptedCard(VALID_QUESTION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, AnakinAnswer.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
    }
}
