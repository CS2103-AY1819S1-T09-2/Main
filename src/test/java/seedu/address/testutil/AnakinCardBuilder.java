package seedu.address.testutil;

import java.util.Queue;

import seedu.address.model.anakindeck.AnakinAnswer;
import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.model.anakindeck.AnakinQuestion;

/**
 * A utility class to help with building AnakinCard objects.
 */
public class AnakinCardBuilder {

    public static final String DEFAULT_QUESTION = "This is a default question";
    public static final String DEFAULT_ANSWER = "Default answer for default question lmao";

    private AnakinQuestion question;
    private AnakinAnswer answer;

    public AnakinCardBuilder() {
        question = new AnakinQuestion(DEFAULT_QUESTION);
        answer = new AnakinAnswer(DEFAULT_ANSWER);
    }

    /**
     * Initializes the CardBuilder with the data of {@code cardToCopy}.
     */
    public AnakinCardBuilder(AnakinCard cardToCopy) {
        question = cardToCopy.getQuestion();
        answer = cardToCopy.getAnswer();
    }

    /**
     * Sets the {@code Question} of the {@code AnakinCard} that we are building.
     */
    public AnakinCardBuilder withQuestion(String question) {
        this.question = new AnakinQuestion(question);
        return this;
    }

    /**
     * Sets the {@code Answer} of the {@code AnakinCard} that we are building.
     */
    public AnakinCardBuilder withAnswer(String answer) {
        this.answer = new AnakinAnswer(answer);
        return this;
    }

    public AnakinCard build() {
        return new AnakinCard(question, answer);
    }

}
