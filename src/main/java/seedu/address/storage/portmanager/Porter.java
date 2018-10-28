package seedu.address.storage.portmanager;

import java.io.FileNotFoundException;

import seedu.address.model.deck.Deck;

public interface Porter {

    void exportDeck(Deck deck) throws Exception;

    Deck importDeck(String filepath) throws FileNotFoundException;
}
