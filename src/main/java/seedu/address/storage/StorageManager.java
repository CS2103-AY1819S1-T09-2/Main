package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.AnakinChangedEvent;
import seedu.address.commons.events.storage.DataSavingExceptionEvent;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AnakinReadOnlyAnakin;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Anakin data in local storage.
 */
public class StorageManager extends ComponentManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AnakinAnakinStorage anakinStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(AnakinAnakinStorage anakinStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.anakinStorage = anakinStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ Anakin methods ==============================

    @Override
    public Path getAnakinFilePath() {
        return anakinStorage.getAnakinFilePath();
    }

    @Override
    public Optional<AnakinReadOnlyAnakin> readAnakin() throws DataConversionException, IOException {
        return readAnakin(anakinStorage.getAnakinFilePath());
    }

    @Override
    public Optional<AnakinReadOnlyAnakin> readAnakin(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return anakinStorage.readAnakin(filePath);
    }

    @Override
    public void saveAnakin(AnakinReadOnlyAnakin anakin) throws IOException {
        saveAnakin(anakin, anakinStorage.getAnakinFilePath());
    }

    @Override
    public void saveAnakin(AnakinReadOnlyAnakin anakin, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        anakinStorage.saveAnakin(anakin, filePath);
    }


    @Override
    @Subscribe
    public void handleAnakinChangedEvent(AnakinChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
        try {
            saveAnakin(event.data);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }

}
