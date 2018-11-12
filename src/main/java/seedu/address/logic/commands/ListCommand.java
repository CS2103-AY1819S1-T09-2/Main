package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_CURRENTLY_REVIEWING_DECK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CARDS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DECKS;

import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.Card;
import seedu.address.model.deck.CardPerformanceMatchesPerformancesPredicate;


/**
 * Lists all decks / cards in the current list to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS_DECK = "Listed all decks";
    public static final String MESSAGE_SUCCESS_ALL_CARDS = "Listed all cards";
    public static final String MESSAGE_SUCCESS_PERFORMANCE_CARDS = "Listed %1$s cards";
    public static final String AUTOCOMPLETE_TEXT = COMMAND_WORD;

    private Predicate<Card> cardPredicate;
    private String MESSAGE_SUCCESS_CARD;

    public ListCommand() {
        this.cardPredicate = PREDICATE_SHOW_ALL_CARDS;
        this.MESSAGE_SUCCESS_CARD = MESSAGE_SUCCESS_ALL_CARDS;
    }

    public ListCommand(CardPerformanceMatchesPerformancesPredicate cardPerformancePredicate) {
        this.cardPredicate = cardPerformancePredicate;
        String performanceString = cardPerformancePredicate.performancesAsStrings().stream().collect(Collectors
                .joining(", ")).toLowerCase();
        this.MESSAGE_SUCCESS_CARD = String.format(MESSAGE_SUCCESS_PERFORMANCE_CARDS, performanceString);
    }


    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        if (model.isReviewingDeck()) {
            throw new CommandException(MESSAGE_CURRENTLY_REVIEWING_DECK);
        }

        if (model.isInsideDeck()) {
            model.updateFilteredCardList(cardPredicate);
            return new CommandResult(MESSAGE_SUCCESS_CARD);
        } else {
            model.updateFilteredDeckList(PREDICATE_SHOW_ALL_DECKS);
            return new CommandResult(MESSAGE_SUCCESS_DECK);
        }
    }
}
