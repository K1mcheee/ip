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
