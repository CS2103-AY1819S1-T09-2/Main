package seedu.address.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.DeckPanelSelectionChangedEvent;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.model.anakindeck.Deck;

/**
 * Panel containing the list of decks.
 */
public class DeckListPanel extends UiPart<Region> {
    private static final String FXML = "DeckListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CardListPanel.class);

    @FXML
    private ListView<Deck> deckListView;

    public DeckListPanel(ObservableList<Deck> deckList) {
        super(FXML);
        setConnections(deckList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Deck> deckList) {
        deckListView.setItems(deckList);
        deckListView.setCellFactory(listView -> new DeckListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void setEventHandlerForSelectionChangeEvent() {
        deckListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.fine("Selection in deck list panel changed to : '" + newValue + "'");
                        raise(new DeckPanelSelectionChangedEvent(newValue));
                    }
                });
    }

    /**
     * Scrolls to the {@code Deck} at the {@code index} and selects it.
     */
    private void scrollTo(int index) {
        Platform.runLater(() -> {
            deckListView.scrollTo(index);
            deckListView.getSelectionModel().clearAndSelect(index);
        });
    }

    @Subscribe
    private void handleJumpToListRequestEvent(JumpToListRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        scrollTo(event.targetIndex);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Deck} using a {@code DeckCard}.
     */
    class DeckListViewCell extends ListCell<Deck> {
        @Override
        protected void updateItem(Deck card, boolean empty) {
            super.updateItem(card, empty);

            if (empty || card == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DeckCard(card, getIndex() + 1).getRoot());
            }
        }
    }

}
