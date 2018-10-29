package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalDecks.DECK_WITH_CARDS;

import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAnakin;
import seedu.address.model.deck.Card;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.anakinexceptions.DeckImportException;
import seedu.address.storage.portmanager.Porter;

public class ImportDeckCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();


    @Test
    public void importDeck_successs() throws Exception {
        Model testModel = new ModelAlwaysImports();
        Deck deckToImport = DECK_WITH_CARDS;
        ImportDeckCommand importCommand = new ImportDeckCommand("Unused");
        CommandResult commandResult = importCommand.execute(testModel, commandHistory);

        assertEquals(String.format(ImportDeckCommand.MESSAGE_IMPORT_DECK_SUCCESS, deckToImport),
            commandResult.feedbackToUser);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {

        @Override
        public void resetData(ReadOnlyAnakin anakin) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDeck(Deck deck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDeck(Deck target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCard(Card card) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAnakin getAnakin() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDeck(Deck target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateDeck(Deck target, Deck newdeck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateCard(Card target, Card newcard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCard(Card card) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCard(Card card) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void getOutOfDeck() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void getIntoDeck(Deck target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Card> getFilteredCardList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Deck> getFilteredDeckList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredDeckList(Predicate<Deck> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCardList(Predicate<Card> predicate) {
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

        @Override
        public boolean isInsideDeck() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String exportDeck(Deck deck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Deck importDeck(String filepath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sort() {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that always imports the same deck.
     */

    private class ModelAlwaysImports extends ModelStub {
        final Porter porter = new PortManagerStub();

        @Override
        public Deck importDeck(String filepath) {
            return porter.importDeck("unused");
        }

        @Override
        public void commitAnakin() {
            // called by {@code ImportDeckCommand#execute()}
        }

    }

    private static class PortManagerStub implements Porter {
        @Override
        public String exportDeck(Deck deck) {
            return null;
        }

        @Override
        public Deck importDeck(String stringPath) throws DeckImportException {
            return DECK_WITH_CARDS;
        }
    }
}
