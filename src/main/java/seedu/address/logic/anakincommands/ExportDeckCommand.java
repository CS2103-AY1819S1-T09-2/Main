package seedu.address.logic.anakincommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.AddressbookMessages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.anakindeck.Deck;

/**
 * Exports a deck to the same folder that Anakin is in identified using it's displayed index from Anakin.
 */
public class ExportDeckCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String DEFAULT_FILEPATH = "./";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Exports the deck identified by the index number used in the displayed deck list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_DECK_SUCCESS = "Successfully exported Deck: %1$s";

    private final Index targetIndex;

    private final String filepath = DEFAULT_FILEPATH;

    public ExportDeckCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Deck> lastShownList = model.getFilteredDeckList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(AddressbookMessages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
        }

        Deck deckToExport = lastShownList.get(targetIndex.getZeroBased());
        model.exportDeck(deckToExport);
        model.commitAnakin();
        return new CommandResult(String.format(MESSAGE_DELETE_DECK_SUCCESS, deckToExport));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ExportDeckCommand // instanceof handles nulls
            && targetIndex.equals(((ExportDeckCommand) other).targetIndex)); // state check
    }
}

