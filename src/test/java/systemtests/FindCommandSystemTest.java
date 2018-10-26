package systemtests;

public class FindCommandSystemTest extends AddressBookSystemTest {

//    @Test
//    public void find() {
//        /* Case: find multiple persons in address book, command with leading spaces and trailing spaces
//         * -> 2 persons found
//         */
//        String command = "   " + FindCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_MEIER + "   ";
//        AddressbookModel expectedAddressbookModel = getModel();
//        ModelHelper.setFilteredList(expectedAddressbookModel, BENSON, DANIEL); // first names of Benson and Daniel
// are "Meier"
//        assertCommandSuccess(command, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//
//        /* Case: repeat previous find command where person list is displaying the persons we are finding
//         * -> 2 persons found
//         */
//        command = FindCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_MEIER;
//        assertCommandSuccess(command, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//
//        /* Case: find person where person list is not displaying the person we are finding -> 1 person found */
//        command = FindCommand.COMMAND_WORD + " Carl";
//        ModelHelper.setFilteredList(expectedAddressbookModel, CARL);
//        assertCommandSuccess(command, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//
//        /* Case: find multiple persons in address book, 2 keywords -> 2 persons found */
//        command = FindCommand.COMMAND_WORD + " Benson Daniel";
//        ModelHelper.setFilteredList(expectedAddressbookModel, BENSON, DANIEL);
//        assertCommandSuccess(command, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//
//        /* Case: find multiple persons in address book, 2 keywords in reversed order -> 2 persons found */
//        command = FindCommand.COMMAND_WORD + " Daniel Benson";
//        assertCommandSuccess(command, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//
//        /* Case: find multiple persons in address book, 2 keywords with 1 repeat -> 2 persons found */
//        command = FindCommand.COMMAND_WORD + " Daniel Benson Daniel";
//        assertCommandSuccess(command, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//
//        /* Case: find multiple persons in address book, 2 matching keywords and 1 non-matching keyword
//         * -> 2 persons found
//         */
//        command = FindCommand.COMMAND_WORD + " Daniel Benson NonMatchingKeyWord";
//        assertCommandSuccess(command, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//
//        /* Case: undo previous find command -> rejected */
//        command = UndoCommand.COMMAND_WORD;
//        String expectedResultMessage = UndoCommand.MESSAGE_FAILURE;
//        assertCommandFailure(command, expectedResultMessage);
//
//        /* Case: redo previous find command -> rejected */
//        command = RedoCommand.COMMAND_WORD;
//        expectedResultMessage = RedoCommand.MESSAGE_FAILURE;
//        assertCommandFailure(command, expectedResultMessage);
//
//        /* Case: find same persons in address book after deleting 1 of them -> 1 person found */
//        executeCommand(DeleteCommand.COMMAND_WORD + " 1");
//        assertFalse(getModel().getAddressBook().getPersonList().contains(BENSON));
//        command = FindCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_MEIER;
//        expectedAddressbookModel = getModel();
//        ModelHelper.setFilteredList(expectedAddressbookModel, DANIEL);
//        assertCommandSuccess(command, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//
//        /* Case: find person in address book, keyword is same as name but of different case -> 1 person found */
//        command = FindCommand.COMMAND_WORD + " MeIeR";
//        assertCommandSuccess(command, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//
//        /* Case: find person in address book, keyword is substring of name -> 0 persons found */
//        command = FindCommand.COMMAND_WORD + " Mei";
//        ModelHelper.setFilteredList(expectedAddressbookModel);
//        assertCommandSuccess(command, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//
//        /* Case: find person in address book, name is substring of keyword -> 0 persons found */
//        command = FindCommand.COMMAND_WORD + " Meiers";
//        ModelHelper.setFilteredList(expectedAddressbookModel);
//        assertCommandSuccess(command, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//
//        /* Case: find person not in address book -> 0 persons found */
//        command = FindCommand.COMMAND_WORD + " Mark";
//        assertCommandSuccess(command, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//
//        /* Case: find phone number of person in address book -> 0 persons found */
//        command = FindCommand.COMMAND_WORD + " " + DANIEL.getPhone().value;
//        assertCommandSuccess(command, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//
//        /* Case: find address of person in address book -> 0 persons found */
//        command = FindCommand.COMMAND_WORD + " " + DANIEL.getAddress().value;
//        assertCommandSuccess(command, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//
//        /* Case: find email of person in address book -> 0 persons found */
//        command = FindCommand.COMMAND_WORD + " " + DANIEL.getEmail().value;
//        assertCommandSuccess(command, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//
//        /* Case: find tags of person in address book -> 0 persons found */
//        List<Tag> tags = new ArrayList<>(DANIEL.getTags());
//        command = FindCommand.COMMAND_WORD + " " + tags.get(0).tagName;
//        assertCommandSuccess(command, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//
//        /* Case: find while a person is selected -> selected card deselected */
//        showAllPersons();
//        selectPerson(Index.fromOneBased(1));
//        assertFalse(getPersonListPanel().getHandleToSelectedCard().getName().equals(DANIEL.getName().fullName));
//        command = FindCommand.COMMAND_WORD + " Daniel";
//        ModelHelper.setFilteredList(expectedAddressbookModel, DANIEL);
//        assertCommandSuccess(command, expectedAddressbookModel);
//        assertSelectedCardDeselected();
//
//        /* Case: find person in empty address book -> 0 persons found */
//        deleteAllPersons();
//        command = FindCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_MEIER;
//        expectedAddressbookModel = getModel();
//        ModelHelper.setFilteredList(expectedAddressbookModel, DANIEL);
//        assertCommandSuccess(command, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//
//        /* Case: mixed case command word -> rejected */
//        command = "FiNd Meier";
//        assertCommandFailure(command, MESSAGE_UNKNOWN_COMMAND);
//    }
//
//    /**
//     * Executes {@code command} and verifies that the command box displays an empty string, the result display
//     * box displays {@code AddressbookMessages#MESSAGE_PERSONS_LISTED_OVERVIEW} with the number of people in the
// filtered list,
//     * and the model related components equal to {@code expectedAddressbookModel}.
//     * These verifications are done by
//     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, AddressbookModel)}.<br>
//     * Also verifies that the status bar remains unchanged, and the command box has the default style class, and the
//     * selected card updated accordingly, depending on {@code cardStatus}.
//     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, AddressbookModel)
//     */
//    private void assertCommandSuccess(String command, AddressbookModel expectedAddressbookModel) {
//        String expectedResultMessage = String.format(
//                MESSAGE_PERSONS_LISTED_OVERVIEW, expectedAddressbookModel.getFilteredPersonList().size());
//
//        executeCommand(command);
//        assertApplicationDisplaysExpected("", expectedResultMessage, expectedAddressbookModel);
//        assertCommandBoxShowsDefaultStyle();
//        assertStatusBarUnchanged();
//    }
//
//    /**
//     * Executes {@code command} and verifies that the command box displays {@code command}, the result display
//     * box displays {@code expectedResultMessage} and the model related components equal to the current model.
//     * These verifications are done by
//     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, AddressbookModel)}.<br>
//     * Also verifies that the browser url, selected card and status bar remain unchanged, and the command box has the
//     * error style.
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
