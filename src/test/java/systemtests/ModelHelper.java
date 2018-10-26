package systemtests;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.anakindeck.Card;
import seedu.address.model.anakindeck.Deck;

/**
 * Contains helper methods to set up {@code AddressbookModel} for testing.
 */
public class ModelHelper {
    private static final Predicate<Deck> PREDICATE_MATCHING_NO_DECKS = unused -> false;
    private static final Predicate<Card> PREDICATE_MATCHING_NO_CARDS = unused -> false;

    /**
     * Updates {@code model}'s filtered list to display only {@code toDisplay}.
     */
    public static void setFilteredDeckList(Model model, List<Deck> decksToDisplay) {
        Optional<Predicate<Deck>> deckPredicate =
            decksToDisplay.stream().map(ModelHelper::getPredicateMatchingDecks).reduce(Predicate::or);
        model.updateFilteredDeckList(deckPredicate.orElse(PREDICATE_MATCHING_NO_DECKS));
    }

    /**
     * @see ModelHelper#setFilteredDeckList(Model, List)
     */
    public static void setFilteredDeckList(Model model, Deck... toDisplay) {
        setFilteredDeckList(model, Arrays.asList(toDisplay));
    }

    /**
     * Updates {@code model}'s filtered list to display only {@code toDisplay}.
     */
    public static void setFilteredCardList(Model model, List<Card> cardsToDisplay) {
        Optional<Predicate<Card>> cardPredicate =
            cardsToDisplay.stream().map(ModelHelper::getPredicateMatchingCards).reduce(Predicate::or);
        model.updateFilteredCardList(cardPredicate.orElse(PREDICATE_MATCHING_NO_CARDS));
    }

    /**
     * @see ModelHelper#setFilteredCardList(Model, List)
     */
    public static void setFilteredCardList(Model model, Card... toDisplay) {
        setFilteredCardList(model, Arrays.asList(toDisplay));
    }


    /**
     * Returns a predicate that evaluates to true if this {@code Deck} equals to {@code other}.
     */
    private static Predicate<Deck> getPredicateMatchingDecks(Deck other) {
        return deck -> deck.equals(other);
    }

    /**
     * Returns a predicate that evaluates to true if this {@code Deck} equals to {@code other}.
     */
    private static Predicate<Card> getPredicateMatchingCards(Card other) {
        return card -> card.equals(other);
    }
}
