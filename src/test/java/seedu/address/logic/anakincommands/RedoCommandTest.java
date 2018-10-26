package seedu.address.logic.anakincommands;

import static seedu.address.logic.anakincommands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.anakincommands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.anakincommands.CommandTestUtil.deleteFirstDeck;
import static seedu.address.testutil.TypicalDecks.getTypicalAnakin;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class RedoCommandTest {

    private final Model model = new ModelManager(getTypicalAnakin(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAnakin(), new UserPrefs());
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
