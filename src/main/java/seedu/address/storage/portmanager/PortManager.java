package seedu.address.storage.portmanager;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.XmlUtil;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.Name;
import seedu.address.model.deck.anakinexceptions.DeckImportException;
import seedu.address.storage.XmlExportableDeck;


/**
 * Manages the import and exports of decks
 */

public class PortManager implements Porter {

    private static final Logger logger = LogsCenter.getLogger(PortManager.class);

    private Path basefilepath;

    public PortManager() {
        basefilepath = Paths.get("");
    }

    public PortManager(Path bfp) {
        basefilepath = bfp;
    }

    @Override
    public String exportDeck(Deck deck) {
        Name deckName = deck.getName();
        Path filePath = makeFilePath(deckName.fullName);

        XmlExportableDeck adaptedDeck = new XmlExportableDeck(deck);

        try {
            //If file doesn't exist, create it
            FileUtil.createIfMissing(filePath);

            //Write to file.
            XmlUtil.saveDataToFile(filePath, adaptedDeck);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Unexpected exception " + e.getMessage(), e);
        }

        return filePath.toAbsolutePath().toString();
    }

    @Override
    public Deck importDeck(String stringPath) throws DeckImportException {
        Path filepath = makeFilePath(stringPath);
        try {
            XmlExportableDeck xmlDeck = loadDeckFromFile(filepath);
            return getImportedDeck(xmlDeck);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new DeckImportException("Target file not found");
        } catch (DataConversionException e) {
            e.printStackTrace();
            throw new DeckImportException("Target deck contains invalid data");
        }
    }

    /**
     * Attempts to load the data from the file at filepath.
     * Returns a XmlExportableDeck object.
     */

    private XmlExportableDeck loadDeckFromFile(Path filepath) throws FileNotFoundException, DataConversionException {
        XmlExportableDeck xmlDeck;
        try {
            xmlDeck = XmlUtil.getDataFromFile(filepath, XmlExportableDeck.class);
            return xmlDeck;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Unexpected exception " + e.getMessage(), e);
        }

    }

    /**
     * Converts the XmlExportableDeck to a Deck
     * Returns a deck object
     * If contents are invalid, throw DataConversionException
     */

    private Deck getImportedDeck(XmlExportableDeck targetDeck) throws DataConversionException {
        try {
            return targetDeck.toModelType();
        } catch (IllegalValueException e) {
            e.printStackTrace();
            logger.info("Illegal values found in " + targetDeck + ": " + e.getMessage());
            throw new DataConversionException(e);
        }

    }


    private Path makeFilePath(String name) {
        return basefilepath.resolve(name + ".xml");
    }

}
