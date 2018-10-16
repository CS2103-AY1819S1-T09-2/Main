package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.ObservableList;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.anakincommands.AnakinNewDeckCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Anakin;
import seedu.address.model.AnakinModel;
import seedu.address.model.AnakinReadOnlyAnakin;
import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.model.anakindeck.AnakinDeck;
import seedu.address.testutil.AnakinDeckBuilder;


public class AnakinNewDeckCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AnakinNewDeckCommand(null);
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        AnakinDeck validDeck = new AnakinDeckBuilder().build();

        CommandResult commandResult = new AnakinNewDeckCommand(validDeck).execute(modelStub, commandHistory);

        assertEquals(String.format(AnakinNewDeckCommand.MESSAGE_SUCCESS, validDeck), commandResult.feedbackToUser);
        assertEquals(Arrays.asList(validDeck), modelStub.decksAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() throws Exception {
        AnakinDeck validDeck = new AnakinDeckBuilder().build();
        AnakinNewDeckCommand newDeckCommand = new AnakinNewDeckCommand(validDeck);
        ModelStub modelStub = new ModelStubWithDeck(validDeck);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AnakinNewDeckCommand.MESSAGE_DUPLICATE_DECK);
        newDeckCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        AnakinDeck firstDeck = new AnakinDeckBuilder().withName("Test Deck1").build();
        AnakinDeck secondDeck = new AnakinDeckBuilder().withName("Test Deck2").build();
        AnakinNewDeckCommand addFirstDeckCommand = new AnakinNewDeckCommand(firstDeck);
        AnakinNewDeckCommand addSecondDeckCommand = new AnakinNewDeckCommand(secondDeck);

        // same object -> returns true
        assertTrue(addFirstDeckCommand.equals(addFirstDeckCommand));

        // same values -> returns true
        AnakinNewDeckCommand addFirstDeckCommandCopy = new AnakinNewDeckCommand(firstDeck);
        assertTrue(addFirstDeckCommand.equals(addFirstDeckCommandCopy));

        // different types -> returns false
        assertFalse(addFirstDeckCommand.equals(1));

        // null -> returns false
        assertFalse(addFirstDeckCommand.equals(null));

        // different person -> returns false
        assertFalse(addFirstDeckCommand.equals(addSecondDeckCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements AnakinModel {

        @Override
        public void resetData(AnakinReadOnlyAnakin anakin) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDeck(AnakinDeck deck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDeck(AnakinDeck target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCard(AnakinCard card) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public AnakinReadOnlyAnakin getAnakin() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDeck(AnakinDeck target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateDeck(AnakinDeck target, AnakinDeck newdeck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateCard(AnakinCard target, AnakinCard newcard) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public boolean hasCard(AnakinCard card) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCard(AnakinCard card) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void getOutOfDeck() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void goIntoDeck(AnakinDeck target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<AnakinCard> getFilteredCardList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<AnakinDeck> getFilteredDeckList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredDeckList(Predicate<AnakinDeck> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCardList(Predicate<AnakinCard> predicate) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public boolean canUndoAnakin() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoAnakin() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoAnakin() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoAnakin() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitAnakin() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithDeck extends ModelStub {
        private final AnakinDeck deck;

        ModelStubWithDeck(AnakinDeck deck) {
            requireNonNull(deck);
            this.deck = deck;
        }

        @Override
        public boolean hasDeck(AnakinDeck deck) {
            requireNonNull(deck);
            return this.deck.isSameDeck(deck);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<AnakinDeck> decksAdded = new ArrayList<>();

        @Override
        public boolean hasDeck(AnakinDeck deck) {
            requireNonNull(deck);
            return decksAdded.stream().anyMatch(deck::isSameDeck);
        }

        @Override
        public void addDeck(AnakinDeck deck) {
            requireNonNull(deck);
            decksAdded.add(deck);
        }

        @Override
        public void commitAnakin() {
            // called by {@code AnakinNewDeckCommand#execute()}
        }

        @Override
        public AnakinReadOnlyAnakin getAnakin() {
            return new Anakin();
        }
    }

}
