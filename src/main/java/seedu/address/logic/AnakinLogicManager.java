package seedu.address.logic;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.anakincommands.Command;
import seedu.address.logic.anakinparser.Parser;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.anakincommands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AnakinModel;
import seedu.address.model.anakindeck.Card;
import seedu.address.model.anakindeck.Deck;

/**
 * The main LogicManager of the app. Implements AnakinLogic
 */
public class AnakinLogicManager extends ComponentManager implements AnakinLogic {
    private final Logger logger = LogsCenter.getLogger(AnakinLogicManager.class);

    private final AnakinModel model;
    private final CommandHistory history;
    private final Parser Parser;

    public AnakinLogicManager(AnakinModel model) {
        this.model = model;
        history = new CommandHistory();
        Parser = new Parser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        try {
            Command command = Parser.parseCommand(commandText);
            return command.execute(model, history);
        } finally {
            history.add(commandText);
        }
    }

    @Override
    public ObservableList<Deck> getFilteredDeckList() {
        return model.getFilteredDeckList();
    }

    @Override
    public ObservableList<Card> getFilteredCardList() {
        return model.getFilteredCardList();
    }

    @Override
    public ListElementPointer getHistorySnapshot() {
        return new ListElementPointer(history.getHistory());
    }
}
