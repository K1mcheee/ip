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
    
    public static Deadline handle(String input, int idx) {
        String name = "";
        String dueDate = "";

        String[] split = input.split(" ");
        String[] date = input.split("/");
        // retrieves command at 0 idx of array before dueDates
        String[] cmd = date[0].split(" ");

        // retrieve task
        for (int i = idx; i < cmd.length; i++) {
            name += " " + cmd[i];
        }

        // retrieve deadline
        String[] byWhen = date[1].split(" ");
        for (int i = idx; i < byWhen.length; i++) {
            dueDate += " " + byWhen[i];
        }

        return new Deadline(name, dueDate);

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
