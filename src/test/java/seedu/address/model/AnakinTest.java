package seedu.address.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.AnakinCommandTestUtil.VALID_CARD_LIST;
import static seedu.address.testutil.AnakinTypicalDecks.DECK_A;
import static seedu.address.testutil.AnakinTypicalDecks.getTypicalAnakin;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.model.anakindeck.AnakinDeck;
import seedu.address.model.anakindeck.anakinexceptions.DuplicateDeckException;
import seedu.address.testutil.AnakinDeckBuilder;

public class AnakinTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final Anakin anakin = new Anakin();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), anakin.getDeckList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        anakin.resetData(null);
    }

    @Test
    public void resetData_withValidAnakinReadOnlyAnakin_replacesData() {
        Anakin newData = getTypicalAnakin();
        anakin.resetData(newData);
        assertEquals(newData, anakin);
    }

    @Test
    public void resetData_withDuplicateDecks_throwsDuplicateDeckException() {
        // Two decks with the same identity field
        AnakinDeck editedDeck_A = new AnakinDeckBuilder(DECK_A).withCards(VALID_CARD_LIST).build();
        List<AnakinDeck> newDecks = Arrays.asList(DECK_A, editedDeck_A);
        AnakinStub newData = new AnakinStub(newDecks);

        thrown.expect(DuplicateDeckException.class);
        anakin.resetData(newData);
    }

    @Test
    public void hasDeck_nullDeck_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        anakin.hasDeck(null);
    }

    @Test
    public void hasDeck_deckNotInAnakin_returnsFalse() {
        assertFalse(anakin.hasDeck(DECK_A));
    }

    @Test
    public void hasDeck_deckInAnakin_returnsTrue() {
        anakin.addDeck(DECK_A);
        assertTrue(anakin.hasDeck(DECK_A));
    }

    @Test
    public void hasDeck_deckWithSameIdentityFieldsInAnakin_returnsTrue() {
        anakin.addDeck(DECK_A);
        AnakinDeck editedDeck_A = new AnakinDeckBuilder(DECK_A).withCards(VALID_CARD_LIST).build();
        assertTrue(anakin.hasDeck(editedDeck_A));
    }

    @Test
    public void getDeckList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        anakin.getDeckList().remove(0);
    }

    /**
     * A stub AnakinReadOnlyAnakin whose decks list can violate interface constraints.
     */
    private static class AnakinStub implements AnakinReadOnlyAnakin {
        private final ObservableList<AnakinDeck> decks = FXCollections.observableArrayList();
        private final ObservableList<AnakinCard> cards = FXCollections.observableArrayList();

        AnakinStub(Collection<AnakinDeck> decks) {
            this.decks.setAll(decks);
        }

        @Override
        public ObservableList<AnakinDeck> getDeckList() {
            return decks;
        }

        @Override
        public ObservableList<AnakinCard> getCardList() {
            return cards;
        }

        @Override
        public boolean isInsideDeck(){ return false;}
    }
}
