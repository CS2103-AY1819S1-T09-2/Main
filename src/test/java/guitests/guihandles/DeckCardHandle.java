package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.address.model.anakindeck.AnakinDeck;

/**
 * Provides a handle to a deck card in the deck list panel.
 */
public class DeckCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String NAME_FIELD_ID = "#name";

    private final Label idLabel;
    private final Label nameLabel;

    public DeckCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        nameLabel = getChildNode(NAME_FIELD_ID);
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getName() {
        return nameLabel.getText();
    }

    /**
     * Returns true if this handle contains {@code deck}.
     */
    public boolean equals(AnakinDeck deck) {
        return getName().equals(deck.getName().fullName);
    }
}
