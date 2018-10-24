package seedu.address.model.anakindeck;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import seedu.address.testutil.Assert;

public class AnakinAnswerTest {
    @Test
    public void  constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new AnakinAnswer(null));
    }

    @Test public void constructor_invalidAnswer_throwsIllegalArgumentException() {
        String invalidAnswer = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new AnakinAnswer(invalidAnswer));
    }

    @Test
    public void isValidAnswer() {
        // null question
        Assert.assertThrows(NullPointerException.class, () -> AnakinAnswer.isValidAnswer(null));

        // invalid name
        assertFalse(AnakinAnswer.isValidAnswer("")); // empty string
        assertFalse(AnakinAnswer.isValidAnswer(" ")); // spaces only
        assertFalse(AnakinAnswer.isValidAnswer("^")); // only non-alphanumeric characters
        assertFalse(AnakinAnswer.isValidAnswer("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(AnakinAnswer.isValidAnswer("mr awesome")); // alphabets only
        assertTrue(AnakinAnswer.isValidAnswer("12345")); // numbers only
        assertTrue(AnakinAnswer.isValidAnswer("what the 1st")); // alphanumeric characters
        assertTrue(AnakinAnswer.isValidAnswer("John Snow")); // with capital letters
        assertTrue(AnakinAnswer.isValidAnswer("King in the north and dragonass 2nd time")); // long names
    }
}
