package seedu.address.storage;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.XmlUtil;
import seedu.address.model.Anakin;
import seedu.address.testutil.AnakinTypicalDecks;

public class AnakinXmlSerializableAnakinTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "XmlSerializableAnakinTest");
    private static final Path TYPICAL_DECKS_FILE = TEST_DATA_FOLDER.resolve("typicalDecksAnakin.xml");
    private static final Path INVALID_DECK_FILE = TEST_DATA_FOLDER.resolve("invalidDeckAnakin.xml");
    private static final Path DUPLICATE_DECK_FILE = TEST_DATA_FOLDER.resolve("duplicateDeckAnakin.xml");
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toModelType_typicalDecksFile_success() throws Exception {
        AnakinXmlSerializableAnakin dataFromFile = XmlUtil.getDataFromFile(TYPICAL_DECKS_FILE,
                AnakinXmlSerializableAnakin.class);
        Anakin anakinFromFile = dataFromFile.toModelType();
        Anakin typicalDecksAnakin = AnakinTypicalDecks.getTypicalAnakin();
        assertEquals(anakinFromFile, typicalDecksAnakin);
    }
    @Test
    public void toModelType_invalidDecksFile_throwsIllegalValueException() throws Exception {
        AnakinXmlSerializableAnakin dataFromFile = XmlUtil.getDataFromFile(INVALID_DECK_FILE,
                AnakinXmlSerializableAnakin.class);
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }
    @Test
    public void toModelType_duplicateDecksFile_throwsIllegalValueException() throws Exception {
        AnakinXmlSerializableAnakin dataFromFile = XmlUtil.getDataFromFile(DUPLICATE_DECK_FILE,
                AnakinXmlSerializableAnakin.class);
        thrown.expect(IllegalValueException.class);
        thrown.expectMessage(AnakinXmlSerializableAnakin.MESSAGE_DUPLICATE_DECK);
        dataFromFile.toModelType();
    }
}
