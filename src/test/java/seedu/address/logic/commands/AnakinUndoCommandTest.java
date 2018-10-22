package seedu.address.logic.commands;

import static seedu.address.logic.commands.AnakinCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.AnakinCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.AnakinCommandTestUtil.deleteFirstDeck;
import static seedu.address.testutil.AnakinTypicalDecks.getTypicalAnakin;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.anakincommands.AnakinUndoCommand;

import seedu.address.logic.CommandHistory;
import seedu.address.model.AnakinModel;
import seedu.address.model.AnakinModelManager;
import seedu.address.model.UserPrefs;

public class AnakinUndoCommandTest {

    private final AnakinModel model = new AnakinModelManager(getTypicalAnakin(), new UserPrefs());
    private final AnakinModel expectedModel = new AnakinModelManager(getTypicalAnakin(), new UserPrefs());
    private final CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        // set up of models' undo/redo history
        deleteFirstDeck(model);
        deleteFirstDeck(model);

        deleteFirstDeck(expectedModel);
        deleteFirstDeck(expectedModel);
    }

    @Test
    public void execute() {
        // multiple undoable states in model
        expectedModel.undoAnakin();
        assertCommandSuccess(new AnakinUndoCommand(), model, commandHistory, AnakinUndoCommand.MESSAGE_SUCCESS, expectedModel);

        // single undoable state in model
        expectedModel.undoAnakin();
        assertCommandSuccess(new AnakinUndoCommand(), model, commandHistory, AnakinUndoCommand.MESSAGE_SUCCESS, expectedModel);

        // no undoable states in model
        assertCommandFailure(new AnakinUndoCommand(), model, commandHistory, AnakinUndoCommand.MESSAGE_FAILURE);
    }
}
