package seedu.address.logic.parser;

import static seedu.address.commons.core.AddressbookMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.AddressbookCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.AddressbookCommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.AddressbookTypicalPersons.AMY;
import static seedu.address.testutil.AddressbookTypicalPersons.BOB;

import org.junit.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.AddressbookPersonBuilder;

public class AddressbookAddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new AddressbookPersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new AddressbookPersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new AddressbookPersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_NAME_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_PHONE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_EMAIL_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_ADDRESS_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_TAG_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_NAME_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}