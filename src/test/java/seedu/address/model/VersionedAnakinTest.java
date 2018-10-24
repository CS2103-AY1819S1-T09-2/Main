package seedu.address.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.AnakinTypicalDecks.DECK_A;
import static seedu.address.testutil.AnakinTypicalDecks.DECK_B;
import static seedu.address.testutil.AnakinTypicalDecks.DECK_C;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import seedu.address.testutil.AnakinBuilder;

public class VersionedAnakinTest {
    private final AnakinReadOnlyAnakin anakinWithDeck_A = new AnakinBuilder().withDeck(DECK_A).build();
    private final AnakinReadOnlyAnakin anakinWithDeck_B = new AnakinBuilder().withDeck(DECK_B).build();
    private final AnakinReadOnlyAnakin anakinWithDeck_C = new AnakinBuilder().withDeck(DECK_C).build();
    private final AnakinReadOnlyAnakin emptyAnakin = new AnakinBuilder().build();

    @Test
    public void commit_singleAnakin_noStatesRemovedCurrentStateSaved() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(emptyAnakin);

        versionedAnakin.commit();
        assertAnakinListStatus(versionedAnakin,
                Collections.singletonList(emptyAnakin),
                emptyAnakin,
                Collections.emptyList());
    }

    @Test
    public void commit_multipleAnakinPointerAtEndOfStateList_noStatesRemovedCurrentStateSaved() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(
                emptyAnakin, anakinWithDeck_A, anakinWithDeck_B);

        versionedAnakin.commit();
        assertAnakinListStatus(versionedAnakin,
                Arrays.asList(emptyAnakin, anakinWithDeck_A, anakinWithDeck_B),
                anakinWithDeck_B,
                Collections.emptyList());
    }

    @Test
    public void commit_multipleAnakinPointerNotAtEndOfStateList_statesAfterPointerRemovedCurrentStateSaved() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(
                emptyAnakin, anakinWithDeck_A, anakinWithDeck_B);
        shiftCurrentStatePointerLeftwards(versionedAnakin, 2);

        versionedAnakin.commit();
        assertAnakinListStatus(versionedAnakin,
                Collections.singletonList(emptyAnakin),
                emptyAnakin,
                Collections.emptyList());
    }

    @Test
    public void canUndo_multipleAnakinPointerAtEndOfStateList_returnsTrue() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(
                emptyAnakin, anakinWithDeck_A, anakinWithDeck_B);

        assertTrue(versionedAnakin.canUndo());
    }

    @Test
    public void canUndo_multipleAnakinPointerAtStartOfStateList_returnsTrue() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(
                emptyAnakin, anakinWithDeck_A, anakinWithDeck_B);
        shiftCurrentStatePointerLeftwards(versionedAnakin, 1);

        assertTrue(versionedAnakin.canUndo());
    }

    @Test
    public void canUndo_singleAnakin_returnsFalse() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(emptyAnakin);

        assertFalse(versionedAnakin.canUndo());
    }

    @Test
    public void canUndo_multipleAnakinPointerAtStartOfStateList_returnsFalse() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(
                emptyAnakin, anakinWithDeck_A, anakinWithDeck_B);
        shiftCurrentStatePointerLeftwards(versionedAnakin, 2);

        assertFalse(versionedAnakin.canUndo());
    }

    @Test
    public void canRedo_multipleAnakinPointerNotAtEndOfStateList_returnsTrue() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(
                emptyAnakin, anakinWithDeck_A, anakinWithDeck_B);
        shiftCurrentStatePointerLeftwards(versionedAnakin, 1);

        assertTrue(versionedAnakin.canRedo());
    }

    @Test
    public void canRedo_multipleAnakinPointerAtStartOfStateList_returnsTrue() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(
                emptyAnakin, anakinWithDeck_A, anakinWithDeck_B);
        shiftCurrentStatePointerLeftwards(versionedAnakin, 2);

        assertTrue(versionedAnakin.canRedo());
    }

    @Test
    public void canRedo_singleAnakin_returnsFalse() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(emptyAnakin);

        assertFalse(versionedAnakin.canRedo());
    }

    @Test
    public void canRedo_multipleAnakinPointerAtEndOfStateList_returnsFalse() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(
                emptyAnakin, anakinWithDeck_A, anakinWithDeck_B);

        assertFalse(versionedAnakin.canRedo());
    }

    @Test
    public void undo_multipleAnakinPointerAtEndOfStateList_success() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(
                emptyAnakin, anakinWithDeck_A, anakinWithDeck_B);

        versionedAnakin.undo();
        assertAnakinListStatus(versionedAnakin,
                Collections.singletonList(emptyAnakin),
                anakinWithDeck_A,
                Collections.singletonList(anakinWithDeck_B));
    }

    @Test
    public void undo_multipleAnakinPointerNotAtStartOfStateList_success() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(
                emptyAnakin, anakinWithDeck_A, anakinWithDeck_B);
        shiftCurrentStatePointerLeftwards(versionedAnakin, 1);

        versionedAnakin.undo();
        assertAnakinListStatus(versionedAnakin,
                Collections.emptyList(),
                emptyAnakin,
                Arrays.asList(anakinWithDeck_A, anakinWithDeck_B));
    }

    @Test
    public void undo_singleAnakin_throwsNoUndoableStateException() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(emptyAnakin);

        assertThrows(AnakinVersionedAnakin.NoUndoableStateException.class, versionedAnakin::undo);
    }

    @Test
    public void undo_multipleAnakinPointerAtStartOfStateList_throwsNoUndoableStateException() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(
                emptyAnakin, anakinWithDeck_A, anakinWithDeck_B);
        shiftCurrentStatePointerLeftwards(versionedAnakin, 2);

        assertThrows(AnakinVersionedAnakin.NoUndoableStateException.class, versionedAnakin::undo);
    }

    @Test
    public void redo_multipleAnakinPointerNotAtEndOfStateList_success() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(
                emptyAnakin, anakinWithDeck_A, anakinWithDeck_B);
        shiftCurrentStatePointerLeftwards(versionedAnakin, 1);

        versionedAnakin.redo();
        assertAnakinListStatus(versionedAnakin,
                Arrays.asList(emptyAnakin, anakinWithDeck_A),
                anakinWithDeck_B,
                Collections.emptyList());
    }

    @Test
    public void redo_multipleAnakinPointerAtStartOfStateList_success() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(
                emptyAnakin, anakinWithDeck_A, anakinWithDeck_B);
        shiftCurrentStatePointerLeftwards(versionedAnakin, 2);

        versionedAnakin.redo();
        assertAnakinListStatus(versionedAnakin,
                Collections.singletonList(emptyAnakin),
                anakinWithDeck_A,
                Collections.singletonList(anakinWithDeck_B));
    }

    @Test
    public void redo_singleAnakin_throwsNoRedoableStateException() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(emptyAnakin);

        assertThrows(AnakinVersionedAnakin.NoRedoableStateException.class, versionedAnakin::redo);
    }

    @Test
    public void redo_multipleAnakinPointerAtEndOfStateList_throwsNoRedoableStateException() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(
                emptyAnakin, anakinWithDeck_A, anakinWithDeck_B);

        assertThrows(AnakinVersionedAnakin.NoRedoableStateException.class, versionedAnakin::redo);
    }

    @Test
    public void equals() {
        AnakinVersionedAnakin versionedAnakin = prepareAnakinList(anakinWithDeck_A, anakinWithDeck_B);

        // same values -> returns true
        AnakinVersionedAnakin copy = prepareAnakinList(anakinWithDeck_A, anakinWithDeck_B);
        assertTrue(versionedAnakin.equals(copy));

        // same object -> returns true
        assertTrue(versionedAnakin.equals(versionedAnakin));

        // null -> returns false
        assertFalse(versionedAnakin.equals(null));

        // different types -> returns false
        assertFalse(versionedAnakin.equals(1));

        // different state list -> returns false
        AnakinVersionedAnakin differentAnakinList = prepareAnakinList(anakinWithDeck_B, anakinWithDeck_C);
        assertFalse(versionedAnakin.equals(differentAnakinList));

        // different current pointer index -> returns false
        AnakinVersionedAnakin differentCurrentStatePointer = prepareAnakinList(
                anakinWithDeck_A, anakinWithDeck_B);
        shiftCurrentStatePointerLeftwards(versionedAnakin, 1);
        assertFalse(versionedAnakin.equals(differentCurrentStatePointer));
    }

    /**
     * Asserts that {@code versionedAnakin} is currently pointing at {@code expectedCurrentState},
     * states before {@code versionedAnakin#currentStatePointer} is equal to {@code expectedStatesBeforePointer},
     * and states after {@code versionedAnakin#currentStatePointer} is equal to {@code expectedStatesAfterPointer}.
     */
    private void assertAnakinListStatus(AnakinVersionedAnakin versionedAnakin,
                                             List<AnakinReadOnlyAnakin> expectedStatesBeforePointer,
                                             AnakinReadOnlyAnakin expectedCurrentState,
                                             List<AnakinReadOnlyAnakin> expectedStatesAfterPointer) {
        // check state currently pointing at is correct
        assertEquals(new Anakin(versionedAnakin), expectedCurrentState);

        // shift pointer to start of state list
        while (versionedAnakin.canUndo()) {
            versionedAnakin.undo();
        }

        // check states before pointer are correct
        for (AnakinReadOnlyAnakin expectedAnakin : expectedStatesBeforePointer) {
            assertEquals(expectedAnakin, new Anakin(versionedAnakin));
            versionedAnakin.redo();
        }

        // check states after pointer are correct
        for (AnakinReadOnlyAnakin expectedAnakin : expectedStatesAfterPointer) {
            versionedAnakin.redo();
            assertEquals(expectedAnakin, new Anakin(versionedAnakin));
        }

        // check that there are no more states after pointer
        assertFalse(versionedAnakin.canRedo());

        // revert pointer to original position
        expectedStatesAfterPointer.forEach(unused -> versionedAnakin.undo());
    }

    /**
     * Creates and returns a {@code VersionedAnakin} with the {@code anakinStates} added into it, and the
     * {@code VersionedAnakin#currentStatePointer} at the end of list.
     */
    private AnakinVersionedAnakin prepareAnakinList(AnakinReadOnlyAnakin... anakinStates) {
        assertFalse(anakinStates.length == 0);

        AnakinVersionedAnakin versionedAnakin = new AnakinVersionedAnakin(anakinStates[0]);
        for (int i = 1; i < anakinStates.length; i++) {
            versionedAnakin.resetData(anakinStates[i]);
            versionedAnakin.commit();
        }

        return versionedAnakin;
    }

    /**
     * Shifts the {@code versionedAnakin#currentStatePointer} by {@code count} to the left of its list.
     */
    private void shiftCurrentStatePointerLeftwards(AnakinVersionedAnakin versionedAnakin, int count) {
        for (int i = 0; i < count; i++) {
            versionedAnakin.undo();
        }
    }
}
