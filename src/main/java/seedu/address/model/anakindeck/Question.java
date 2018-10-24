package seedu.address.model.anakindeck;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 *  Represents a Card's question in the deck.
 *  Guarantees: immutable; is valid as declared in {@link #isValidQuestion(String)}
 */
public class Question {
    public static final String MESSAGE_QUESTION_CONSTRAINTS =
            "Questions can contain any text inputs but it should not be blank";

    /*
     * The first character of the question must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String QUESTION_VALIDATION_REGEX = "[\\p{Graph}][\\p{Graph} ]*";

    public final String fullQuestion;

    /**
     * Constructs a {@code Question}.
     *
     * @param question A valid question.
     */
    public Question(String question) {
        requireNonNull(question);
        checkArgument(isValidQuestion(question), MESSAGE_QUESTION_CONSTRAINTS);
        fullQuestion = question;
    }

    /**
     * Returns true if a given string is a valid question.
     */
    public static boolean isValidQuestion(String test) {
        return test.matches(QUESTION_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullQuestion;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Question // instanceof handles nulls
                && fullQuestion.equals(((Question) other).fullQuestion)); // state check
    }

    @Override
    public int hashCode() {
        return fullQuestion.hashCode();
    }
}
