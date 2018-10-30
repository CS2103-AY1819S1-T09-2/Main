package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.deck.Card;

/**
 * An UI component that displays information of a {@code Card} during deck review.
 */
public class DeckReviewCard extends UiPart<Region> {
    private static final String FXML = "DeckReviewCard.fxml";
    public final Card card;
    private final Logger logger = LogsCenter.getLogger(DeckReviewScreen.class);

    @FXML
    private Label text;

    public DeckReviewCard(Card card, Boolean showAnswer) {
        super(FXML);
        this.card = card;
        if (!showAnswer) {
            text.setText("Q: " + card.getQuestion().fullQuestion);
        } else {
            text.setText("A: " + card.getAnswer().fullAnswer);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeckReviewCard)) {
            return false;
        }

        // state check
        DeckReviewCard card = (DeckReviewCard) other;
        return text.getText().equals(card.text.getText())
                && this.card.equals(card.card);
    }
}
