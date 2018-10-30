package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;

/**
 * A view container for edit operations.
 */
public class DeckEditScreen extends UiPart<Region> {
    private static final String FXML = "DeckEditScreen.fxml";
    private final Logger logger = LogsCenter.getLogger(DeckEditScreen.class);

    @FXML
    private StackPane deckListPanelPlaceholder;

    @FXML
    private StackPane cardListPanelPlaceholder;

    public DeckEditScreen(DeckListPanel deckListPanel, CardListPanel cardListPanel) {
        super(FXML);
        cardListPanelPlaceholder.getChildren().add(cardListPanel.getRoot());
        deckListPanelPlaceholder.getChildren().add(deckListPanel.getRoot());
    }
}
