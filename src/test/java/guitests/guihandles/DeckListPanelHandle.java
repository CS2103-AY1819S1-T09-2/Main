package guitests.guihandles;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import seedu.address.model.anakindeck.AnakinDeck;

/**
 * Provides a handle for {@code DeckListPanel} containing the list of {@code DeckCard}.
 */
public class DeckListPanelHandle extends NodeHandle<ListView<AnakinDeck>> {
    public static final String DECK_LIST_VIEW_ID = "#deckListView";

    private static final String CARD_PANE_ID = "#cardPane";

    private Optional<AnakinDeck> lastRememberedSelectedDeckCard;

    public DeckListPanelHandle(ListView<AnakinDeck> deckListPanelNode) {
        super(deckListPanelNode);
    }

    /**
     * Returns a handle to the selected {@code DeckCardHandle}.
     * A maximum of 1 item can be selected at any time.
     * @throws AssertionError if no card is selected, or more than 1 card is selected.
     * @throws IllegalStateException if the selected card is currently not in the scene graph.
     */
    public DeckCardHandle getHandleToSelectedCard() {
        List<AnakinDeck> selectedDeckList = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedDeckList.size() != 1) {
            throw new AssertionError("Deck list size expected 1.");
        }

        return getAllCardNodes().stream()
                .map(DeckCardHandle::new)
                .filter(handle -> handle.equals(selectedDeckList.get(0)))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    /**
     * Returns the index of the selected deck card.
     */
    public int getSelectedCardIndex() {
        return getRootNode().getSelectionModel().getSelectedIndex();
    }

    /**
     * Returns true if a deck card is currently selected.
     */
    public boolean isAnyCardSelected() {
        List<AnakinDeck> selectedDeckCardsList = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedDeckCardsList.size() > 1) {
            throw new AssertionError("Card list size expected 0 or 1.");
        }

        return !selectedDeckCardsList.isEmpty();
    }

    /**
     * Navigates the listview to display {@code deck}.
     */
    public void navigateToCard(AnakinDeck deck) {
        if (!getRootNode().getItems().contains(deck)) {
            throw new IllegalArgumentException("Deck does not exist.");
        }

        guiRobot.interact(() -> {
            getRootNode().scrollTo(deck);
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Navigates the listview to {@code index}.
     */
    public void navigateToCard(int index) {
        if (index < 0 || index >= getRootNode().getItems().size()) {
            throw new IllegalArgumentException("Index is out of bounds.");
        }

        guiRobot.interact(() -> {
            getRootNode().scrollTo(index);
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Selects the {@code DeckCard} at {@code index} in the list.
     */
    public void select(int index) {
        getRootNode().getSelectionModel().select(index);
    }

    /**
     * Returns the deck card handle of a deck associated with the {@code index} in the list.
     * @throws IllegalStateException if the selected deck card is currently not in the scene graph.
     */
    public DeckCardHandle getDeckCardHandle(int index) {
        return getAllCardNodes().stream()
                .map(DeckCardHandle::new)
                .filter(handle -> handle.equals(getAnakinDeck(index)))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private AnakinDeck getAnakinDeck(int index) {
        return getRootNode().getItems().get(index);
    }

    /**
     * Returns all deck card nodes in the scene graph.
     * Deck card nodes that are visible in the listview are definitely in the scene graph, while some nodes that are not
     * visible in the listview may also be in the scene graph.
     */
    private Set<Node> getAllCardNodes() {
        return guiRobot.lookup(CARD_PANE_ID).queryAll();
    }

    /**
     * Remembers the selected {@code DeckCard} in the list.
     */
    public void rememberSelectedDeckCard() {
        List<AnakinDeck> selectedItems = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 0) {
            lastRememberedSelectedDeckCard = Optional.empty();
        } else {
            lastRememberedSelectedDeckCard = Optional.of(selectedItems.get(0));
        }
    }

    /**
     * Returns true if the selected {@code DeckCard} is different from the value remembered by the most recent
     * {@code rememberSelectedDeckCard()} call.
     */
    public boolean isSelectedDeckCardChanged() {
        List<AnakinDeck> selectedItems = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 0) {
            return lastRememberedSelectedDeckCard.isPresent();
        } else {
            return !lastRememberedSelectedDeckCard.isPresent()
                    || !lastRememberedSelectedDeckCard.get().equals(selectedItems.get(0));
        }
    }

    /**
     * Returns the size of the list.
     */
    public int getListSize() {
        return getRootNode().getItems().size();
    }
}
