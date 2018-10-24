package seedu.address.model.anakindeck;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 *  Represents a Card's answer in the deck.
 *  Guarantees: immutable; is valid as declared in {@link #isValidAnswer(String)}
 */
public class Answer {
    public static final String MESSAGE_ANSWER_CONSTRAINTS =
            "Answers can contain any text inputs but it should not be blank";

    /*
     * The first character of the answer must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String ANSWER_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullAnswer;

    /**
     * Constructs a {@code Answer}.
     *
     * @param answer A valid answer.
     */
    public Answer(String answer) {
        requireNonNull(answer);
        checkArgument(isValidAnswer(answer), MESSAGE_ANSWER_CONSTRAINTS);
        fullAnswer = answer;
    }

    /**
     * Returns true if a given string is a valid answer.
     */
    public static boolean isValidAnswer(String test) {
        // TODO: Update validation regex
        // return test.matches(ANSWER_VALIDATION_REGEX);
        return true;
    }

    @Override
    public String toString() {
        return fullAnswer;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Answer // instanceof handles nulls
                && fullAnswer.equals(((Answer) other).fullAnswer)); // state check
    }

    @Override
    public int hashCode() {
        return fullAnswer.hashCode();
    }
}
