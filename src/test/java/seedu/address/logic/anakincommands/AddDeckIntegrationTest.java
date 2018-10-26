package seedu.address.logic.commands;

import static seedu.address.logic.anakincommands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.anakincommands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDecks.getTypicalAnakin;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.anakincommands.NewDeckCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.anakindeck.Deck;
import seedu.address.testutil.DeckBuilder;

/**
 * Contains integration tests (interaction with the AddressbookModel) for {@code AddCommand}.
 */
public class AddDeckIntegrationTest {

    private Model anakinModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        anakinModel = new ModelManager(getTypicalAnakin(), new UserPrefs());
    }

    @Test
    public void execute_newDeck_success() {
        Deck validDeck = new DeckBuilder().build();

        Model expectedAnakinModel = new ModelManager(anakinModel.getAnakin(), new UserPrefs());
        expectedAnakinModel.addDeck(validDeck);
        expectedAnakinModel.commitAnakin();

        assertCommandSuccess(new NewDeckCommand(validDeck), anakinModel, commandHistory,
                String.format(NewDeckCommand.MESSAGE_SUCCESS, validDeck), expectedAnakinModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Deck deckInList = anakinModel.getAnakin().getDeckList().get(0);
        assertCommandFailure(new NewDeckCommand(deckInList), anakinModel, commandHistory,
                NewDeckCommand.MESSAGE_DUPLICATE_DECK);
    }

}
