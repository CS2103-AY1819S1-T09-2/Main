package seedu.address.testutil;

import seedu.address.model.anakindeck.Answer;
import seedu.address.model.anakindeck.Card;
import seedu.address.model.anakindeck.Question;

/**
 * A utility class to help with building Card objects.
 */
public class AnakinCardBuilder {

    public static final String DEFAULT_QUESTION = "This is a default question";
    public static final String DEFAULT_ANSWER = "Default answer for default question lmao";

    private Question question;
    private Answer answer;

    public AnakinCardBuilder() {
        question = new Question(DEFAULT_QUESTION);
        answer = new Answer(DEFAULT_ANSWER);
    }

    /**
     * Initializes the CardBuilder with the data of {@code cardToCopy}.
     */
    public AnakinCardBuilder(Card cardToCopy) {
        question = cardToCopy.getQuestion();
        answer = cardToCopy.getAnswer();
    }

    /**
     * Sets the {@code Question} of the {@code Card} that we are building.
     */
    public AnakinCardBuilder withQuestion(String question) {
        this.question = new Question(question);
        return this;
    }

    /**
     * Sets the {@code Answer} of the {@code Card} that we are building.
     */
    public AnakinCardBuilder withAnswer(String answer) {
        this.answer = new Answer(answer);
        return this;
    }

    public Card build() {
        return new Card(question, answer);
    }

}
