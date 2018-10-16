package seedu.address.logic.anakincommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.model.AnakinModel.PREDICATE_SHOW_ALL_CARDS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.AnakinMessages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;

import seedu.address.model.AnakinModel;
import seedu.address.model.anakindeck.AnakinAnswer;
import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.model.anakindeck.AnakinQuestion;

/**
 * Edits the details of an existing card in a deck.
 */
public class AnakinEditCardCommand extends AnakinCommand {

    public static final String COMMAND_WORD = "editcard";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the card "
            + "by the index number used in the deck. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_QUESTION + "QUESTION] "
            + "[" + PREFIX_ANSWER + "ANSWER]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_QUESTION + "Why is Earth round?";

    public static final String MESSAGE_EDIT_CARD_SUCCESS = "Edited Card: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CARD = "This card already exists in the deck.";

    private final Index index;
    private final EditCardDescriptor editCardDescriptor;

    /**
     * @param index of the card in the deck to edit
     * @param editCardDescriptor details to edit the card with
     */
    public AnakinEditCardCommand(Index index, EditCardDescriptor editCardDescriptor) {
        requireNonNull(index);
        requireNonNull(editCardDescriptor);

        this.index = index;
        this.editCardDescriptor = new EditCardDescriptor(editCardDescriptor);
    }

    @Override
    public CommandResult execute(AnakinModel model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<AnakinCard> lastShownList = model.getFilteredCardList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(AnakinMessages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
        }

        AnakinCard cardToEdit = lastShownList.get(index.getZeroBased());
        AnakinCard editedCard = createEditedCard(cardToEdit, editCardDescriptor);

        if (!cardToEdit.isSameCard(editedCard) && model.hasCard(editedCard)) {
            throw new CommandException(MESSAGE_DUPLICATE_CARD);
        }

        model.updateCard(cardToEdit, editedCard);
        model.updateFilteredCardList(PREDICATE_SHOW_ALL_CARDS);
        // TODO: Check that card changes are saved when committing
        model.commitAnakin();
        return new CommandResult(String.format(MESSAGE_EDIT_CARD_SUCCESS, editedCard));
    }

    /**
     * Creates and returns a {@code Card} with the details of {@code cardToEdit}
     * edited with {@code editCardDescriptor}.
     */
    private static AnakinCard createEditedCard(AnakinCard cardToEdit, EditCardDescriptor editCardDescriptor) {
        assert cardToEdit != null;

        AnakinQuestion updatedQuestion = editCardDescriptor.getQuestion().orElse(cardToEdit.getQuestion());
        AnakinAnswer updatedAnswer = editCardDescriptor.getAnswer().orElse(cardToEdit.getAnswer());

        return new AnakinCard(updatedQuestion, updatedAnswer);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AnakinEditCardCommand)) {
            return false;
        }

        // state check
        AnakinEditCardCommand e = (AnakinEditCardCommand) other;
        return index.equals(e.index)
                && editCardDescriptor.equals(e.editCardDescriptor);
    }

    /**
     * Stores the details to edit the card with. Each non-empty field value will replace the
     * corresponding field value of the card.
     */
    public static class EditCardDescriptor {
        private AnakinQuestion question;
        private AnakinAnswer answer;

        public EditCardDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditCardDescriptor(EditCardDescriptor toCopy) {
            setQuestion(toCopy.question);
            setAnswer(toCopy.answer);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(question, answer);
        }

        public void setQuestion(AnakinQuestion question) {
            this.question = question;
        }

        public Optional<AnakinQuestion> getQuestion() {
            return Optional.ofNullable(question);
        }

        public void setAnswer(AnakinAnswer answer) {
            this.answer = answer;
        }

        public Optional<AnakinAnswer> getAnswer() {
            return Optional.ofNullable(answer);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCardDescriptor)) {
                return false;
            }

            // state check
            EditCardDescriptor e = (EditCardDescriptor) other;

            return getQuestion().equals(e.getQuestion())
                    && getAnswer().equals(e.getAnswer());
        }
    }
}
