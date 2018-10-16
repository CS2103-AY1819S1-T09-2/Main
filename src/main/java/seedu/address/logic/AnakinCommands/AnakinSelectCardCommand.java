package seedu.address.logic.AnakinCommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnakinModel;
import seedu.address.model.anakindeck.AnakinCard;

/**
 * Selects a person identified using it's displayed index from the address book.
 */
public class AnakinSelectCardCommand extends AnakinCommand {

    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SELECT_CARD_SUCCESS = "Selected Person: %1$s";

    private final Index targetIndex;

    public AnakinSelectCardCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(AnakinModel model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        List<AnakinCard> filteredCardList = model.getFilteredCardList();

        if (targetIndex.getZeroBased() >= filteredCardList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
        }

        EventsCenter.getInstance().post(new JumpToListRequestEvent(targetIndex));
        return new CommandResult(String.format(MESSAGE_SELECT_CARD_SUCCESS, targetIndex.getOneBased()));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnakinSelectCardCommand // instanceof handles nulls
                && targetIndex.equals(((AnakinSelectCardCommand) other).targetIndex)); // state check
    }
}
