package guitests.guihandles;

import javafx.stage.Stage;

/**
 * Provides a handle for {@code MainWindow}.
 */
public class MainWindowHandle extends StageHandle {

    private final DeckListPanelHandle deckListPanel;
    private final CardListPanelHandle cardListPanel;
    private final ResultDisplayHandle resultDisplay;
    private final CommandBoxHandle commandBox;
    private final StatusBarFooterHandle statusBarFooter;
    private final MainMenuHandle mainMenu;

    public MainWindowHandle(Stage stage) {
        super(stage);

        deckListPanel = new DeckListPanelHandle(getChildNode(DeckListPanelHandle.DECK_LIST_VIEW_ID));
        cardListPanel = new CardListPanelHandle(getChildNode(CardListPanelHandle.CARD_LIST_VIEW_ID));
        resultDisplay = new ResultDisplayHandle(getChildNode(ResultDisplayHandle.RESULT_DISPLAY_ID));
        commandBox = new CommandBoxHandle(getChildNode(CommandBoxHandle.COMMAND_INPUT_FIELD_ID));
        statusBarFooter = new StatusBarFooterHandle(getChildNode(StatusBarFooterHandle.STATUS_BAR_PLACEHOLDER));
        mainMenu = new MainMenuHandle(getChildNode(MainMenuHandle.MENU_BAR_ID));
        // TODO: Add deck edit screen and deck review screen
    }

    public DeckListPanelHandle getDeckListPanel() {
        return deckListPanel;
    }

    public CardListPanelHandle getCardListPanel() {
        return cardListPanel;
    }


    public ResultDisplayHandle getResultDisplay() {
        return resultDisplay;
    }

    public CommandBoxHandle getCommandBox() {
        return commandBox;
    }

    public StatusBarFooterHandle getStatusBarFooter() {
        return statusBarFooter;
    }

    public MainMenuHandle getMainMenu() {
        return mainMenu;
    }
}
