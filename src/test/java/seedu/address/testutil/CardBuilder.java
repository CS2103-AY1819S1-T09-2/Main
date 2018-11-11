package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.deck.Answer;
import seedu.address.model.deck.Card;
import seedu.address.model.deck.Performance;
import seedu.address.model.deck.Question;

/**
 * A utility class to help with building Card objects.
 */
public class CardBuilder {

    public static final String DEFAULT_QUESTION = "This is a default question";
    public static final String DEFAULT_ANSWER = "Default answer for default question lmao";
    public static final Performance DEFAULT_PERFORMANCE = Performance.NORMAL;
    public static final int DEFAULT_TIMES_REVIEWED = 0;
    public static final int DEFAULT_REVIEW_SCORE = 0;
    public static final LocalDateTime DEFAULT_NEXT_REVIEW = LocalDateTime.now();



    private Question question;
    private Answer answer;
    private Performance performance;
    private int timesReviewed;
    private double reviewScore;
    private LocalDateTime nextReview;


    public CardBuilder() {
        question = new Question(DEFAULT_QUESTION);
        answer = new Answer(DEFAULT_ANSWER);
        performance = DEFAULT_PERFORMANCE;
        timesReviewed = DEFAULT_TIMES_REVIEWED;
        reviewScore = DEFAULT_REVIEW_SCORE;
        nextReview = DEFAULT_NEXT_REVIEW;


    }

    /**
     * Initializes the CardBuilder with the data of {@code cardToCopy}.
     */
    public CardBuilder(Card cardToCopy) {
        question = cardToCopy.getQuestion();
        answer = cardToCopy.getAnswer();
        performance = cardToCopy.getPerformance();
        timesReviewed = cardToCopy.getTimesReviewed();
    }

    /**
     * Sets the {@code Question} of the {@code Card} that we are building.
     */
    public CardBuilder withQuestion(String question) {
        this.question = new Question(question);
        return this;
    }

    /**
     * Sets the {@code Answer} of the {@code Card} that we are building.
     */
    public CardBuilder withAnswer(String answer) {
        this.answer = new Answer(answer);
        return this;
    }

    /**
     * Sets the {@code Performance} of the {@code Card} that we are building
     */
    public CardBuilder withPerformance(Performance performance) {
        this.performance = performance;
        return this;
    }

    /**
     * Sets the {@code timesReviewed} of the {@code Card} that we are building
     */
    public CardBuilder withTimesReviewed(int timesReviewed) {
        this.timesReviewed = timesReviewed;
        return this;
    }

    /**
     * Sets the {@code reviewScore} of the {@code Card} that we are building
     */
    public CardBuilder withReviewScore(double reviewScore) {
        this.reviewScore = reviewScore;
        return this;
    }
    /**
     * Sets the {@code nextReviewDate} of the {@code Card} that we are building
     */
    public CardBuilder withNextReviewDate(LocalDateTime nextReviewDate) {
        this.nextReview = nextReviewDate;
        return this;
    }

    public Card build() {
        return new Card(question, answer, performance, timesReviewed, reviewScore, nextReview);
    }

}
