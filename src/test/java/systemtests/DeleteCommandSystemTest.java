package systemtests;

public class DeleteCommandSystemTest extends AddressBookSystemTest {
//
//    private static final String MESSAGE_INVALID_DELETE_COMMAND_FORMAT =
//            String.format(AddressbookMessages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);
//
//    @Test
//    public void delete() {
//        /* ----------------- Performing delete operation while an unfiltered list is being shown
// -------------------- */
//
//        /* Case: delete the first person in the list, command with leading spaces and trailing spaces -> deleted */
//        AddressbookModel expectedAddressbookModel = getModel();
//        String command = "     " + DeleteCommand.COMMAND_WORD + "      " + INDEX_FIRST_PERSON.getOneBased() + "
//   ";
//        Person deletedPerson = removePerson(expectedAddressbookModel, INDEX_FIRST_PERSON);
//        String expectedResultMessage = String.format(MESSAGE_DELETE_PERSON_SUCCESS, deletedPerson);
//        assertCommandSuccess(command, expectedAddressbookModel, expectedResultMessage);
//
//        /* Case: delete the last person in the list -> deleted */
//        AddressbookModel addressbookModelBeforeDeletingLast = getModel();
//        Index lastPersonIndex = getLastIndex(addressbookModelBeforeDeletingLast);
//        assertCommandSuccess(lastPersonIndex);
//
//        /* Case: undo deleting the last person in the list -> last person restored */
//        command = UndoCommand.COMMAND_WORD;
//        expectedResultMessage = UndoCommand.MESSAGE_SUCCESS;
//        assertCommandSuccess(command, addressbookModelBeforeDeletingLast, expectedResultMessage);
//
//        /* Case: redo deleting the last person in the list -> last person deleted again */
//        command = RedoCommand.COMMAND_WORD;
//        removePerson(addressbookModelBeforeDeletingLast, lastPersonIndex);
//        expectedResultMessage = RedoCommand.MESSAGE_SUCCESS;
//        assertCommandSuccess(command, addressbookModelBeforeDeletingLast, expectedResultMessage);
//
//        /* Case: delete the middle person in the list -> deleted */
//        Index middlePersonIndex = getMidIndex(getModel());
//        assertCommandSuccess(middlePersonIndex);
//
//        /* ------------------ Performing delete operation while a filtered list is being shown
// ---------------------- */
//
//        /* Case: filtered person list, delete index within bounds of address book and person list -> deleted */
//        showPersonsWithName(KEYWORD_MATCHING_MEIER);
//        Index index = INDEX_FIRST_PERSON;
//        assertTrue(index.getZeroBased() < getModel().getFilteredPersonList().size());
//        assertCommandSuccess(index);
//
//        /* Case: filtered person list, delete index within bounds of address book but out of bounds of person list
//         * -> rejected
//         */
//        showPersonsWithName(KEYWORD_MATCHING_MEIER);
//        int invalidIndex = getModel().getAddressBook().getPersonList().size();
//        command = DeleteCommand.COMMAND_WORD + " " + invalidIndex;
//        assertCommandFailure(command, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//
//        /* --------------------- Performing delete operation while a person card is selected
// ------------------------ */
//
//        /* Case: delete the selected person -> person list panel selects the person before the deleted person */
//        showAllPersons();
//        expectedAddressbookModel = getModel();
//        Index selectedIndex = getLastIndex(expectedAddressbookModel);
//        Index expectedIndex = Index.fromZeroBased(selectedIndex.getZeroBased() - 1);
//        selectPerson(selectedIndex);
//        command = DeleteCommand.COMMAND_WORD + " " + selectedIndex.getOneBased();
//        deletedPerson = removePerson(expectedAddressbookModel, selectedIndex);
//        expectedResultMessage = String.format(MESSAGE_DELETE_PERSON_SUCCESS, deletedPerson);
//        assertCommandSuccess(command, expectedAddressbookModel, expectedResultMessage, expectedIndex);
//
//        /* --------------------------------- Performing invalid delete operation
// ------------------------------------ */
//
//        /* Case: invalid index (0) -> rejected */
//        command = DeleteCommand.COMMAND_WORD + " 0";
//        assertCommandFailure(command, MESSAGE_INVALID_DELETE_COMMAND_FORMAT);
//
//        /* Case: invalid index (-1) -> rejected */
//        command = DeleteCommand.COMMAND_WORD + " -1";
//        assertCommandFailure(command, MESSAGE_INVALID_DELETE_COMMAND_FORMAT);
//
//        /* Case: invalid index (size + 1) -> rejected */
//        Index outOfBoundsIndex = Index.fromOneBased(
//                getModel().getAddressBook().getPersonList().size() + 1);
//        command = DeleteCommand.COMMAND_WORD + " " + outOfBoundsIndex.getOneBased();
//        assertCommandFailure(command, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//
//        /* Case: invalid arguments (alphabets) -> rejected */
//        assertCommandFailure(DeleteCommand.COMMAND_WORD + " abc", MESSAGE_INVALID_DELETE_COMMAND_FORMAT);
//
//        /* Case: invalid arguments (extra argument) -> rejected */
//        assertCommandFailure(DeleteCommand.COMMAND_WORD + " 1 abc", MESSAGE_INVALID_DELETE_COMMAND_FORMAT);
//
//        /* Case: mixed case command word -> rejected */
//        assertCommandFailure("DelETE 1", MESSAGE_UNKNOWN_COMMAND);
//    }
//
//    /**
//     * Removes the {@code Person} at the specified {@code index} in {@code addressbookModel}'s address book.
//     * @return the removed person
//     */
//    private Person removePerson(AddressbookModel addressbookModel, Index index) {
//        Person targetPerson = getPerson(addressbookModel, index);
//        addressbookModel.deletePerson(targetPerson);
//        return targetPerson;
//    }
//
//    /**
//     * Deletes the person at {@code toDelete} by creating a default {@code DeleteCommand} using {@code toDelete} and
//     * performs the same verification as {@code assertCommandSuccess(String, AddressbookModel, String)}.
//     * @see DeleteCommandSystemTest#assertCommandSuccess(String, AddressbookModel, String)
//     */
//    private void assertCommandSuccess(Index toDelete) {
//        AddressbookModel expectedAddressbookModel = getModel();
//        Person deletedPerson = removePerson(expectedAddressbookModel, toDelete);
//        String expectedResultMessage = String.format(MESSAGE_DELETE_PERSON_SUCCESS, deletedPerson);
//
//        assertCommandSuccess(
//                DeleteCommand.COMMAND_WORD + " " + toDelete.getOneBased(), expectedAddressbookModel,
// expectedResultMessage);
//    }
//
//    /**
//     * Executes {@code command} and in addition,<br>
//     * 1. Asserts that the command box displays an empty string.<br>
//     * 2. Asserts that the result display box displays {@code expectedResultMessage}.<br>
//     * 3. Asserts that the browser url and selected card remains unchanged.<br>
//     * 4. Asserts that the status bar's sync status changes.<br>
//     * 5. Asserts that the command box has the default style class.<br>
//     * Verifications 1 and 2 are performed by
//     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, AddressbookModel)}.
//     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, AddressbookModel)
//     */
//    private void assertCommandSuccess(String command, AddressbookModel expectedAddressbookModel, String
// expectedResultMessage) {
//        assertCommandSuccess(command, expectedAddressbookModel, expectedResultMessage, null);
//    }
//
//    /**
//     * Performs the same verification as {@code assertCommandSuccess(String, AddressbookModel, String)} except that
// the browser url
//     * and selected card are expected to update accordingly depending on the card at {@code
// expectedSelectedCardIndex}.
//     * @see DeleteCommandSystemTest#assertCommandSuccess(String, AddressbookModel, String)
//     * @see AddressBookSystemTest#assertSelectedCardChanged(Index)
//     */
//    private void assertCommandSuccess(String command, AddressbookModel expectedAddressbookModel, String
// expectedResultMessage,
//                                      Index expectedSelectedCardIndex) {
//        executeCommand(command);
//        assertApplicationDisplaysExpected("", expectedResultMessage, expectedAddressbookModel);
//
//        if (expectedSelectedCardIndex != null) {
//            assertSelectedCardChanged(expectedSelectedCardIndex);
//        } else {
//            assertSelectedCardUnchanged();
//        }
//
//        assertCommandBoxShowsDefaultStyle();
//        assertStatusBarUnchangedExceptSyncStatus();
//    }
//
//    /**
//     * Executes {@code command} and in addition,<br>
//     * 1. Asserts that the command box displays {@code command}.<br>
//     * 2. Asserts that result display box displays {@code expectedResultMessage}.<br>
//     * 3. Asserts that the browser url, selected card and status bar remain unchanged.<br>
//     * 4. Asserts that the command box has the error style.<br>
//     * Verifications 1 and 2 are performed by
//     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, AddressbookModel)}.<br>
//     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, AddressbookModel)
//     */
//    private void assertCommandFailure(String command, String expectedResultMessage) {
//        AddressbookModel expectedAddressbookModel = getModel();
//
//        executeCommand(command);
//        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//        assertCommandBoxShowsErrorStyle();
//        assertStatusBarUnchanged();
//    }
}
