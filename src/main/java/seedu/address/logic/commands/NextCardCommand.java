package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.FlipCardRequestEvent;
import seedu.address.commons.events.ui.ReviewNextCardEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Advances to next card during deck review.
 */
public class NextCardCommand extends Command {

    public static final String COMMAND_WORD = "nextcard";
    public static final String MESSAGE_SUCCESS = "Moving to next question";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        // TODO: check if in deck review mode

        // TODO: check if current pointer is at last card

        EventsCenter.getInstance().post(new ReviewNextCardEvent());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
