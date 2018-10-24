package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.address.model.anakindeck.AnakinCard;

/**
 * Provides a handle to a card card in the card list panel.
 */
public class CardCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String QUESTION_FIELD_ID = "#question";
    private static final String ANSWER_FIELD_ID = "#name";

    private final Label idLabel;
    private final Label questionLabel;

    public CardCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        questionLabel = getChildNode(QUESTION_FIELD_ID);
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getQuestion() {
        return questionLabel.getText();
    }


    /**
     * Returns true if this handle contains {@code deck}.
     */
    public boolean equals(AnakinCard card) {
        return getQuestion().equals(card.getQuestion().fullQuestion);
    }
}
