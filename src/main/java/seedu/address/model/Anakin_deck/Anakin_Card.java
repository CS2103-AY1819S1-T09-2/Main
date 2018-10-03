package seedu.address.model.Anakin_deck;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Card inside a Deck.
 */
public class Anakin_Card {

    private final int performance;

    // Identity fields
    private final AnakinQuestion question;
    private final AnakinAnswer answer;

    public Anakin_Card(AnakinQuestion question, AnakinAnswer answer) {
        requireAllNonNull(question, answer);
        this.question = question;
        this.answer = answer;
        performance = 0;
    }

    public AnakinQuestion getQuestion() {
        return question;
    }

    public AnakinAnswer getAnswer() {
        return answer;
    }

    public int getPerformance() {
        return performance;
    }

    /**
     * Returns true if 2 cards are the same, or have same identity fields.
     */
    public boolean isSameCard(Anakin_Card otherCard) {
        if (otherCard == this) {
            return true;
        }

        return otherCard.getQuestion().equals(getQuestion())
                && otherCard.getAnswer().equals(getAnswer());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Anakin_Card)) {
            return false;
        }

        return isSameCard((Anakin_Card) other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Question: ")
                .append(getQuestion())
                .append(" Answer: ")
                .append(getAnswer());
        return builder.toString();
    }
}
