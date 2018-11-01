package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DECK_LEVEL_OPERATION;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.Deck;

/**
 * Exports a deck identified using it's displayed index from Anakin.
 */
public class ExportDeckCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Exports the deck identified by the index number used in the displayed deck list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_EXPORT_DECK_SUCCESS = "Successfully Exported Deck: %1$s to %2$s";

    private final Index targetIndex;

    public ExportDeckCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.isInsideDeck()) {
            throw new CommandException(MESSAGE_INVALID_DECK_LEVEL_OPERATION);
        }

        List<Deck> lastShownList = model.getFilteredDeckList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
        }

        Deck deckToExport = lastShownList.get(targetIndex.getZeroBased());
        String exportPath = model.exportDeck(deckToExport);
        System.out.println(exportPath);
        model.commitAnakin();
        return new CommandResult(String.format(MESSAGE_EXPORT_DECK_SUCCESS, deckToExport, exportPath));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ExportDeckCommand // instanceof handles nulls
            && targetIndex.equals(((ExportDeckCommand) other).targetIndex)); // state check
    }
}

