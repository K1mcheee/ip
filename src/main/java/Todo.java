/**
 * Encapsulates a Todo task.
 *
 * @author K1mcheee
 */

public class Todo extends Task{

    /**
     * Constructor for a Todo task.
     * Calls parent constructor to initialise name.
     *
     * @param name Description of todo task
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns description of Todo tasks
     *
     * @param input entire String from user
     * @return formatted Task description
     */
    public static String handle(String input) {
        String name = ""; // initialise to unmarked
        String[] split = input.split(" ");

        // retrieve task
        for (int i = 1; i < split.length; i++) {
            name += " " + split[i];
        }

        return name;
    }

    /**
     * Add an indication to the task to
     * show that it is a Todo task.
     *
     * @return string with task type and task name.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
