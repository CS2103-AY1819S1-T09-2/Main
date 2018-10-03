package seedu.address.logic;

import java.util.logging.Logger;

import javafx.collections.ObservableList;

import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Anakin_commands.Anakin_Command;
import seedu.address.logic.Anakinparser.AnakinParser;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Anakin_deck.Anakin_Deck;
import seedu.address.model.Anakin_Model;


/**
 * The main LogicManager of the app. Implements AnakinLogic
 */
public class AnakinLogicManager extends ComponentManager implements AnakinLogic {
    private final Logger logger = LogsCenter.getLogger(AnakinLogicManager.class);

    private final Anakin_Model model;
    private final CommandHistory history;
    private final AnakinParser anakinParser;

    public AnakinLogicManager(Anakin_Model model) {
        this.model = model;
        history = new CommandHistory();
        anakinParser = new AnakinParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        try {
            Anakin_Command command = anakinParser.parseCommand(commandText);
            return command.execute(model, history);
        } finally {
            history.add(commandText);
        }
    }

    @Override
    public ObservableList<Anakin_Deck> getFilteredDeckList() {
        return model.getFilteredDeckList();
    }

    @Override
    public ListElementPointer getHistorySnapshot() {
        return new ListElementPointer(history.getHistory());
    }
}
