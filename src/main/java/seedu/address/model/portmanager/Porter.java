package seedu.address.model.portmanager;

import seedu.address.model.anakindeck.Deck;

public interface Porter {

    void exportDeck(Deck deck) throws Exception;

    Deck importDeck(String filepath);
}
