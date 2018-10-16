package seedu.address.model.anakindeck.anakinexceptions;

/**
 * Signals that the operation will result in duplicate Cards (Cards are considered duplicates if they have the same
 * identity).
 */
public class DuplicateCardException extends RuntimeException {
    public DuplicateCardException() {
        super("Operation would result in duplicate cards");
    }
}
