package seedu.address.model.anakindeck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.AnakinCommandTestUtil.VALID_CARD_LIST;
import static seedu.address.testutil.AnakinTypicalDecks.DECK_A;
import static seedu.address.testutil.AnakinTypicalDecks.DECK_B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.model.anakindeck.anakinexceptions.DeckNotFoundException;
import seedu.address.model.anakindeck.anakinexceptions.DuplicateDeckException;
import seedu.address.testutil.AnakinDeckBuilder;

public class UniqueDeckListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final AnakinUniqueDeckList uniqueDeckList = new AnakinUniqueDeckList();

    @Test
    public void contains_nullDeck_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueDeckList.contains(null);
    }

    @Test
    public void contains_deckNotInList_returnsFalse() {
        assertFalse(uniqueDeckList.contains(DECK_A));
    }

    @Test
    public void contains_DeckInList_returnsTrue() {
        uniqueDeckList.add(DECK_A);
        assertTrue(uniqueDeckList.contains(DECK_A));
    }

    @Test
    public void contains_deckWithSameIdentityFieldsInList_returnsTrue() {
        uniqueDeckList.add(DECK_A);
        AnakinDeck editedDeck_A = new AnakinDeckBuilder(DECK_A).withCards(VALID_CARD_LIST).build();
        assertTrue(uniqueDeckList.contains(editedDeck_A));
    }

    @Test
    public void add_nullDeck_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueDeckList.add(null);
    }

    @Test
    public void add_duplicateDeck_throwsDuplicateDeckException() {
        uniqueDeckList.add(DECK_A);
        thrown.expect(DuplicateDeckException.class);
        uniqueDeckList.add(DECK_A);
    }

    @Test
    public void setDeck_nullTargetDeck_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueDeckList.setDeck(null, DECK_A);
    }

    @Test
    public void setDeck_nullEditedDeck_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueDeckList.setDeck(DECK_A, null);
    }

    @Test
    public void setDeck_targetDeckNotInList_throwsDeckNotFoundException() {
        thrown.expect(DeckNotFoundException.class);
        uniqueDeckList.setDeck(DECK_B, DECK_B);
    }

    @Test
    public void setDeck_editedDeckIsSameDeck_success() {
        uniqueDeckList.add(DECK_A);
        uniqueDeckList.setDeck(DECK_A, DECK_A);
        AnakinUniqueDeckList expectedUniqueDeckList = new AnakinUniqueDeckList();
        expectedUniqueDeckList.add(DECK_A);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDeck_editedDeckHasSameIdentity_success() {
        uniqueDeckList.add(DECK_A);
        AnakinDeck editedDeck_A = new AnakinDeckBuilder(DECK_A).withCards(VALID_CARD_LIST).build();
        uniqueDeckList.setDeck(DECK_A, editedDeck_A);
        AnakinUniqueDeckList expectedUniqueDeckList = new AnakinUniqueDeckList();
        expectedUniqueDeckList.add(editedDeck_A);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDeck_editedDeckHasDifferentIdentity_success() {
        uniqueDeckList.add(DECK_A);
        uniqueDeckList.setDeck(DECK_A, DECK_B);
        AnakinUniqueDeckList expectedUniqueDeckList = new AnakinUniqueDeckList();
        expectedUniqueDeckList.add(DECK_B);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDeck_editedDeckHasNonUniqueIdentity_throwsDuplicateDeckException() {
        uniqueDeckList.add(DECK_A);
        uniqueDeckList.add(DECK_B);
        thrown.expect(DuplicateDeckException.class);
        uniqueDeckList.setDeck(DECK_A, DECK_B);
    }

    @Test
    public void remove_nullDeck_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueDeckList.remove(null);
    }

    @Test
    public void remove_deckDoesNotExist_throwsDeckNotFoundException() {
        thrown.expect(DeckNotFoundException.class);
        uniqueDeckList.remove(DECK_A);
    }

    @Test
    public void remove_existingDeck_removesDeck() {
        uniqueDeckList.add(DECK_A);
        uniqueDeckList.remove(DECK_A);
        AnakinUniqueDeckList expectedUniqueDeckList = new AnakinUniqueDeckList();
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDecks_nullUniqueDeckList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueDeckList.setDecks((AnakinUniqueDeckList) null);
    }

    @Test
    public void setDecks_uniqueDeckList_replacesOwnListWithProvidedUniqueDeckList() {
        uniqueDeckList.add(DECK_A);
        AnakinUniqueDeckList expectedUniqueDeckList = new AnakinUniqueDeckList();
        expectedUniqueDeckList.add(DECK_B);
        uniqueDeckList.setDecks(expectedUniqueDeckList);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDecks_nullList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueDeckList.setDecks((List<AnakinDeck>) null);
    }

    @Test
    public void setDecks_list_replacesOwnListWithProvidedList() {
        uniqueDeckList.add(DECK_A);
        List<AnakinDeck> deckList = Collections.singletonList(DECK_B);
        uniqueDeckList.setDecks(deckList);
        AnakinUniqueDeckList expectedUniqueDeckList = new AnakinUniqueDeckList();
        expectedUniqueDeckList.add(DECK_B);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDecks_listWithDuplicateDecks_throwsDuplicateDeckException() {
        List<AnakinDeck> listWithDuplicateDecks = Arrays.asList(DECK_A, DECK_A);
        thrown.expect(DuplicateDeckException.class);
        uniqueDeckList.setDecks(listWithDuplicateDecks);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        uniqueDeckList.asUnmodifiableObservableList().remove(0);
    }

}
