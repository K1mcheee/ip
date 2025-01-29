/**
 * Encapsulates an Event task.
 *
 * @author K1mcheee
 */
public class Event extends Task{
    /** Event start date */
    private String from;
    /** Event end date */
    private String to;

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
    }

    public static Event handle(String input, int idx) {
        String name = "";
        String dueDate = "";
        String to = "";
        String from = "";

        String[] split = input.split(" ");
        String[] date = input.split("/");
        // retrieves command at 0 idx of array before dueDates
        String[] cmd = date[0].split(" ");

        // retrieve task
        for (int i = idx; i < cmd.length; i++) {
            name += " " + cmd[i];
        }

        // retrieve fromDate
        String[] fromDate = date[1].split(" ");
        for (int i = idx; i < fromDate.length; i++) {
            from += " " + fromDate[i];
        }

        // retrieve toDate
        String[] toDate = date[2].split(" ");
        for (int i = idx; i < toDate.length; i++) {
            to += " " + toDate[i];
        }

        return new Event(name, from, to);
    }

    @Override
    public String handle() {
        return String.format("event%s /from %s /to %s", super.getDescription(), this.from, this.to);
    }

    /**
     * Add an indication to the task to
     * show that it is a Event task.
     *
     * @return string with task type and task name.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from:%s to:%s)", super.toString(), this.from, this.to);
    }
}
