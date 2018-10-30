package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.events.ui.StartGameplayRequestEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.Deck;

/**
 * Plays a deck inside Anakin.
 */
public class PlayCommand extends Command {

    public static final String COMMAND_WORD = "play";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Plays a deck inside Anakin. "
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_SUCCESS = "Gameplay started: %1$s";

    private final Index index;

    /**
     * Creates a PlayCommand to play a selected deck.
     * @param index of the deck in the filtered deck list to play
     */
    public PlayCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Deck> lastShownList = model.getFilteredDeckList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
        }

        Deck deckToPlay = lastShownList.get(index.getZeroBased());
        // TODO: trigger UI to change screen and select first/last known card
        EventsCenter.getInstance().post(new StartGameplayRequestEvent(deckToPlay));

        return new CommandResult(String.format(MESSAGE_SUCCESS, deckToPlay));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PlayCommand // instanceof handles nulls
                && this.index.equals(((PlayCommand) other).index));
    }
}
