package duke;

/**
 * Represents exceptions unique to Duke
 */
public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    public DukeException() {
        super();
    }

}
