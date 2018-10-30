package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ReviewPreviousCardEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Moves to previous card during deck review.
 */
public class PreviousCardCommand extends Command {

    public static final String COMMAND_WORD = "prevcard";
    public static final String MESSAGE_SUCCESS = "Moving to previous question";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        // TODO: check if in deck review mode

        // TODO: check if current pointer is at first card

        EventsCenter.getInstance().post(new ReviewPreviousCardEvent());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
