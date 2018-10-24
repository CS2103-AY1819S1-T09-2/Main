package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.AnakinTypicalDecks.getTypicalAnakin;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import seedu.address.commons.events.model.AnakinChangedEvent;
import seedu.address.commons.events.storage.DataSavingExceptionEvent;
import seedu.address.model.Anakin;
import seedu.address.model.AnakinReadOnlyAnakin;
import seedu.address.model.UserPrefs;
import seedu.address.ui.testutil.EventsCollectorRule;

public class StorageManagerTest {

    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    private StorageManager storageManager;

    @Before
    public void setUp() {
        XmlAnakinStorage anakinStorage = new XmlAnakinStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(anakinStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.getRoot().toPath().resolve(fileName);
    }


    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(300, 600, 4, 6);
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void anakinReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link XmlAnakinStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link XmlAnakinStorage} class.
         */
        Anakin original = getTypicalAnakin();
        storageManager.saveAnakin(original);
        AnakinReadOnlyAnakin retrieved = storageManager.readAnakin().get();
        assertEquals(original, new Anakin(retrieved));
    }

    @Test
    public void getAnakinFilePath() {
        assertNotNull(storageManager.getAnakinFilePath());
    }

    @Test
    public void handleAnakinChangedEvent_exceptionThrown_eventRaised() {
        // Create a StorageManager while injecting a stub that  throws an exception when the save method is called
        Storage storage = new StorageManager(new XmlAnakinStorageExceptionThrowingStub(Paths.get
                ("dummy")),
                new JsonUserPrefsStorage(Paths.get("dummy")));
        storage.handleAnakinChangedEvent(new AnakinChangedEvent(new Anakin()));
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof DataSavingExceptionEvent);
    }


    /**
     * A Stub class to throw an exception when the save method is called
     */
    class XmlAnakinStorageExceptionThrowingStub extends XmlAnakinStorage {

        public XmlAnakinStorageExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveAnakin(AnakinReadOnlyAnakin anakin, Path filePath) throws IOException {
            throw new IOException("dummy exception");
        }
    }


}
