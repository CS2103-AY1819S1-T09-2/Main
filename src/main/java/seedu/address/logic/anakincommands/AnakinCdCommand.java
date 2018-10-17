package seedu.address.logic.anakincommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnakinModel;
import seedu.address.model.anakindeck.AnakinDeck;

/**
 * Navigates into a deck identified using its displayed index from Anakin.
 */

public class AnakinCdCommand extends AnakinCommand {

    public static final String COMMAND_WORD = "cd";
    public static final String EXIT_DECK_ARGS = "..";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Navigates into the deck identified by the index number used in the displayed deck list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CD_SUCCESS = "Successfully navigated into %1$s";
    public static final String MESSAGE_EXIT_SUCCESS = "Successfully exited deck";

    private final Index targetIndex;

    private boolean noIndex;

    public AnakinCdCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.noIndex = false;
    }

    /**
     * Alternate constructor for AnakinCdCommand that does not require an Index
     */
    public AnakinCdCommand() {
        //Set targetIndex as 0.
        this.targetIndex = Index.fromZeroBased(0);
        this.noIndex = true;
    }

    @Override
    public CommandResult execute(AnakinModel anakinModel, CommandHistory history) throws CommandException {
        requireNonNull(anakinModel);
        List<AnakinDeck> lastShownList = anakinModel.getFilteredDeckList();

        if (this.noIndex) {
            //Exit the deck
            anakinModel.getOutOfDeck();
            anakinModel.commitAnakin();
            return new CommandResult(String.format(MESSAGE_EXIT_SUCCESS));
        } else {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
            }

            AnakinDeck deckToEnter = lastShownList.get(targetIndex.getZeroBased());
            anakinModel.goIntoDeck(deckToEnter);
            anakinModel.commitAnakin();
            return new CommandResult(String.format(MESSAGE_CD_SUCCESS, deckToEnter));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnakinCdCommand // instanceof handles nulls
                && targetIndex.equals(((AnakinCdCommand) other).targetIndex)); // state check
    }
}

