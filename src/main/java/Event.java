import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * Encapsulates an Event task.
 *
 * @author K1mcheee
 */
public class Event extends Task{
    /** Event start date */
    private final String from;
    /** Event end date */
    private final String to;

    private final LocalDate fromDate;
    private final LocalDate toDate;

    private final LocalDateTime fromTime;
    private final LocalDateTime toTime;

    private boolean hasTime = false;


    /**
     * Constructor for Event task.
     * Calls parent constructor to set name.
     * Initialises from and to dates.
     *
     * @param name Description of Event task.
     * @param from When the event starts.
     * @param to When the event ends.
     */
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
        this.fromDate = null;
        this.toDate = null;
        this.fromTime = null;
        this.toTime = null;
    }

    public Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = null;
        this.to = null;
        this.fromDate = from;
        this.toDate = to;
        this.fromTime = null;
        this.toTime = null;
    }

    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = null;
        this.to = null;
        this.fromDate = null;
        this.toDate = null;
        this.fromTime = from;
        this.toTime = to;
    }

    public static Event handle(String input, int idx) {
        String name = "";
        String to = "";
        String from = "";

        String[] date = input.split("/");
        // retrieves command at 0 idx of array before dueDates
        String[] cmd = date[0].split(" ");

        Pattern dateOnly = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Pattern dateAndTime = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{4}");
        DateTimeFormatter dTFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        // retrieve task
        for (int i = idx; i < cmd.length; i++) {
            name += " " + cmd[i];
        }

        // retrieve fromDate
        String[] inputFrom = date[1].split(" ");
        // retrieve toDate
        String[] inputTo = date[2].split(" ");
        try {
            if (inputFrom.length == 2 && dateOnly.matcher(inputFrom[1]).matches()
                && inputTo.length == 2 && dateOnly.matcher(inputTo[1]).matches()) {

                return new Event(name, LocalDate.parse(inputFrom[1]), LocalDate.parse(inputTo[1]));

            } else if (inputFrom.length > 2 &&
                    dateAndTime.matcher(inputFrom[1] + " " + inputFrom[2]).matches()
                    && inputTo.length > 2 && dateAndTime.matcher(inputTo[1] + " " + inputTo[2]).matches()) {

                Event task = new Event(name, LocalDateTime.parse(inputFrom[1] + " " + inputFrom[2], dTFormat),
                        LocalDateTime.parse(inputTo[1] + " " + inputTo[2], dTFormat));
                task.hasTime = true;
                return task;

            } else {
                MochaException.invalidDateTime();
            }
        } catch (MochaException e) {
            System.out.println(e.getMessage());
        }

        for (int i = idx; i < inputFrom.length; i++) {
            from += " " + inputFrom[i];
        }

        for (int i = idx; i < inputTo.length; i++) {
            to += " " + inputTo[i];
        }

        return new Event(name, from, to);
    }

    @Override
    public boolean hasTime() {
        return this.hasTime;
    }

    @Override
    public String printFromDate() {
        if (this.from != null) {
            return from;
        } else if (this.fromDate != null) {
            return this.fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return this.fromTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        }
    }

    @Override
    public String printDueDate() {
        if (this.to != null) {
            return from;
        } else if (this.toDate != null) {
            return this.toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return this.toTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        }
    }

    @Override
    public String handleFromDate() {
        if (this.from != null) {
            return from;
        } else if (this.fromDate != null) {
            return this.fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            return this.fromTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }
    }

    @Override
    public String handleDueDate() {
        if (this.to != null) {
            return from;
        } else if (this.toDate != null) {
            return this.toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            return this.toTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }
    }

    @Override
    public String handle() {
        return String.format("event%s /from %s /to %s", super.getDescription(),
                this.handleFromDate(), this.handleDueDate());
    }

    /**
     * Add an indication to the task to
     * show that it is a Event task.
     *
     * @return string with task type and task name.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from:%s to:%s)", super.toString(),
                this.printFromDate(), this.printDueDate());
    }
}
