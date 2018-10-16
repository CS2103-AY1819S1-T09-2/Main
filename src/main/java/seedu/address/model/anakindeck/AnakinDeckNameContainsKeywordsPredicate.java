package seedu.address.model.anakindeck;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code AnakinDeck}'s {@code Name} matches any of the keywords given.
 */
public class AnakinDeckNameContainsKeywordsPredicate implements Predicate<AnakinDeck> {
    private final List<String> keywords;

    public AnakinDeckNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(AnakinDeck deck) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(deck.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnakinDeckNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AnakinDeckNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
