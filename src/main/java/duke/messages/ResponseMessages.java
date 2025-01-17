package duke.messages;

/**
 * Contains the chatbot's responses and messages after handling commands.
 */
public final class ResponseMessages {

    private static final String lineSep = System.lineSeparator();

    public static String STARTUP = "Hello! I'm dukebot, what can I do for you?" + lineSep;
    public static String ENDING = "Bye. Hope to see you again soon!";
    public static String LIST_TASKS = "Here are the tasks in your list:" + lineSep;
    public static String TASK_MARKED = "Nice! I've marked this task as done:" + lineSep;
    public static String TASK_UNMARKED = "OK, I've marked this task as not done yet:" + lineSep;
    public static String TASK_ADDED = "Got it. I've added this task:" + lineSep;
    public static String TASK_COUNT = "Now you have %d task(s) in the list.\n";
    public static String TASKS_REMOVED = "Noted. I've removed these task(s):" + lineSep;
    public static String FIND_TASKS = "Here are tasks matching '%s' in your list:" + lineSep;

}
