package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.anakincommands.AnakinCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Anakin;
import seedu.address.model.AnakinModel;
import seedu.address.model.anakindeck.AnakinDeck;
import seedu.address.model.anakindeck.AnakinDeckNameContainsKeywordsPredicate;

/**
 * Contains helper methods for testing commands.
 */
public class AnakinCommandTestUtil {

    public static final String VALID_NAME = "My Deck";

    public static final String INVALID_NAME = " " + PREFIX_NAME + " Bad_Deck_Name!";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";


    /**
     * Executes the given {@code command}, confirms that <br>
     * - the result message matches {@code expectedMessage} <br>
     * - the {@code actualModel} matches {@code expectedModel} <br>
     * - the {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandSuccess(AnakinCommand command,
            AnakinModel actualModel, CommandHistory actualCommandHistory,
            String expectedMessage, AnakinModel expectedModel) {
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);
        try {
            CommandResult result = command.execute(actualModel, actualCommandHistory);
            assertEquals(expectedMessage, result.feedbackToUser);
            assertEquals(expectedModel, actualModel);
            assertEquals(expectedCommandHistory, actualCommandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book and the filtered deck list in the {@code actualModel} remain unchanged <br>
     * - {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandFailure(AnakinCommand command,
            AnakinModel actualModel, CommandHistory actualCommandHistory,
            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Anakin expectedAnakin = new Anakin(actualModel.getAnakin());
        List<AnakinDeck> expectedFilteredList = new ArrayList<>(actualModel.getFilteredDeckList());

        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        try {
            command.execute(actualModel, actualCommandHistory);
            throw new AssertionError("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedAnakin, actualModel.getAnakin());
            assertEquals(expectedFilteredList, actualModel.getFilteredDeckList());
            assertEquals(expectedCommandHistory, actualCommandHistory);
        }
    }

    /**
     * Updates {@code model}'s filtered list to show only the deck at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */

    public static void showDeckAtIndex(AnakinModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredDeckList().size());

        AnakinDeck deck = model.getFilteredDeckList().get(targetIndex.getZeroBased());

        final String[] splitName = deck.getName().fullName.split("\\s+");
        model.updateFilteredDeckList(
                new AnakinDeckNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredDeckList().size());
    }

    /**
     * Deletes the first deck in {@code model}'s filtered list from {@code model}'s address book.
     */
    public static void deleteFirstDeck(AnakinModel model) {
        AnakinDeck deck = model.getFilteredDeckList().get(0);
        model.deleteDeck(deck);
        model.commitAnakin();
    }

}
