package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.ReadOnlyAnakin;

/**
 * A class to access AddressBook data stored as an xml file on the hard disk.
 */
public class XmlAnakinStorage implements AnakinStorage {

    private static final Logger logger = LogsCenter.getLogger(XmlAnakinStorage.class);

    private Path filePath;

    public XmlAnakinStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAnakinFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAnakin> readAnakin() throws DataConversionException, IOException {
        return readAnakin(filePath);
    }

    /**
     * Similar to {@link #readAnakin()}
     *
     * @param filePath location of the data. Cannot be null
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAnakin> readAnakin(Path filePath) throws DataConversionException,
            FileNotFoundException {
        requireNonNull(filePath);

        if (!Files.exists(filePath)) {
            logger.info("Anakin file " + filePath + " not found");
            return Optional.empty();
        }

        XmlSerializableAnakin xmlAnakin = XmlFileStorage.loadDataFromSaveFile(filePath);
        try {
            return Optional.of(xmlAnakin.toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAnakin(ReadOnlyAnakin anakin) throws IOException {
        saveAnakin(anakin, filePath);
    }

    /**
     * Similar to {@link #saveAnakin(ReadOnlyAnakin)}
     *
     * @param filePath location of the data. Cannot be null
     */
    public void saveAnakin(ReadOnlyAnakin anakin, Path filePath) throws IOException {
        requireNonNull(anakin);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        XmlFileStorage.saveDataToFile(filePath, new XmlSerializableAnakin(anakin));
    }

}
