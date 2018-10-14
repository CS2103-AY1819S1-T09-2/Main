package seedu.address.model.anakindeck;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Card inside a Deck.
 */
public class AnakinCard {

    private final Performance performance;

    // Identity fields
    private final AnakinQuestion question;
    private final AnakinAnswer answer;

    public AnakinCard(AnakinQuestion question, AnakinAnswer answer) {
        requireAllNonNull(question, answer);
        this.question = question;
        this.answer = answer;
        performance = Performance.DEFAULT;
    }

    public AnakinQuestion getQuestion() {
        return question;
    }

    public AnakinAnswer getAnswer() {
        return answer;
    }

    public Performance getPerformance() {
        return performance;
    }

    /**
     * Returns true if 2 cards are the same, or have same question.
     */
    public boolean isSameCard(AnakinCard otherCard) {
        if (otherCard == this) {
            return true;
        }

        return otherCard.getQuestion().equals(question);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AnakinCard)) {
            return false;
        }

        return isSameCard((AnakinCard) other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Question: ")
                .append(question)
                .append(" Answer: ")
                .append(answer);
        return builder.toString();
    }
}
