package seedu.address.model.deck;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a Card inside a Deck.
 */
public class Card {

    private final Performance performance;
    private final int timesReviewed;
    private LocalDateTime nextReviewDate;
    private double reviewScore;

    // Identity fields
    private final Question question;
    private final Answer answer;

    public static final double EASINESS = 2.5;
    public static final double DEFAULT_REVIEW_SCORE = 2.5;

    public static final double PERFORMANCE_MODERATING_FACTOR = -0.8;
    public static final double UPDATED_SQUARED_COEFFICIENT = 0.02;
    public static final double UPDATED_LINEAR_COEFFICIENT = 0.28;
    public static final double REVIEW_INTERVAL_COEFFICIENT = 6;

    private static final int CORRECT_THRESHOLD = 2;


    public Card(Question question, Answer answer) {
        requireAllNonNull(question, answer);
        this.question = question;
        this.answer = answer;
        this.reviewScore = DEFAULT_REVIEW_SCORE;
        timesReviewed = 0;
        performance = Performance.GOOD;
        this.nextReviewDate = LocalDateTime.now();

    }

    public Card(Question question, Answer answer, Performance performance, int timesReviewed, double reviewScore,
                LocalDateTime nextReviewDate) {
        requireAllNonNull(question, answer, performance, timesReviewed, nextReviewDate);
        this.question = question;
        this.answer = answer;
        this.performance = performance;
        this.timesReviewed = timesReviewed;
        this.nextReviewDate = nextReviewDate;
        this.reviewScore = reviewScore;
    }

    public Card(Card other) {
        this.question = new Question(other.getQuestion().toString());
        this.answer = new Answer(other.getAnswer().toString());
        this.performance = other.performance;
        this.timesReviewed = other.timesReviewed;
        this.nextReviewDate = other.nextReviewDate;
        this.reviewScore = other.reviewScore;
    }

    public static Card classifyCard(Card card, Performance performance) {
        int performanceAsInt = performance.ordinal();
        card.updateReviewScore(performanceAsInt);
        LocalDateTime nextReviewDate = calculateNextReviewDate(card, performance);
        return new Card(card.question, card.answer, performance, card.timesReviewed + 1,
                card.reviewScore, nextReviewDate);
    }

    private static LocalDateTime calculateNextReviewDate(Card card, Performance performance) {
        double consecutiveCorrectAnswers = card.getConsecutiveCorrect();
        double addedDays = 1;
        if(performance.isCorrect()) {
            addedDays =  REVIEW_INTERVAL_COEFFICIENT * Math.pow(card.reviewScore, consecutiveCorrectAnswers - 1);
        }
        return card.nextReviewDate.plusDays((long)addedDays);
    }
    public void updateReviewScore(int performanceAsInt) {
        reviewScore = PERFORMANCE_MODERATING_FACTOR + UPDATED_LINEAR_COEFFICIENT * performanceAsInt
                + UPDATED_SQUARED_COEFFICIENT * performanceAsInt * performanceAsInt;

    }
    public Question getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public Performance getPerformance() {
        return performance;
    }

    public int getTimesReviewed() {
        return timesReviewed;
    }

    public int getConsecutiveCorrect() {
        return (this.performance.ordinal() > CORRECT_THRESHOLD) ? timesReviewed + 1 : 0;

    }

    public LocalDateTime getNextReview() {
        return nextReviewDate;
    }

    /**
     * Returns true if 2 cards are the same, or have same question.
     */
    public boolean isSameCard(Card otherCard) {
        if (otherCard == this) {
            return true;
        }

        if (otherCard == null) {
            return false;
        }

        return otherCard.getQuestion().equals(question);
    }


    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Card)) {
            return false;
        }

        return isSameCard((Card) other);
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
