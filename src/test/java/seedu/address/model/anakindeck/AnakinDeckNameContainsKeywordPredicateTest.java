package seedu.address.model.anakindeck;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import seedu.address.testutil.AnakinDeckBuilder;

public class AnakinDeckNameContainsKeywordPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AnakinDeckNameContainsKeywordsPredicate firstPredicate = new AnakinDeckNameContainsKeywordsPredicate(firstPredicateKeywordList);
        AnakinDeckNameContainsKeywordsPredicate secondPredicate = new AnakinDeckNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AnakinDeckNameContainsKeywordsPredicate firstPredicateCopy = new AnakinDeckNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        AnakinDeckNameContainsKeywordsPredicate predicate = new AnakinDeckNameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new AnakinDeckBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new AnakinDeckNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new AnakinDeckBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new AnakinDeckNameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new AnakinDeckBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new AnakinDeckNameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new AnakinDeckBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        AnakinDeckNameContainsKeywordsPredicate predicate = new AnakinDeckNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new AnakinDeckBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new AnakinDeckNameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new AnakinDeckBuilder().withName("Alice Bob").build()));
    }
}
