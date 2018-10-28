package seedu.address.storage.portmanager;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBException;

import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.XmlUtil;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.Name;
import seedu.address.storage.XmlAdaptedDeck;
import seedu.address.storage.XmlFileStorage;

public class PortManager implements Porter {
    private Path basefilepath;

    public PortManager() {
        basefilepath = Paths.get("");
    }
    public PortManager(String bfp){
        basefilepath = Paths.get(bfp);
    }

    public void exportDeck(Deck deck) throws Exception{
        Name deckName = deck.getName();
        Path filePath = makeFilePath(deckName);
        System.out.println(filePath.toAbsolutePath().toString());
        XmlAdaptedDeck adaptedDeck = new XmlAdaptedDeck(deck);

        //If file doesn't exist, create it
        FileUtil.createIfMissing(filePath);

        //Write to file.
        try {
            XmlUtil.saveDataToFile(filePath, adaptedDeck);
        } catch (JAXBException e) {
            throw new AssertionError("Unexpected exception " + e.getMessage(), e);
        }

    }

    @Override
    public Deck importDeck(String filepath) throws FileNotFoundException{
        String deckName = "deck1";
        return new Deck(new Name(deckName));
    }


    private Path makeFilePath(Name name){
        return basefilepath.resolve(name.toString());
    }

}
