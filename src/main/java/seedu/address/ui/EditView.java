package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;

/**
 * A view container for edit operations.
 */
public class EditView extends UiPart<Region> {
    private static final String FXML = "EditView.fxml";
    private final Logger logger = LogsCenter.getLogger(EditView.class);

    @FXML
    private StackPane deckListPanelPlaceholder;

    @FXML
    private StackPane cardListPanelPlaceholder;

    public EditView(DeckListPanel deckListPanel, CardListPanel cardListPanel) {
        super(FXML);
        cardListPanelPlaceholder.getChildren().add(cardListPanel.getRoot());
        deckListPanelPlaceholder.getChildren().add(deckListPanel.getRoot());
    }
}
