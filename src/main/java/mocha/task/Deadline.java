package mocha.task;

import mocha.MochaException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.regex.Pattern;

/**
 * Encapsulates a Deadline task.
 *
 * @author K1mcheee
 */
public class Deadline extends Task {
    /** due date of the task*/
    private final String dueDate;
    private final LocalDate deadline;
    private final LocalDateTime deadlineTime;
    private boolean hasTime = false;

    private static final Pattern DATE_ONLY = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    private static final Pattern DATE_AND_TIME = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{4}");
    private static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    /**
     * Constructor for deadline class.
     * Calls parent constructor to set name.
     * initialises the due date.
     *
     * @param name Description of Deadline task.
     * @param deadline When the task is due.
     */
    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.dueDate = null;
        this.deadline = deadline;
        this.deadlineTime = null;
    }

    public Deadline(String name, LocalDateTime deadlineTime) {
        super(name);
        this.dueDate = null;
        this.deadline = null;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Parses user input for easier processing.
     *
     * @param input String input by user.
     * @param idx index the tasks input starts.
     * @return Formatted string for further parsing
     * @throws MochaException When date format is incorrect.
     */
    public static Deadline handle(String input, int idx) throws MochaException {
        String name = "";

        String[] date = input.split("/");
        // retrieves command and description at 0 idx of array before dueDates
        String[] cmd = date[0].split(" ");

        // retrieve task
        for (int i = idx; i < cmd.length; i++) {
            name += " " + cmd[i];
        }
        // retrieve deadline
        String[] inputDate = date[1].split(" ");
        return processDeadlineTask(name, inputDate);
    }

    private static Deadline processDeadlineTask(String name, String[] inputDate) throws MochaException {
        if (inputDate.length == 2 && DATE_ONLY.matcher(inputDate[1]).matches()) {
            return new Deadline(name, LocalDate.parse(inputDate[1]));
        }
        if (inputDate.length > 2 &&
                DATE_AND_TIME.matcher(inputDate[1] + " " + inputDate[2]).matches()) {
            Deadline task = new Deadline(name,
                    LocalDateTime.parse(inputDate[1] + " " + inputDate[2], DT_FORMAT));
            task.hasTime = true;
            return task;
        }
        throw new MochaException("Invalid date/time! Input as yyyy-mm-dd for date and HHmm for time!");
    }

    @Override
    public boolean hasTime() {
        return this.hasTime;
    }

    @Override
    public String printDueDate() {
        if (this.dueDate != null) {
            return dueDate;
        }
        if (this.deadline != null) {
            return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return this.deadlineTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    @Override
    public String handleDueDate() {
        if (this.dueDate != null) {
            return dueDate;
        }
        if (this.deadline != null) {
            return this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return this.deadlineTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

    }

    @Override
    public String handle() {
        return String.format("deadline%s /by %s", super.getDescription(), this.handleDueDate());
    }

    /**
     * Add an indication to the task to
     * show that it is a Deadline task.
     *
     * @return string with task type and task name.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by:%s)" , super.toString(), this.printDueDate());
    }
}
