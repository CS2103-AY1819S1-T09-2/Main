package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static seedu.address.testutil.AnakinTypicalDecks.DECK_A;
import static seedu.address.testutil.AnakinTypicalDecks.DECK_F;
import static seedu.address.testutil.AnakinTypicalDecks.DECK_G;
import static seedu.address.testutil.AnakinTypicalDecks.getTypicalAnakin;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Anakin;
import seedu.address.model.AnakinReadOnlyAnakin;

public class XmlAnakinStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "XmlAnakinStorageTest");
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    private java.util.Optional<AnakinReadOnlyAnakin> readAnakin(String filePath) throws Exception {
        return new XmlAnakinStorage(Paths.get(filePath)).readAnakin(addToTestDataPathIfNotNull(filePath));
    }

    @Test
    public void readAnakin_nullFilePath_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        readAnakin(null);
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAnakin("NonExistentFile.xml").isPresent());
    }

    @Test
    public void read_notXmlFormat_exceptionThrown() throws Exception {
        thrown.expect(DataConversionException.class);
        readAnakin("NotXmlFormatAnakin.xml");
    }

    @Test
    public void readAnakin_invalidDeckAnakin_throwDataConversionException() throws Exception {
        thrown.expect(DataConversionException.class);
        readAnakin("invalidDeckAnakin.xml");
    }

    @Test
    public void readAnakin_invalidAndValidDeckAnakin_throwDataConversionException() throws Exception {
        thrown.expect(DataConversionException.class);
        readAnakin("invalidAndValidDeckAnakin.xml");
    }

    @Test
    public void readAndSaveAnakin_allInOrder_success() throws Exception {
        Path filePath = testFolder.getRoot().toPath().resolve("TempAnakin.xml");
        Anakin original = getTypicalAnakin();
        XmlAnakinStorage xmlAnakinStorage = new XmlAnakinStorage(filePath);

        //Save in new file and read back
        xmlAnakinStorage.saveAnakin(original, filePath);
        AnakinReadOnlyAnakin readBack = xmlAnakinStorage.readAnakin(filePath).get();
        assertEquals(original, new Anakin(readBack));

        //Modify data, overwrite exiting file, and read back
        original.addDeck(DECK_F);
        original.removeDeck(DECK_A);
        xmlAnakinStorage.saveAnakin(original, filePath);
        readBack = xmlAnakinStorage.readAnakin(filePath).get();
        assertEquals(original, new Anakin(readBack));

        //Save and read without specifying file path
        original.addDeck(DECK_G);
        xmlAnakinStorage.saveAnakin(original); //file path not specified
        readBack = xmlAnakinStorage.readAnakin().get(); //file path not specified
        assertEquals(original, new Anakin(readBack));
    }

    /**
     * Saves {@code anakin} at the specified {@code filePath}.
     */
    private void saveAnakin(AnakinReadOnlyAnakin anakin, String filePath) {
        try {
            new XmlAnakinStorage(Paths.get(filePath))
                    .saveAnakin(anakin, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAnakin_nullAnakin_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        saveAnakin(null, "SomeFile.xml");
    }

    @Test
    public void saveAnakin_nullFilePath_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        saveAnakin(new Anakin(), null);
    }
}
