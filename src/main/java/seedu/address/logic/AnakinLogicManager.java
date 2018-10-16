package seedu.address.logic;

import java.util.logging.Logger;

import javafx.collections.ObservableList;

import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.anakincommands.AnakinCommand;
import seedu.address.logic.anakinparser.AnakinParser;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AnakinModel;
import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.model.anakindeck.AnakinDeck;

/**
 * The main LogicManager of the app. Implements AnakinLogic
 */
public class AnakinLogicManager extends ComponentManager implements AnakinLogic {
    private final Logger logger = LogsCenter.getLogger(AnakinLogicManager.class);

    private final AnakinModel model;
    private final CommandHistory history;
    private final AnakinParser AnakinParser;

    public AnakinLogicManager(AnakinModel model) {
        this.model = model;
        history = new CommandHistory();
        AnakinParser = new AnakinParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        try {
            AnakinCommand command = AnakinParser.parseCommand(commandText);
            return command.execute(model, history);
        } finally {
            history.add(commandText);
        }
    }

    @Override
    public ObservableList<AnakinDeck> getFilteredDeckList() {
        return model.getFilteredDeckList();
    }

    @Override
    public ObservableList<AnakinCard> getFilteredCardList() {
        return model.getFilteredCardList();
    }

    @Override
    public ListElementPointer getHistorySnapshot() {
        return new ListElementPointer(history.getHistory());
    }
}
