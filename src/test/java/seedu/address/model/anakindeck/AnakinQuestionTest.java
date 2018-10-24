package seedu.address.model.anakindeck;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import seedu.address.testutil.Assert;

public class AnakinQuestionTest {

    @Test
    public void  constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new AnakinQuestion(null));
    }

    @Test public void constructor_invalidQuestion_throwsIllegalArgumentException() {
        String invalidQuestion = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new AnakinQuestion(invalidQuestion));
    }

    @Test
    public void isValidQuestion() {
        // null question
        Assert.assertThrows(NullPointerException.class, () -> AnakinQuestion.isValidQuestion(null));

        // invalid name
        assertFalse(AnakinQuestion.isValidQuestion("")); // empty string
        assertFalse(AnakinQuestion.isValidQuestion(" ")); // spaces only
        assertFalse(AnakinQuestion.isValidQuestion("^")); // only non-alphanumeric characters
        assertFalse(AnakinQuestion.isValidQuestion("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(AnakinQuestion.isValidQuestion("mr awesome")); // alphabets only
        assertTrue(AnakinQuestion.isValidQuestion("12345")); // numbers only
        assertTrue(AnakinQuestion.isValidQuestion("abcdefg the 1st")); // alphanumeric characters
        assertTrue(AnakinQuestion.isValidQuestion("Who is the king in the North")); // with capital letters
        assertTrue(AnakinQuestion.isValidQuestion("King in the north and dragonass 1st time")); // long names
    }
}
