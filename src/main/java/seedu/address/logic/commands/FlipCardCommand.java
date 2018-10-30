package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.FlipCardRequestEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Flips a card during deck review.
 */
public class FlipCardCommand extends Command {

    public static final String COMMAND_WORD = "flipcard";
    public static final String MESSAGE_SUCCESS = "Card flipped!";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        // TODO: check if in deck review mode

        EventsCenter.getInstance().post(new FlipCardRequestEvent());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
