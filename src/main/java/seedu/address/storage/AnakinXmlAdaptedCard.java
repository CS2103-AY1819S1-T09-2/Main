package seedu.address.storage;

import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.anakindeck.Answer;
import seedu.address.model.anakindeck.Card;
import seedu.address.model.anakindeck.Question;

/**
 * JAXB-friendly version of the Card.
 */
public class AnakinXmlAdaptedCard {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Card's %s field is missing!";

    @XmlElement(required = true)
    private String question;
    @XmlElement(required = true)
    private String answer;

    /**
     * Constructs an AnakinXmlAdaptedCard.
     * This is the no-arg constructor that is required by JAXB.
     */
    public AnakinXmlAdaptedCard() {
    }

    /**
     * Constructs an {@code AnakinXmlAdaptedCard} with the given person details.
     */
    public AnakinXmlAdaptedCard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    /**
     * Converts a given Card into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created AnakinXmlAdaptedCard
     */
    public AnakinXmlAdaptedCard(Card source) {
        question = source.getQuestion().fullQuestion;
        answer = source.getAnswer().fullAnswer;
    }

    /**
     * Converts this jaxb-friendly adapted card object into the model's Card object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Card toModelType() throws IllegalValueException {
        if (question == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Question.class.getSimpleName()));
        }
        if (!Question.isValidQuestion(question)) {
            throw new IllegalValueException(Question.MESSAGE_QUESTION_CONSTRAINTS);
        }

        final Question cardQuestion = new Question(question);

        if (answer == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Answer.class.getSimpleName()));
        }
        if (!Answer.isValidAnswer(answer)) {
            throw new IllegalValueException(Answer.MESSAGE_ANSWER_CONSTRAINTS);
        }

        final Answer cardAnswer = new Answer(answer);

        return new Card(cardQuestion, cardAnswer);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AnakinXmlAdaptedCard)) {
            return false;
        }

        AnakinXmlAdaptedCard otherCard = (AnakinXmlAdaptedCard) other;
        return Objects.equals(question, otherCard.question)
                && Objects.equals(answer, otherCard.answer);
    }
}