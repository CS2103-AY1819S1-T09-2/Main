package seedu.address.ui;

import static java.time.Duration.ofMillis;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static seedu.address.testutil.EventsUtil.postNow;
import static seedu.address.testutil.TypicalCards.getTypicalCards;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CARD;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardCardDisplaysCard;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardCardEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import guitests.guihandles.CardCardHandle;
import guitests.guihandles.CardListPanelHandle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.XmlUtil;
import seedu.address.model.anakindeck.Card;
import seedu.address.storage.XmlSerializableAnakin;

public class CardListPanelTest extends GuiUnitTest {
    private static final ObservableList<Card> TYPICAL_CARDS =
        FXCollections.observableList(getTypicalCards());

    private static final JumpToListRequestEvent JUMP_TO_SECOND_EVENT = new JumpToListRequestEvent(INDEX_SECOND_CARD);

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "sandbox");

    private static final long CARD_CREATION_AND_DELETION_TIMEOUT = 2500;

    private CardListPanelHandle cardListPanelHandle;

    @Test
    public void display() {
        initUi(TYPICAL_CARDS);

        for (int i = 0; i < TYPICAL_CARDS.size(); i++) {
            cardListPanelHandle.navigateToCard(TYPICAL_CARDS.get(i));
            Card expectedCard = TYPICAL_CARDS.get(i);
            CardCardHandle actualCard = cardListPanelHandle.getCardCardHandle(i);

            assertCardCardDisplaysCard(expectedCard, actualCard);
            assertEquals(Integer.toString(i + 1) + ". ", actualCard.getId());
        }
    }

    @Test
    public void handleJumpToListRequestEvent() {
        initUi(TYPICAL_CARDS);
        postNow(JUMP_TO_SECOND_EVENT);
        guiRobot.pauseForHuman();

        CardCardHandle expectedCard = cardListPanelHandle.getCardCardHandle(INDEX_SECOND_CARD.getZeroBased());
        CardCardHandle selectedDeck = cardListPanelHandle.getHandleToSelectedCard();
        assertCardCardEquals(expectedCard, selectedDeck);
    }

    /**
     * Verifies that creating and deleting large number of decks in {@code DeckListPanel} requires lesser than
     * {@code CARD_CREATION_AND_DELETION_TIMEOUT} milliseconds to execute.
     */
    @Test
    public void performanceTest() throws Exception {
        ObservableList<Card> backingList = createBackingList(10000);

        assertTimeoutPreemptively(ofMillis(CARD_CREATION_AND_DELETION_TIMEOUT), () -> {
            initUi(backingList);
            guiRobot.interact(backingList::clear);
        }, "Creation and deletion of deck cards exceeded time limit");
    }

    /**
     * Returns a list of decks containing {@code deckCount} decks that is used to populate the
     * {@code DeckListPanel}.
     */
    private ObservableList<Card> createBackingList(int cardCount) throws Exception {
        Path xmlFile = createXmlFileWithDeckWithCards(cardCount);
        XmlSerializableAnakin xmlAnakin =
            XmlUtil.getDataFromFile(xmlFile, XmlSerializableAnakin.class);
        List<Card> cardList = xmlAnakin.toModelType().getDeckList().get(0).getCards().internalList;
        return FXCollections.observableArrayList(cardList);
    }

    /**
     * Returns a .xml file containing {@code deckCount} decks. This file will be deleted when the JVM terminates.
     */
    private Path createXmlFileWithDeckWithCards(int cardCount) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
        builder.append("<anakin>\n");
        builder.append("<decks>\n");
        builder.append("<name>").append("Sample Deck").append("a</name>\n");
        for (int i = 0; i < cardCount; i++) {
            builder.append("<cards>\n");
            builder.append("<question>").append(i).append("a</question>\n");
            builder.append("<answer>").append(i).append("a</answer>\n");
            builder.append("</cards>\n");
        }
        builder.append("</decks>\n");
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
    private void initUi(ObservableList<Card> backingList) {
        CardListPanel cardListPanel = new CardListPanel(backingList);
        uiPartRule.setUiPart(cardListPanel);

        cardListPanelHandle = new CardListPanelHandle(getChildNode(cardListPanel.getRoot(),
            CardListPanelHandle.CARD_LIST_VIEW_ID));
    }
}
