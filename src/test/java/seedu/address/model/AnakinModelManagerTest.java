package seedu.address.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.AnakinTypicalDecks.DECK_A;
import static seedu.address.testutil.AnakinTypicalDecks.DECK_B;

import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.model.anakindeck.AnakinDeckNameContainsKeywordsPredicate;
import seedu.address.testutil.AnakinBuilder;

public class AnakinModelManagerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private AnakinModelManager modelManager = new AnakinModelManager();

    @Test
    public void hasDeck_nullDeck_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.hasDeck(null);
    }

    @Test
    public void hasDeck_deckNotInAnakin_returnsFalse() {
        assertFalse(modelManager.hasDeck(DECK_A));
    }

    @Test
    public void hasDeck_deckInAnakin_returnsTrue() {
        modelManager.addDeck(DECK_A);
        assertTrue(modelManager.hasDeck(DECK_A));
    }

    @Test
    public void getFilteredDeckList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        modelManager.getFilteredDeckList().remove(0);
    }

    @Test
    public void equals() {
        Anakin anakin = new AnakinBuilder().withDeck(DECK_A).withDeck(DECK_B).build();
        Anakin differentAnakin = new Anakin();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new AnakinModelManager(anakin, userPrefs);
        AnakinModelManager modelManagerCopy = new AnakinModelManager(anakin, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different anakin -> returns false
        assertFalse(modelManager.equals(new AnakinModelManager(differentAnakin, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = DECK_A.getName().fullName.split("\\s+");
        modelManager.updateFilteredDeckList(new AnakinDeckNameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new AnakinModelManager(anakin, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredDeckList(AnakinModel.PREDICATE_SHOW_ALL_DECKS);

        // different userPrefs -> returns true
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAnakinFilePath(Paths.get("differentFilePath"));
        assertTrue(modelManager.equals(new AnakinModelManager(anakin, differentUserPrefs)));
    }
}
