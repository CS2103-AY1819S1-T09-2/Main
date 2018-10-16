package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AnakinReadOnlyAnakin;

/**
 * Represents a storage for {@link seedu.address.model.AnakinReadOnlyAnakin}
 */
public interface AnakinAnakinStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAnakinFilePath();

    /**
     * Returns Anakin data as a {@link seedu.address.model.AnakinReadOnlyAnakin}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<AnakinReadOnlyAnakin> readAnakin() throws DataConversionException, IOException;

    /**
     * @see #getAnakinFilePath()
     */
    Optional<AnakinReadOnlyAnakin> readAnakin(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link AnakinReadOnlyAnakin} to the storage.
     *
     * @param anakin cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAnakin(AnakinReadOnlyAnakin anakin) throws IOException;

    /**
     * @see #saveAnakin(AnakinReadOnlyAnakin)
     */
    void saveAnakin(AnakinReadOnlyAnakin anakin, Path filePath) throws IOException;

}
