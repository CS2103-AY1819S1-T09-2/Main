package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAnakin;

/**
 * Represents a storage for {@link ReadOnlyAnakin}
 */
public interface AnakinAnakinStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAnakinFilePath();

    /**
     * Returns Anakin data as a {@link ReadOnlyAnakin}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAnakin> readAnakin() throws DataConversionException, IOException;

    /**
     * @see #getAnakinFilePath()
     */
    Optional<ReadOnlyAnakin> readAnakin(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAnakin} to the storage.
     *
     * @param anakin cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAnakin(ReadOnlyAnakin anakin) throws IOException;

    /**
     * @see #saveAnakin(ReadOnlyAnakin)
     */
    void saveAnakin(ReadOnlyAnakin anakin, Path filePath) throws IOException;

}
