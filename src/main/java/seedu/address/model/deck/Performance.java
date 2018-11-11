package seedu.address.model.deck;

import java.time.LocalDateTime;

/**
 * Represents a card's performance.
 */
public class Performance implements Comparable<Performance> {
    public static final double EASINESS = 2.5;
    public static final double PERFORMANCE_MODERATING_FACTOR = -0.8;

    private static final int CORRECT_THRESHOLD = 2;
    private Difficulty difficulty;
    private int timesReviewed;
    private int consecutiveCorrect;
    private LocalDateTime nextReviewDate;



    public Performance(Difficulty diff, int timesReviewed) {
        this.consecutiveCorrect = getConsecutiveCorrect();
        this.timesReviewed = timesReviewed;
        this.difficulty = diff;
        this.nextReviewDate = LocalDateTime.now();
    }

    public int getConsecutiveCorrect() {
        return (this.difficulty.ordinal() > CORRECT_THRESHOLD) ? timesReviewed + 1 : 0;
    }

    public LocalDateTime getNextReview() {
        return nextReviewDate;
    }
    public enum Difficulty {
        EASY,
        NORMAL,
        HARD;

        public static final String MESSAGE_PERFORMANCE_CONSTRAINTS =
                "Performance must be one of the strings {easy|normal|hard}";

        /**
         * Converts the provided string to a Performance type
         *
         * @param type the input string
         * @return the converted Performance type
         */
        public static Difficulty type(String type) {
            return Difficulty.valueOf(type.toUpperCase());
        }

        /**
         * Returns a boolean indicating whether the given string can be converted to a valid Difficulty level
         *
         * @param type the input string
         * @return True if type is a valid performance, false otherwise
         */
        public static boolean isValidDifficulty(String type) {
            if (type == null) {
                return false;
            }
            try {
                Difficulty dummy = Difficulty.type(type);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
    }

    @Override
    public int compareTo(Performance otherSchedule) {
        return this.nextReviewDate.compareTo(otherSchedule.getNextReview());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Performance // instanceof handles nulls
                && this.difficulty.equals(((Performance) other).difficulty))
                && this.timesReviewed == ((Performance) other).timesReviewed
                && this.consecutiveCorrect == ((Performance) other).consecutiveCorrect // consecutive correct check
                && this.nextReviewDate == ((Performance) other).nextReviewDate; // consecutive correct check

    }

}
