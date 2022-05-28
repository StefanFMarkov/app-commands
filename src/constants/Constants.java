package constants;

public class Constants {

    private Constants() {
    }

    public static final String GOODBYE = """
            Have a nice day!
            """;
    public static final String EXIT = "exit";

    public static final String GET = "get";

    public static final String SET = "set";

    public static final String SAVE = "save";

    public static final String LOAD = "load";

    public static final String REVERSE = "reverse";

    public static final String NO_KEY_FOUND = "Err: no value for %s%n";
    public static final String COUNT_WORDS = "count-words";

    public static final String FILE_PATH = "src/files/";

    public static final String INCORRECT = "incorrect";

    public static final String EXPORTED_DATA = "Data exported to %s%n";

    public static final String WORDS_IN_TEXT = "Words in %s: %d%n";

    public static final String NOTHING = "";

    public static final String LOADED_DATA = "Data from %s is loaded%n";

    public static final String ACTIONS = "Enter action: get | set | save | load | reverse | count-words";

    public static final String VALID_ACTION = "Enter valid action: get | set | save | load | reverse | count-words";

    public static final String VALID_ACTION_DATA = "Please enter valid data and action: get | set | save | load | reverse | count-words!";
}
