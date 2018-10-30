package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.deck.Card;

/**
 * An UI component for deck review.
 */
public class DeckReviewScreen extends UiPart<Region> {
    private static final String FXML = "DeckReviewScreen.fxml";
    public static final String id = "deckReview";
    private final Logger logger = LogsCenter.getLogger(DeckReviewScreen.class);

    @FXML
    private StackPane reviewCardPlaceholder;

    public DeckReviewScreen() {
        super(FXML);
        // TODO: display default 404 Card if card list is empty
    }

    public DeckReviewScreen(ObservableList<Card> cardList, int index) {
        super(FXML);
        // TODO: display DeckReviewCard card UI with Question and flip to show Answer at the back
        Card cardToShow = cardList.get(index);
        DeckReviewCard gameCardWithoutAnswer = new DeckReviewCard(cardToShow, false);
        DeckReviewCard gameCardWithAnswer = new DeckReviewCard(cardToShow, true);
        reviewCardPlaceholder.getChildren().add(gameCardWithAnswer.getRoot());
        reviewCardPlaceholder.getChildren().add(gameCardWithoutAnswer.getRoot());
    }
    // TODO: keep an index to track which card
    // TODO: Listen to flip card event to flip
    // TODO: Listen to prev and next commands to iterate through cards
}
