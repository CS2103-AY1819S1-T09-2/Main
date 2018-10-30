package seedu.address.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

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
    private final Logger logger = LogsCenter.getLogger(DeckReviewScreen.class);

    @FXML
    private StackPane reviewCardPlaceholder;

    public DeckReviewScreen() {
        super(FXML);
    }

    public DeckReviewScreen(Card card) {
        super(FXML);
        registerAsAnEventHandler(this);
        updateCard(card);
    }

    /**
     * Updates UI with new cardToShow
     * @param cardToShow on the screen
     */
    public void updateCard(Card cardToShow) {
        DeckReviewCard gameCardWithoutAnswer = new DeckReviewCard(cardToShow, false);
        DeckReviewCard gameCardWithAnswer = new DeckReviewCard(cardToShow, true);
        reviewCardPlaceholder.getChildren().add(gameCardWithAnswer.getRoot());
        reviewCardPlaceholder.getChildren().add(gameCardWithoutAnswer.getRoot());
    }

    /**
     * Reorders the nodes in stack pane to show different cards
     */
    public void handleFlipCard() {
        Node currentFront = reviewCardPlaceholder.getChildren().get(reviewCardPlaceholder.getChildren().size() - 1);
        currentFront.toBack();
    }

    @Subscribe
    private void handleFlipCardEvent(FlipCardRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        handleFlipCard();
    }

    public void handleNextCard(Card nextCard) {
        updateCard(nextCard);
    }

    @Subscribe
    private void handleNextCardEvent(ReviewNextCardEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        handleNextCard(event.getNextCard());
    }

    public void handlePreviousCard(Card prevCard) {
        updateCard(prevCard);
    }

    @Subscribe
    private void handlePreviousCardEvent(ReviewPreviousCardEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        handlePreviousCard(event.getPreviousCard());
    }
}
