package systemtests;

public class AddCommandSystemTest extends AnakinSystemTest {
//
//    @Test
//    public void add() {
//        AddressbookModel addressbookModel = getModel();
//
//        /* ------------------------ Perform add operations on the shown unfiltered list
// ----------------------------- */
//
//        /* Case: add a person without tags to a non-empty address book, command with leading spaces and trailing
// spaces
//         * -> added
//         */
//        Person toAdd = AMY;
//        String command = "   " + AddCommand.COMMAND_WORD + "  " + NAME_DESC_AMY + "  " + PHONE_DESC_AMY + " "
//                + EMAIL_DESC_AMY + "   " + ADDRESS_DESC_AMY + "   " + TAG_DESC_FRIEND + " ";
//        assertCommandSuccess(command, toAdd);
//
//        /* Case: undo adding Amy to the list -> Amy deleted */
//        command = UndoCommand.COMMAND_WORD;
//        String expectedResultMessage = UndoCommand.MESSAGE_SUCCESS;
//        assertCommandSuccess(command, addressbookModel, expectedResultMessage);
//
//        /* Case: redo adding Amy to the list -> Amy added again */
//        command = RedoCommand.COMMAND_WORD;
//        addressbookModel.addPerson(toAdd);
//        expectedResultMessage = RedoCommand.MESSAGE_SUCCESS;
//        assertCommandSuccess(command, addressbookModel, expectedResultMessage);
//
//        /* Case: add a person with all fields same as another person in the address book except name -> added */
//        toAdd = new AddressbookPersonBuilder(AMY).withName(VALID_NAME_BOB).build();
//        command = AddCommand.COMMAND_WORD + NAME_DESC_BOB + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
//                + TAG_DESC_FRIEND;
//        assertCommandSuccess(command, toAdd);
//
//        /* Case: add a person with all fields same as another person in the address book except phone and email
//         * -> added
//         */
//        toAdd = new AddressbookPersonBuilder(AMY).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
//        command = AddressbookPersonUtil.getAddCommand(toAdd);
//        assertCommandSuccess(command, toAdd);
//
//        /* Case: add to empty address book -> added */
//        deleteAllPersons();
//        assertCommandSuccess(ALICE);
//
//        /* Case: add a person with tags, command with parameters in random order -> added */
//        toAdd = BOB;
//        command = AddCommand.COMMAND_WORD + TAG_DESC_FRIEND + PHONE_DESC_BOB + ADDRESS_DESC_BOB + NAME_DESC_BOB
//                + TAG_DESC_HUSBAND + EMAIL_DESC_BOB;
//        assertCommandSuccess(command, toAdd);
//
//        /* Case: add a person, missing tags -> added */
//        assertCommandSuccess(HOON);
//
//        /* -------------------------- Perform add operation on the shown filtered list
// ------------------------------ */
//
//        /* Case: filters the person list before adding -> added */
//        showPersonsWithName(KEYWORD_MATCHING_MEIER);
//        assertCommandSuccess(IDA);
//
//        /* ------------------------ Perform add operation while a person card is selected
// --------------------------- */
//
//        /* Case: selects first card in the person list, add a person -> added, card selection remains unchanged */
//        selectPerson(Index.fromOneBased(1));
//        assertCommandSuccess(CARL);
//
//        /* ----------------------------------- Perform invalid add operations
// --------------------------------------- */
//
//        /* Case: add a duplicate person -> rejected */
//        command = AddressbookPersonUtil.getAddCommand(HOON);
//        assertCommandFailure(command, AddCommand.MESSAGE_DUPLICATE_PERSON);
//
//        /* Case: add a duplicate person except with different phone -> rejected */
//        toAdd = new AddressbookPersonBuilder(HOON).withPhone(VALID_PHONE_BOB).build();
//        command = AddressbookPersonUtil.getAddCommand(toAdd);
//        assertCommandFailure(command, AddCommand.MESSAGE_DUPLICATE_PERSON);
//
//        /* Case: add a duplicate person except with different email -> rejected */
//        toAdd = new AddressbookPersonBuilder(HOON).withEmail(VALID_EMAIL_BOB).build();
//        command = AddressbookPersonUtil.getAddCommand(toAdd);
//        assertCommandFailure(command, AddCommand.MESSAGE_DUPLICATE_PERSON);
//
//        /* Case: add a duplicate person except with different address -> rejected */
//        toAdd = new AddressbookPersonBuilder(HOON).withAddress(VALID_ADDRESS_BOB).build();
//        command = AddressbookPersonUtil.getAddCommand(toAdd);
//        assertCommandFailure(command, AddCommand.MESSAGE_DUPLICATE_PERSON);
//
//        /* Case: add a duplicate person except with different tags -> rejected */
//        command = AddressbookPersonUtil.getAddCommand(HOON) + " " + PREFIX_TAG.getPrefix() + "friends";
//        assertCommandFailure(command, AddCommand.MESSAGE_DUPLICATE_PERSON);
//
//        /* Case: missing name -> rejected */
//        command = AddCommand.COMMAND_WORD + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY;
//        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
//
//        /* Case: missing phone -> rejected */
//        command = AddCommand.COMMAND_WORD + NAME_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY;
//        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
//
//        /* Case: missing email -> rejected */
//        command = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY;
//        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
//
//        /* Case: missing address -> rejected */
//        command = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY;
//        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
//
//        /* Case: invalid keyword -> rejected */
//        command = "adds " + AddressbookPersonUtil.getPersonDetails(toAdd);
//        assertCommandFailure(command, AddressbookMessages.MESSAGE_UNKNOWN_COMMAND);
//
//        /* Case: invalid name -> rejected */
//        command = AddCommand.COMMAND_WORD + INVALID_NAME_DESC + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY;
//        assertCommandFailure(command, Name.MESSAGE_NAME_CONSTRAINTS);
//
//        /* Case: invalid phone -> rejected */
//        command = AddCommand.COMMAND_WORD + NAME_DESC_AMY + INVALID_PHONE_DESC + EMAIL_DESC_AMY + ADDRESS_DESC_AMY;
//        assertCommandFailure(command, Phone.MESSAGE_PHONE_CONSTRAINTS);
//
//        /* Case: invalid email -> rejected */
//        command = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + INVALID_EMAIL_DESC + ADDRESS_DESC_AMY;
//        assertCommandFailure(command, Email.MESSAGE_EMAIL_CONSTRAINTS);
//
//        /* Case: invalid address -> rejected */
//        command = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + INVALID_ADDRESS_DESC;
//        assertCommandFailure(command, Address.MESSAGE_ADDRESS_CONSTRAINTS);
//
//        /* Case: invalid tag -> rejected */
//        command = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
//                + INVALID_TAG_DESC;
//        assertCommandFailure(command, Tag.MESSAGE_TAG_CONSTRAINTS);
//    }
//
//    /**
//     * Executes the {@code AddCommand} that adds {@code toAdd} to the model and asserts that the,<br>
//     * 1. Command box displays an empty string.<br>
//     * 2. Command box has the default style class.<br>
//     * 3. Result display box displays the success message of executing {@code AddCommand} with the details of
//     * {@code toAdd}.<br>
//     * 4. {@code Storage} and {@code PersonListPanel} equal to the corresponding components in
//     * the current model added with {@code toAdd}.<br>
//     * 5. Browser url and selected card remain unchanged.<br>
//     * 6. Status bar's sync status changes.<br>
//     * Verifications 1, 3 and 4 are performed by
//     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, AddressbookModel)}.<br>
//     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, AddressbookModel)
//     */
//    private void assertCommandSuccess(Person toAdd) {
//        assertCommandSuccess(AddressbookPersonUtil.getAddCommand(toAdd), toAdd);
//    }
//
//    /**
//     * Performs the same verification as {@code assertCommandSuccess(Person)}. Executes {@code command}
//     * instead.
//     * @see AddCommandSystemTest#assertCommandSuccess(Person)
//     */
//    private void assertCommandSuccess(String command, Person toAdd) {
//        AddressbookModel expectedAddressbookModel = getModel();
//        expectedAddressbookModel.addPerson(toAdd);
//        String expectedResultMessage = String.format(AddCommand.MESSAGE_SUCCESS, toAdd);
//
//        assertCommandSuccess(command, expectedAddressbookModel, expectedResultMessage);
//    }
//
//    /**
//     * Performs the same verification as {@code assertCommandSuccess(String, Person)} except asserts that
//     * the,<br>
//     * 1. Result display box displays {@code expectedResultMessage}.<br>
//     * 2. {@code Storage} and {@code PersonListPanel} equal to the corresponding components in
//     * {@code expectedAddressbookModel}.<br>
//     * @see AddCommandSystemTest#assertCommandSuccess(String, Person)
//     */
//    private void assertCommandSuccess(String command, AddressbookModel expectedAddressbookModel, String
// expectedResultMessage) {
//        executeCommand(command);
//        assertApplicationDisplaysExpected("", expectedResultMessage, expectedAddressbookModel);
//        assertSelectedCardUnchanged();
//        assertCommandBoxShowsDefaultStyle();
//        assertStatusBarUnchangedExceptSyncStatus();
//    }
//
//    /**
//     * Executes {@code command} and asserts that the,<br>
//     * 1. Command box displays {@code command}.<br>
//     * 2. Command box has the error style class.<br>
//     * 3. Result display box displays {@code expectedResultMessage}.<br>
//     * 4. {@code Storage} and {@code PersonListPanel} remain unchanged.<br>
//     * 5. Browser url, selected card and status bar remain unchanged.<br>
//     * Verifications 1, 3 and 4 are performed by
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
