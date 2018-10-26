package seedu.address.logic;

import javafx.collections.ObservableList;
import seedu.address.logic.anakincommands.CommandResult;
import seedu.address.logic.anakincommands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.anakindeck.Card;
import seedu.address.model.anakindeck.Deck;

/**
 * API of the AddressbookLogic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns an unmodifiable view of the filtered list of decks
     */
    ObservableList<Deck> getFilteredDeckList();

    /**
     * Returns an unmodifiable view of the filtered list of cards
     */
    ObservableList<Card> getFilteredCardList();

    /**
     * Returns the list of input entered by the user, encapsulated in a {@code ListElementPointer} object
     */
    ListElementPointer getHistorySnapshot();
}
