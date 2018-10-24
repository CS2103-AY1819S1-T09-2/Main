package seedu.address.ui;

import static java.time.Duration.ofMillis;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static seedu.address.testutil.AnakinTypicalDecks.getTypicalDecks;
import static seedu.address.testutil.AnakinTypicalIndexes.INDEX_SECOND_DECK;
import static seedu.address.testutil.EventsUtil.postNow;
import static seedu.address.ui.testutil.GuiTestAssert.assertDeckCardDisplaysDeck;
import static seedu.address.ui.testutil.GuiTestAssert.assertDeckCardEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import guitests.guihandles.DeckCardHandle;
import guitests.guihandles.DeckListPanelHandle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.XmlUtil;
import seedu.address.model.anakindeck.Deck;
import seedu.address.storage.AnakinXmlSerializableAnakin;

public class DeckListPanelTest extends GuiUnitTest {
    private static final ObservableList<Deck> TYPICAL_DECKS =
            FXCollections.observableList(getTypicalDecks());

    private static final JumpToListRequestEvent JUMP_TO_SECOND_EVENT = new JumpToListRequestEvent(INDEX_SECOND_DECK);

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "sandbox");

    private static final long CARD_CREATION_AND_DELETION_TIMEOUT = 2500;

    private DeckListPanelHandle deckListPanelHandle;

    @Test
    public void display() {
        initUi(TYPICAL_DECKS);

        for (int i = 0; i < TYPICAL_DECKS.size(); i++) {
            deckListPanelHandle.navigateToCard(TYPICAL_DECKS.get(i));
            Deck expectedDeck = TYPICAL_DECKS.get(i);
            DeckCardHandle actualCard = deckListPanelHandle.getDeckCardHandle(i);

            assertDeckCardDisplaysDeck(expectedDeck, actualCard);
            assertEquals(Integer.toString(i + 1) + ". ", actualCard.getId());
        }
    }

    @Test
    public void handleJumpToListRequestEvent() {
        initUi(TYPICAL_DECKS);
        postNow(JUMP_TO_SECOND_EVENT);
        guiRobot.pauseForHuman();

        DeckCardHandle expectedDeck = deckListPanelHandle.getDeckCardHandle(INDEX_SECOND_DECK.getZeroBased());
        DeckCardHandle selectedDeck = deckListPanelHandle.getHandleToSelectedCard();
        assertDeckCardEquals(expectedDeck, selectedDeck);
    }

    /**
     * Verifies that creating and deleting large number of decks in {@code DeckListPanel} requires lesser than
     * {@code CARD_CREATION_AND_DELETION_TIMEOUT} milliseconds to execute.
     */
    @Test
    public void performanceTest() throws Exception {
        ObservableList<Deck> backingList = createBackingList(10000);

        assertTimeoutPreemptively(ofMillis(CARD_CREATION_AND_DELETION_TIMEOUT), () -> {
            initUi(backingList);
            guiRobot.interact(backingList::clear);
        }, "Creation and deletion of deck cards exceeded time limit");
    }

    /**
     * Returns a list of decks containing {@code deckCount} decks that is used to populate the
     * {@code DeckListPanel}.
     */
    private ObservableList<Deck> createBackingList(int deckCount) throws Exception {
        Path xmlFile = createXmlFileWithDecks(deckCount);
        AnakinXmlSerializableAnakin xmlAnakin =
                XmlUtil.getDataFromFile(xmlFile, AnakinXmlSerializableAnakin.class);
        return FXCollections.observableArrayList(xmlAnakin.toModelType().getDeckList());
    }

    /**
     * Returns a .xml file containing {@code deckCount} decks. This file will be deleted when the JVM terminates.
     */
    private Path createXmlFileWithDecks(int deckCount) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
        builder.append("<anakin>\n");
        for (int i = 0; i < deckCount; i++) {
            builder.append("<decks>\n");
            builder.append("<name>").append(i).append("a</name>\n");
            builder.append("</decks>\n");
        }
        builder.append("</anakin>\n");

        Path manyDecksFile = TEST_DATA_FOLDER.resolve("manyDecks.xml");
        FileUtil.createFile(manyDecksFile);
        FileUtil.writeToFile(manyDecksFile, builder.toString());
        manyDecksFile.toFile().deleteOnExit();
        return manyDecksFile;
    }

    /**
     * Initializes {@code deckListPanelHandle} with a {@code DeckListPanel} backed by {@code backingList}.
     * Also shows the {@code Stage} that displays only {@code DeckListPanel}.
     */
    private void initUi(ObservableList<Deck> backingList) {
        DeckListPanel deckListPanel = new DeckListPanel(backingList);
        uiPartRule.setUiPart(deckListPanel);

        deckListPanelHandle = new DeckListPanelHandle(getChildNode(deckListPanel.getRoot(),
                DeckListPanelHandle.DECK_LIST_VIEW_ID));
    }
}
