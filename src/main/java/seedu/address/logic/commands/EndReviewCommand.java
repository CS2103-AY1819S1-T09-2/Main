package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.EndReviewRequestEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Ends a review for a deck.
 */
public class EndReviewCommand extends Command {

    public static final String COMMAND_WORD = "endreview";
    public static final String MESSAGE_SUCCESS = "Deck review ended.";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        // TODO: check for when not in game

        EventsCenter.getInstance().post(new EndReviewRequestEvent());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
