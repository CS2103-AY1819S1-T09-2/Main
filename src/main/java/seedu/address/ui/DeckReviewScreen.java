package seedu.address.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.FlipCardRequestEvent;
import seedu.address.commons.events.ui.ReviewNextCardEvent;
import seedu.address.commons.events.ui.ReviewPreviousCardEvent;
import seedu.address.model.deck.Card;

/**
 * An UI component for deck review.
 */
public class DeckReviewScreen extends UiPart<Region> {
    private static final String FXML = "DeckReviewScreen.fxml";
    public static final String id = "deckReview";
    private final Logger logger = LogsCenter.getLogger(DeckReviewScreen.class);

    private int index;
    private ObservableList<Card> cardList;

    @FXML
    private StackPane reviewCardPlaceholder;

    public DeckReviewScreen() {
        super(FXML);
    }

    public DeckReviewScreen(ObservableList<Card> cardList, int index) {
        super(FXML);
        registerAsAnEventHandler(this);
        // TODO: should UI handle the card list?
        this.index = index;
        this.cardList = cardList;

        Card cardToShow = cardList.get(index);
        updateCard(cardToShow);
    }

    public void updateCard(Card cardToShow) {
        DeckReviewCard gameCardWithoutAnswer = new DeckReviewCard(cardToShow, false);
        DeckReviewCard gameCardWithAnswer = new DeckReviewCard(cardToShow, true);
        reviewCardPlaceholder.getChildren().add(gameCardWithAnswer.getRoot());
        reviewCardPlaceholder.getChildren().add(gameCardWithoutAnswer.getRoot());
    }

    // TODO: keep an index to track which card
    // TODO: Listen to flip card event to flip
    public void handleFlipCard() {
        Node currentFront = reviewCardPlaceholder.getChildren().get(reviewCardPlaceholder.getChildren().size() - 1);
        currentFront.toBack();
    }

    @Subscribe
    private void handleFlipCardEvent(FlipCardRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        handleFlipCard();
    }

    // TODO: Listen to prev and next commands to iterate through cards
    public void handleNextCard() {
        int newIndex = index + 1;
        if (newIndex == cardList.size()) {
            newIndex = 0;
        }
        Card nextCard = cardList.get(newIndex);
        updateCard(nextCard);
        index = newIndex;
    }

    @Subscribe
    private void handleNextCardEvent(ReviewNextCardEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        handleNextCard();
    }

    public void handlePreviousCard() {
        int newIndex = index - 1;
        if (newIndex < 0) {
            newIndex = cardList.size() - 1;
        }
        Card nextCard = cardList.get(newIndex);
        updateCard(nextCard);
        index = newIndex;
    }

    @Subscribe
    private void handlePreviousCardEvent(ReviewPreviousCardEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        handlePreviousCard();
    }
}
