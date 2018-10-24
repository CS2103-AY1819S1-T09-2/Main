package seedu.address.logic.anakincommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.AnakinModel.PREDICATE_SHOW_ALL_CARDS;
import static seedu.address.model.AnakinModel.PREDICATE_SHOW_ALL_DECKS;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.anakincommands.exceptions.CommandException;
import seedu.address.model.AnakinModel;

/**
 * Reverts the {@code model}'s address book to its previous state.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo success!";
    public static final String MESSAGE_FAILURE = "No more commands to undo!";

    @Override
    public CommandResult execute(AnakinModel model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoAnakin()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.undoAnakin();
        model.updateFilteredCardList(PREDICATE_SHOW_ALL_CARDS);
        model.updateFilteredDeckList(PREDICATE_SHOW_ALL_DECKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
