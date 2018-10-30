package seedu.address.model.deck.anakinexceptions;

/**
 * Signals that the operation cannot occur when reviewing deck.
 */
public class IllegalOperationWhileReviewingDeckException extends RuntimeException {
    public IllegalOperationWhileReviewingDeckException() {
        super("Operation is not allowed when user is reviewing deck.");
    }
}
