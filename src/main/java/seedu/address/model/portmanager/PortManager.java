package seedu.address.model.portmanager;

import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.util.FileUtil;
import seedu.address.model.anakindeck.Deck;
import seedu.address.model.anakindeck.Name;
import seedu.address.storage.XmlAdaptedDeck;
import seedu.address.storage.XmlFileStorage;
import seedu.address.storage.XmlSerializableAnakin;

public class PortManager implements Porter{

    private static final Path basefilepath = Paths.get("");


    public void exportDeck(Deck deck) throws Exception{
        Name deckName = deck.getName();
        Path filePath = makeFilePath(deckName);
        System.out.println(filePath.toAbsolutePath().toString());
        XmlAdaptedDeck adaptedDeck = new XmlAdaptedDeck(deck);
        FileUtil.createIfMissing(filePath);
        XmlFileStorage.saveDataToFile(filePath, new XmlAdaptedDeck());

    }

    @Override
    public Deck importDeck(String filepath){
        String deckName = "deck1";
        return new Deck(new Name(deckName));
    }


    private Path makeFilePath(Name name){
        return basefilepath.resolve(name.toString());
    }

}
