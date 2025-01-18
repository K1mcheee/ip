/**
 * Encapsulates a Deadline task.
 *
 * @author K1mcheee
 */
public class Deadline extends Task{
    /** due date of the task*/
    private String dueDate;

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
    }

    /**
     * Add an indication to the task to
     * show that it is a Deadline task.
     *
     * @return string with task type and task name.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by:%s)" , super.toString(), this.dueDate);
    }
}
