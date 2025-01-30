import java.util.ArrayList;
import java.util.List;

public class TaskList {
    /**List of tasks*/
    private List<Task> commands; //tasklist

    public TaskList(ArrayList<Task> commands) {
        this.commands = commands;
    }
    /**
     * Constructor to initialize the list of tasks.
     *
     * @param commands List of tasks loaded from storage.
     */
    public TaskList(List<Task> commands) {
        this.commands = commands;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return size of List.
     */
    public int size() {
        return commands.size();
    }

    /**
     * Returns the Task at specified index.
     *
     * @param index of item in array.
     * @return Task at array index.
     */
    public Task get(int index) {
        return commands.get(index);
    }

    /**
     * Removes the task from specified index of array.
     *
     * @param index of item in array.
     */
    public void remove(int index) {
        commands.remove(index);
    }

    /**
     * Adds a task to the array.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        commands.add(task);
    }


}
