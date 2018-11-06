package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * The logic component that is responsible for performing autocompletion based on user input.
 */

public class Autocompleter {

    /**
     * Checks if {@code commandBox}'s commandTextField has a potential autocompletable command word,
     * if there exists such a word, it is replaced with the relevant autocompletion command.
     */
    public static boolean isAutocompletable(String input) {
        // Check if the give input string has an existing autocompletion in the list of command words which starts
        // with what the user has currently typed
        // listOfCommandWords.stream().filter(command ->command.startsWith(string)).collect().size() > 0;


        return true;

    }

    public static String getAutocompletion(String input) {
        // Filter from the list of available autocompletions and pick the first one if it exists
        // listOfCompletions.filter(completion -> completion.startsWith(input)).get(0);
        return "bob";
    }
    /**
     * This is a temporary javadoc comment
     * if there exists such a word, it is replaced with the relevant autocompletion command.
     */
    public List<String> generateAutoCompletions() {
        return new ArrayList<String>();
        // Add all possible completions and their completion fields
    }

}
