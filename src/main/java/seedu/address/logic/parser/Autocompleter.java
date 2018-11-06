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
    public boolean isAutocompletable() {
        // Generate a list of all possible completions
        // Every command has a completion
        return true;

    }

    public static String getAutocompletion(String input) {
        return "bob";
    }
    /**
     * This is a temporary javadoc comment
     * if there exists such a word, it is replaced with the relevant autocompletion command.
     */
    public List<String> generateCompletions() {
        return new ArrayList<String>();
        // Add all possible completions and their completion fields
    }

}
