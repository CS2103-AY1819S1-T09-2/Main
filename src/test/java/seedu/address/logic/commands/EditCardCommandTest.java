package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_CANNOT_UNDO_CD_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_B;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDecks.getTypicalAnakinInDeck;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CARD;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.EditCardCommand.EditCardDescriptor;
import seedu.address.model.Anakin;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.deck.Card;
import seedu.address.testutil.CardBuilder;
import seedu.address.testutil.EditCardDescriptorBuilder;

//import static org.junit.Assert.assertNotEquals;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * EditCardCommand.
 */
public class EditCardCommandTest {

    private Model model = new ModelManager(getTypicalAnakinInDeck(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Card editedCard = new CardBuilder().build();
        EditCardDescriptor descriptor = new EditCardDescriptorBuilder(editedCard).build();
        EditCardCommand anakinEditCardCommand = new EditCardCommand(INDEX_FIRST_CARD, descriptor);

        String expectedMessage = String.format(EditCardCommand.MESSAGE_EDIT_CARD_SUCCESS, editedCard);

        Model expectedModel = new ModelManager(model.getAnakin(), new UserPrefs());
        expectedModel.updateCard(model.getFilteredCardList().get(INDEX_FIRST_CARD.getOneBased()), editedCard);
        expectedModel.commitAnakin();

        assertCommandSuccess(anakinEditCardCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastAnakinCard = Index.fromOneBased(model.getFilteredCardList().size());
        Card lastCard = model.getFilteredCardList().get(indexLastAnakinCard.getZeroBased());

        CardBuilder anakinCardInList = new CardBuilder(lastCard);
        Card editedCard = anakinCardInList
            .withQuestion(VALID_QUESTION_A).withAnswer(VALID_ANSWER_A).build();

        EditCardDescriptor descriptor = new EditCardDescriptorBuilder()
            .withQuestion(VALID_QUESTION_A)
            .withAnswer(VALID_ANSWER_A).build();
        EditCardCommand anakinEditCardCommand = new EditCardCommand(indexLastAnakinCard, descriptor);

        String expectedMessage = String.format(EditCardCommand.MESSAGE_EDIT_CARD_SUCCESS, editedCard);

        Model expectedModel = new ModelManager(new Anakin(model.getAnakin()), new UserPrefs());
        expectedModel.updateCard(lastCard, editedCard);
        expectedModel.commitAnakin();

        assertCommandSuccess(anakinEditCardCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCardCommand anakinEditCardCommand = new EditCardCommand(INDEX_FIRST_CARD, new EditCardDescriptor());
        Card editedCard = model.getFilteredCardList().get(INDEX_FIRST_CARD.getZeroBased());

        String expectedMessage = String.format(EditCardCommand.MESSAGE_EDIT_CARD_SUCCESS, editedCard);

        Model expectedModel = new ModelManager(new Anakin(model.getAnakin()), new UserPrefs());
        expectedModel.commitAnakin();

        assertCommandSuccess(anakinEditCardCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        //showCardAtIndex(model, INDEX_FIRST_CARD);

        Card cardInFilteredList = model.getFilteredCardList().get(INDEX_FIRST_CARD.getZeroBased());
        Card editedCard = new CardBuilder(cardInFilteredList).withQuestion(VALID_QUESTION_A).build();
        EditCardCommand anakinEditCardCommand = new EditCardCommand(INDEX_FIRST_CARD,
            new EditCardDescriptorBuilder().withQuestion(VALID_QUESTION_A).build());

        String expectedMessage = String.format(EditCardCommand.MESSAGE_EDIT_CARD_SUCCESS, editedCard);

        Model expectedModel = new ModelManager(new Anakin(model.getAnakin()), new UserPrefs());
        expectedModel.updateCard(model.getFilteredCardList().get(0), editedCard);
        expectedModel.commitAnakin();

        assertCommandSuccess(anakinEditCardCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAnakinCardUnfilteredList_failure() {
        Card firstCard = model.getFilteredCardList().get(INDEX_FIRST_CARD.getZeroBased());
        EditCardDescriptor descriptor = new EditCardDescriptorBuilder(firstCard).build();
        EditCardCommand anakinEditCardCommand = new EditCardCommand(INDEX_SECOND_CARD, descriptor);

        assertCommandFailure(anakinEditCardCommand, model, commandHistory, EditCardCommand.MESSAGE_DUPLICATE_CARD);
    }

    @Test
    public void execute_duplicateAnakinCardFilteredList_failure() {
        //showCardAtIndex(model, INDEX_FIRST_CARD);

        // edit anakincard in filtered list into a duplicate in address book
        Card anakincardInList = model.getAnakin().getCardList().get(INDEX_SECOND_CARD.getZeroBased());
        EditCardCommand anakinEditCardCommand = new EditCardCommand(INDEX_FIRST_CARD,
            new EditCardDescriptorBuilder(anakincardInList).build());

        assertCommandFailure(anakinEditCardCommand, model, commandHistory, EditCardCommand.MESSAGE_DUPLICATE_CARD);
    }

    @Test
    public void execute_invalidAnakinCardIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCardList().size() + 1);
        EditCardDescriptor descriptor = new EditCardDescriptorBuilder().withQuestion(VALID_QUESTION_A).build();
        EditCardCommand anakinEditCardCommand = new EditCardCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(anakinEditCardCommand, model, commandHistory,
            Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    // TODO: When filter functions are available write filter test
    //    @Test
    //    public void execute_invalidAnakinCardIndexFilteredList_failure() {
    //        showCardAtIndex(model, INDEX_FIRST_CARD);
    //        Index outOfBoundIndex = INDEX_SECOND_CARD;
    //        // ensures that outOfBoundIndex is still in bounds of address book list
    //        assertTrue(outOfBoundIndex.getZeroBased() < model.getAnakin().getCardList().size());
    //
    //        EditCardCommand anakinEditCardCommand = new EditCardCommand(outOfBoundIndex,
    //                new EditCardDescriptorBuilder().withQuestion(VALID_QUESTION_A).build());
    //
    //        assertCommandFailure(anakinEditCardCommand, model, commandHistory, AddressbookMessages
    // .MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
    //    }
    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        Card editedCard = new CardBuilder().build();
        Card anakinCardToEdit = model.getFilteredCardList().get(INDEX_FIRST_CARD.getZeroBased());
        EditCardDescriptor descriptor = new EditCardDescriptorBuilder(editedCard).build();
        EditCardCommand anakinEditCardCommand = new EditCardCommand(INDEX_FIRST_CARD, descriptor);
        Model expectedModel = new ModelManager(new Anakin(model.getAnakin()), new UserPrefs());
        expectedModel.updateCard(anakinCardToEdit, editedCard);
        expectedModel.commitAnakin();

        // edit -> first anakincard edited
        anakinEditCardCommand.execute(model, commandHistory);

        // undo -> reverts anakin back to previous state and filtered anakincard list to show all anakincards
        expectedModel.undoAnakin();
        assertCommandSuccess(new UndoCommand(), model, commandHistory,
            seedu.address.logic.commands.UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first anakincard edited again
        expectedModel.redoAnakin();
        assertCommandSuccess(new seedu.address.logic.commands.RedoCommand(), model, commandHistory,
            seedu.address.logic.commands.RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCardList().size() + 1);
        EditCardDescriptor descriptor = new EditCardDescriptorBuilder().withQuestion(VALID_QUESTION_A).build();
        EditCardCommand anakinEditCardCommand = new EditCardCommand(outOfBoundIndex, descriptor);

        // execution failed -> address book state not added into model
        assertCommandFailure(anakinEditCardCommand, model, commandHistory,
            Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);

        // single address book state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, commandHistory,
            MESSAGE_CANNOT_UNDO_CD_COMMAND);
        assertCommandFailure(new seedu.address.logic.commands.RedoCommand(), model, commandHistory,
            seedu.address.logic.commands.RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Edits a {@code Card} from a filtered list.
     * 2. Undo the edit.
     * 3. The unfiltered list should be shown now. Verify that the index of the previously edited anakincard in the
     * unfiltered list is different from the index at the filtered list.
     * 4. Redo the edit. This ensures {@code RedoCommand} edits the anakincard object regardless of indexing.
     */

    @Test
    public void executeUndoRedo_validIndexFilteredList_sameAnakinCardEdited() throws Exception {
        Card editedAnakinCard = new CardBuilder().build();
        EditCardDescriptor descriptor = new EditCardDescriptorBuilder(editedAnakinCard).build();
        EditCardCommand anakinEditCardCommand = new EditCardCommand(INDEX_FIRST_CARD, descriptor);
        Model expectedModel = new ModelManager(new Anakin(model.getAnakin()), new UserPrefs());

        //  showAnakinCardAtIndex(model, INDEX_SECOND_CARD);
        Card anakincardToEdit = model.getFilteredCardList().get(INDEX_FIRST_CARD.getZeroBased());
        expectedModel.updateCard(anakincardToEdit, editedAnakinCard);
        expectedModel.commitAnakin();

        // edit -> edits second anakincard in unfiltered anakincard list / first anakincard in filtered anakincard list
        anakinEditCardCommand.execute(model, commandHistory);

        // undo -> reverts anakin back to previous state and filtered anakincard list to show all anakincards
        expectedModel.undoAnakin();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // assertNotEquals(model.getFilteredCardList().get(INDEX_FIRST_CARD.getZeroBased()), anakincardToEdit);
        // redo -> edits same second anakincard in unfiltered anakincard list
        expectedModel.redoAnakin();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        EditCardDescriptor cardDesc = new EditCardDescriptorBuilder()
            .withAnswer(VALID_ANSWER_A).withQuestion(VALID_QUESTION_A)
            .build();
        EditCardDescriptor cardDescB = new EditCardDescriptorBuilder()
            .withAnswer(VALID_ANSWER_B).withQuestion(VALID_QUESTION_B)
            .build();
        final EditCardCommand standardCommand = new EditCardCommand(INDEX_FIRST_CARD, cardDesc);

        // same values -> returns true
        EditCardDescriptor copyDescriptor = new EditCardDescriptor(cardDesc);
        EditCardCommand commandWithSameValues = new EditCardCommand(INDEX_FIRST_CARD, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCardCommand(INDEX_SECOND_CARD, cardDesc)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCardCommand(INDEX_FIRST_CARD, cardDescB)));
    }

}
