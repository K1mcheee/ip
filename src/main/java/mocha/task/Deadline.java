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
    /**
     * Constructor for deadline class.
     * Calls parent constructor to set name.
     * initialises the due date.
     *
     * @param name Description of Deadline task.
     * @param dueDate When the task is due.
     */
    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
        this.deadline = null;
        this.deadlineTime = null;
    }

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

    public static Deadline handle(String input, int idx) {
        String name = "";
        String dueDate = "";

        String[] date = input.split("/");

        // retrieves command and description at 0 idx of array before dueDates
        String[] cmd = date[0].split(" ");

        Pattern dateOnly = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Pattern dateAndTime = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{4}");
        DateTimeFormatter dTFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        // retrieve task
        for (int i = idx; i < cmd.length; i++) {
            name += " " + cmd[i];
        }

        // retrieve deadline
        String[] inputDate = date[1].split(" ");

        try {
            if (inputDate.length == 2 && dateOnly.matcher(inputDate[1]).matches()) {
                return new Deadline(name, LocalDate.parse(inputDate[1]));
            } else if (inputDate.length > 2 &&
                    dateAndTime.matcher(inputDate[1] + " " + inputDate[2]).matches()) {
                Deadline task = new Deadline(name,
                        LocalDateTime.parse(inputDate[1] + " " + inputDate[2], dTFormat));
                task.hasTime = true;
                return task;
            } else {
                MochaException.invalidDateTime();
            }
        } catch (MochaException e) {
            System.out.println(e.getMessage());
        }

        String[] byWhen = date[1].split(" ");
        for (int i = idx; i < byWhen.length; i++) {
            dueDate += " " + byWhen[i];
        }
        return new Deadline(name, dueDate);

    }

    @Override
    public boolean hasTime() {
        return this.hasTime;
    }

    @Override
    public String printDueDate() {
        if (this.dueDate != null) {
            return dueDate;
        } else if (this.deadline != null) {
            return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return this.deadlineTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        }

    }

    @Override
    public String handleDueDate() {
        if (this.dueDate != null) {
            return dueDate;
        } else if (this.deadline != null) {
            return this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            return this.deadlineTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

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
