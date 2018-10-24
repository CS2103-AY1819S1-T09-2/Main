package seedu.address.logic.commands.anakincommands;

import static seedu.address.logic.commands.anakincommands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.anakincommands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.anakincommands.CommandTestUtil.deleteFirstDeck;
import static seedu.address.testutil.AnakinTypicalDecks.getTypicalAnakin;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.anakincommands.RedoCommand;


import seedu.address.logic.CommandHistory;
import seedu.address.model.AnakinModel;
import seedu.address.model.AnakinModelManager;
import seedu.address.model.UserPrefs;

public class RedoCommandTest {

    private final AnakinModel model = new AnakinModelManager(getTypicalAnakin(), new UserPrefs());
    private final AnakinModel expectedModel = new AnakinModelManager(getTypicalAnakin(), new UserPrefs());
    private final CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        // set up of both models' undo/redo history
        deleteFirstDeck(model);
        deleteFirstDeck(model);
        model.undoAnakin();
        model.undoAnakin();

        deleteFirstDeck(expectedModel);
        deleteFirstDeck(expectedModel);
        expectedModel.undoAnakin();
        expectedModel.undoAnakin();
    }

    @Test
    public void execute() {
        // multiple redoable states in model
        expectedModel.redoAnakin();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // single redoable state in model
        expectedModel.redoAnakin();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // no redoable state in model
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }
}
