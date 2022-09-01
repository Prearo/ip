package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Representing a deadline, i.e. a task with a specified due date
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDateTime localDateTime;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.localDateTime = LocalDateTime.parse(by);
        } catch (DateTimeParseException ignored) {
            // Ignore usage of localDateTime if input is not formatted as one
        }
        this.by = by;
    }

    public String getTime() {
        return by;
    }

    /**
     * Generates an encoding of the Task for use in storage
     * @return encoded string following the storage format
     */
    public String getStorageString() {
        return "D" + "|" + (isDone ? "1" : "0") + "|" + getDescription() + "|" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + (localDateTime == null ? by
                    : localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")))
                + ")";
    }
}