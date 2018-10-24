package seedu.address.logic.commands.anakincommands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.AnakinTypicalCards.CARD_A;
import static seedu.address.testutil.AnakinTypicalCards.CARD_B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.anakincommands.AnakinNewCardCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Anakin;
import seedu.address.model.AnakinModel;
import seedu.address.model.AnakinModelManager;
import seedu.address.model.ReadOnlyAnakin;
import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.model.anakindeck.AnakinDeck;
import seedu.address.testutil.AnakinCardBuilder;
import seedu.address.testutil.AnakinDeckBuilder;


public class NewCardCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    private AnakinModel testModel = new AnakinModelManager();

    @Test
    public void constructor_nullCard_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AnakinNewCardCommand(null);
    }

    @Test
    public void execute_CardAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCardAdded modelStub = new ModelStubAcceptingCardAdded();
        AnakinCard validCard = CARD_A;

        CommandResult commandResult = new AnakinNewCardCommand(validCard).
                execute(modelStub, commandHistory);

        assertEquals(String.format(
                AnakinNewCardCommand.MESSAGE_NEW_CARD_SUCCESS,
                validCard), commandResult.feedbackToUser);
        assertEquals(Arrays.asList(validCard), modelStub.cardsAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateCard_throwsCommandException() throws Exception {
        AnakinCard validCard = CARD_B;
        AnakinNewCardCommand newCardCommand = new AnakinNewCardCommand(validCard);
        ModelStub modelStub = new ModelStubWithCard(validCard);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AnakinNewCardCommand.MESSAGE_DUPLICATE_CARD);
        newCardCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void execute_validCardButNotInDeck_throwsRuntimeException() throws Exception{
        AnakinCard validCard = CARD_B;
        AnakinNewCardCommand newCardCommand = new AnakinNewCardCommand(validCard);
        AnakinModel model = testModel;
        thrown.expect(RuntimeException.class);
        newCardCommand.execute(model,commandHistory);

    }

    // Integrated test
    @Test
    public void execute_validCardInDeck_success() throws Exception{
        AnakinCard validCard = CARD_B;
        AnakinNewCardCommand newCardCommand = new AnakinNewCardCommand(validCard);
        AnakinDeck validDeck =  new AnakinDeckBuilder().
                withName("Deck with Card B").build();

        AnakinModel model = testModel;
        model.goIntoDeck(validDeck);

        CommandResult commandResult = newCardCommand.execute(model,commandHistory);

        assertEquals(String.format(
                AnakinNewCardCommand.MESSAGE_NEW_CARD_SUCCESS,
                validCard), commandResult.feedbackToUser);

    }


    @Test
    public void equals() {
        AnakinCard firstCard = new AnakinCardBuilder().
                withQuestion("Test Card1").withAnswer("A1").build();
        AnakinCard secondCard = new AnakinCardBuilder().
                withQuestion("Test Card2").withAnswer("A2").build();
        AnakinNewCardCommand addFirstCardCommand =
                new AnakinNewCardCommand(firstCard);
        AnakinNewCardCommand addSecondCardCommand =
                new AnakinNewCardCommand(secondCard);

        // same object -> returns true
        assertTrue(addFirstCardCommand.equals(addFirstCardCommand));

        // same values -> returns true
        AnakinNewCardCommand addFirstCardCommandCopy =
                new AnakinNewCardCommand(firstCard);
        assertTrue(addFirstCardCommand.equals(addFirstCardCommandCopy));

        // different types -> returns false
        assertFalse(addFirstCardCommand.equals(1));

        // null -> returns false
        assertFalse(addFirstCardCommand.equals(null));

        // different card -> returns false
        assertFalse(addFirstCardCommand.equals(addSecondCardCommand));
    }


    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements AnakinModel {

        @Override
        public void resetData(ReadOnlyAnakin anakin) {
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
        public ReadOnlyAnakin getAnakin() {
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

        @Override
        public boolean isInsideDeck(){
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sort(){
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithCard extends ModelStub {
        private final AnakinCard card;

        ModelStubWithCard(AnakinCard card) {
            requireNonNull(card);
            this.card = card;
        }

        @Override
        public boolean hasCard(AnakinCard card) {
            requireNonNull(card);
            return this.card.isSameCard(card);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingCardAdded extends ModelStub {
        final ArrayList<AnakinCard> cardsAdded = new ArrayList<>();

        @Override
        public boolean hasCard(AnakinCard card) {
            requireNonNull(card);
            return cardsAdded.stream().anyMatch(card::isSameCard);
        }

        @Override
        public void addCard(AnakinCard card) {
            requireNonNull(card);
            cardsAdded.add(card);
        }

        @Override
        public void commitAnakin() {
            // called by {@code AnakinNewCardCommand#execute()}
        }

        @Override
        public ReadOnlyAnakin getAnakin() {
            return new Anakin();
        }
    }

}
