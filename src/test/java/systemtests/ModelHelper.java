package systemtests;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.model.AnakinModel;
import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.model.anakindeck.AnakinDeck;

/**
 * Contains helper methods to set up {@code Model} for testing.
 */
public class ModelHelper {
    private static final Predicate<AnakinDeck> PREDICATE_MATCHING_NO_DECKS = unused -> false;
    private static final Predicate<AnakinCard> PREDICATE_MATCHING_NO_CARDS = unused -> false;

    /**
     * Updates {@code model}'s filtered list to display only {@code toDisplay}.
     */
    public static void setFilteredDeckList(AnakinModel model, List<AnakinDeck> decksToDisplay) {
        Optional<Predicate<AnakinDeck>> deckPredicate =
                decksToDisplay.stream().map(ModelHelper::getPredicateMatchingDecks).reduce(Predicate::or);
        model.updateFilteredDeckList(deckPredicate.orElse(PREDICATE_MATCHING_NO_DECKS));
    }

    /**
     * Updates {@code model}'s filtered list to display only {@code toDisplay}.
     */
    public static void setFilteredCardList(AnakinModel model, List<AnakinCard> cardsToDisplay) {
        Optional<Predicate<AnakinCard>> cardPredicate =
                cardsToDisplay.stream().map(ModelHelper::getPredicateMatchingCards).reduce(Predicate::or);
        model.updateFilteredCardList(cardPredicate.orElse(PREDICATE_MATCHING_NO_CARDS));
    }

    /**
     * @see ModelHelper#setFilteredDeckList(AnakinModel, List)
     */
    public static void setFilteredDeckList(AnakinModel model, AnakinDeck... toDisplay) {
        setFilteredDeckList(model, Arrays.asList(toDisplay));
    }

    /**
     * @see ModelHelper#setFilteredCardList(AnakinModel, List)
     */
    public static void setFilteredCardList(AnakinModel model, AnakinCard... toDisplay) {
        setFilteredCardList(model, Arrays.asList(toDisplay));
    }

    /**
     * Returns a predicate that evaluates to true if this {@code AnakinDeck} equals to {@code other}.
     */
    private static Predicate<AnakinDeck> getPredicateMatchingDecks(AnakinDeck other) {
        return deck -> deck.equals(other);
    }

    /**
     * Returns a predicate that evaluates to true if this {@code AnakinDeck} equals to {@code other}.
     */
    private static Predicate<AnakinCard> getPredicateMatchingCards(AnakinCard other) {
        return card -> card.equals(other);
    }
}
