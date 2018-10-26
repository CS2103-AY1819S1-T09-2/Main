package seedu.address.commons.util;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.Anakin;
import seedu.address.storage.XmlAdaptedCard;
import seedu.address.storage.XmlAdaptedDeck;
import seedu.address.storage.XmlSerializableAnakin;
import seedu.address.testutil.AnakinBuilder;
import seedu.address.testutil.DeckBuilder;
import seedu.address.testutil.TestUtil;

public class XmlUtilTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "XmlUtilTest");
    private static final Path EMPTY_FILE = TEST_DATA_FOLDER.resolve("empty.xml");
    private static final Path MISSING_FILE = TEST_DATA_FOLDER.resolve("missing.xml");
    private static final Path VALID_FILE = TEST_DATA_FOLDER.resolve("validAnakin.xml");
    private static final Path MISSING_DECK_FIELD_FILE = TEST_DATA_FOLDER.resolve("missingDeckField.xml");
    private static final Path INVALID_DECK_FIELD_FILE = TEST_DATA_FOLDER.resolve("invalidDeckField.xml");
    private static final Path VALID_DECK_FILE = TEST_DATA_FOLDER.resolve("validDeck.xml");
    private static final Path TEMP_FILE = TestUtil.getFilePathInSandboxFolder("tempAnakin.xml");

    private static final String INVALID_NAME = " ";

    private static final String VALID_NAME = "Qui Gon Jinn";
    private static final List<XmlAdaptedCard> VALID_CARDS = Collections
        .singletonList(new XmlAdaptedCard("How is", "Gamora"));

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getDataFromFile_nullFile_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        XmlUtil.getDataFromFile(null, Anakin.class);
    }

    @Test
    public void getDataFromFile_nullClass_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        XmlUtil.getDataFromFile(VALID_FILE, null);
    }

    @Test
    public void getDataFromFile_missingFile_fileNotFoundException() throws Exception {
        thrown.expect(FileNotFoundException.class);
        XmlUtil.getDataFromFile(MISSING_FILE, Anakin.class);
    }

    @Test
    public void getDataFromFile_emptyFile_dataFormatMismatchException() throws Exception {
        thrown.expect(JAXBException.class);
        XmlUtil.getDataFromFile(EMPTY_FILE, Anakin.class);
    }

    @Test
    public void getDataFromFile_validFile_validResult() throws Exception {
        Anakin dataFromFile = XmlUtil.getDataFromFile(VALID_FILE, XmlSerializableAnakin.class).toModelType();
        assertEquals(3, dataFromFile.getDeckList().size());
    }

    @Test
    public void xmlAdaptedDeckFromFile_fileWithMissingDeckField_validResult() throws Exception {
        XmlAdaptedDeck actualDeck = XmlUtil.getDataFromFile(
            MISSING_DECK_FIELD_FILE, XmlAdaptedDeckWithRootElement.class);
        XmlAdaptedDeck expectedDeck = new XmlAdaptedDeck(
            null, VALID_CARDS);
        assertEquals(expectedDeck, actualDeck);
    }

    @Test
    public void xmlAdaptedDeckFromFile_fileWithInvalidDeckField_validResult() throws Exception {
        XmlAdaptedDeck actualDeck = XmlUtil.getDataFromFile(
            INVALID_DECK_FIELD_FILE, XmlAdaptedDeckWithRootElement.class);
        XmlAdaptedDeck expectedDeck = new XmlAdaptedDeck(
            INVALID_NAME, VALID_CARDS);
        assertEquals(expectedDeck, actualDeck);
    }

    @Test
    public void xmlAdaptedDeckFromFile_fileWithValidDeck_validResult() throws Exception {
        XmlAdaptedDeck actualDeck = XmlUtil.getDataFromFile(
            VALID_DECK_FILE, XmlAdaptedDeckWithRootElement.class);
        XmlAdaptedDeck expectedDeck = new XmlAdaptedDeck(
            VALID_NAME, VALID_CARDS);
        assertEquals(expectedDeck, actualDeck);
    }

    @Test
    public void saveDataToFile_nullFile_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        XmlUtil.saveDataToFile(null, new Anakin());
    }

    @Test
    public void saveDataToFile_nullClass_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        XmlUtil.saveDataToFile(VALID_FILE, null);
    }

    @Test
    public void saveDataToFile_missingFile_fileNotFoundException() throws Exception {
        thrown.expect(FileNotFoundException.class);
        XmlUtil.saveDataToFile(MISSING_FILE, new Anakin());
    }

    @Test
    public void saveDataToFile_validFile_dataSaved() throws Exception {
        FileUtil.createFile(TEMP_FILE);
        XmlSerializableAnakin dataToWrite = new XmlSerializableAnakin(new Anakin());
        XmlUtil.saveDataToFile(TEMP_FILE, dataToWrite);
        XmlSerializableAnakin dataFromFile = XmlUtil.getDataFromFile(TEMP_FILE, XmlSerializableAnakin.class);
        assertEquals(dataToWrite, dataFromFile);

        AnakinBuilder builder = new AnakinBuilder(new Anakin());
        dataToWrite = new XmlSerializableAnakin(
            builder.withDeck(new DeckBuilder().build()).build());

        XmlUtil.saveDataToFile(TEMP_FILE, dataToWrite);
        dataFromFile = XmlUtil.getDataFromFile(TEMP_FILE, XmlSerializableAnakin.class);
        assertEquals(dataToWrite, dataFromFile);
    }

    /**
     * Test class annotated with {@code XmlRootElement} to allow unmarshalling of .xml data to {@code
     * XmlAdaptedDeck}
     * objects.
     */
    @XmlRootElement(name = "deck")
    private static class XmlAdaptedDeckWithRootElement extends XmlAdaptedDeck {
    }
}
