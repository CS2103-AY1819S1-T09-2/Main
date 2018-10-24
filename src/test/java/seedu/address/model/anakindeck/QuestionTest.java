package seedu.address.model.anakindeck;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import seedu.address.testutil.Assert;

public class QuestionTest {

    @Test
    public void  constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Question(null));
    }

    @Test public void constructor_invalidQuestion_throwsIllegalArgumentException() {
        String invalidQuestion = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Question(invalidQuestion));
    }

    @Test
    public void isValidQuestion() {
        // null question
        Assert.assertThrows(NullPointerException.class, () -> Question.isValidQuestion(null));

        // invalid name
        assertFalse(Question.isValidQuestion("")); // empty string
        assertFalse(Question.isValidQuestion(" ")); // spaces only
        assertFalse(Question.isValidQuestion("^")); // only non-alphanumeric characters
        assertFalse(Question.isValidQuestion("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Question.isValidQuestion("mr awesome")); // alphabets only
        assertTrue(Question.isValidQuestion("12345")); // numbers only
        assertTrue(Question.isValidQuestion("abcdefg the 1st")); // alphanumeric characters
        assertTrue(Question.isValidQuestion("Who is the king in the North")); // with capital letters
        assertTrue(Question.isValidQuestion("King in the north and dragonass 1st time")); // long names
    }
}
