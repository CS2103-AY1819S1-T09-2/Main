package seedu.address.model.anakindeck;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import seedu.address.testutil.Assert;

public class AnswerTest {
    @Test
    public void  constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Answer(null));
    }

    @Test public void constructor_invalidAnswer_throwsIllegalArgumentException() {
        String invalidAnswer = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Answer(invalidAnswer));
    }

    @Test
    public void isValidAnswer() {
        // null question
        Assert.assertThrows(NullPointerException.class, () -> Answer.isValidAnswer(null));

        // invalid name
        assertFalse(Answer.isValidAnswer("")); // empty string
        assertFalse(Answer.isValidAnswer(" ")); // spaces only
        assertFalse(Answer.isValidAnswer("^")); // only non-alphanumeric characters
        assertFalse(Answer.isValidAnswer("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Answer.isValidAnswer("mr awesome")); // alphabets only
        assertTrue(Answer.isValidAnswer("12345")); // numbers only
        assertTrue(Answer.isValidAnswer("what the 1st")); // alphanumeric characters
        assertTrue(Answer.isValidAnswer("John Snow")); // with capital letters
        assertTrue(Answer.isValidAnswer("King in the north and dragonass 2nd time")); // long names
    }
}
