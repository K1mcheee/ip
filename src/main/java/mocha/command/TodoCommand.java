package mocha.command;

import mocha.TaskFile;
import mocha.TaskList;
import mocha.Ui;
import mocha.task.Task;
import mocha.task.Todo;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a Todo command.
 *
 * @author K1mcheee
 */
public class TodoCommand extends Command {
    /**String input from user*/
    private String input;

    /**
     * Constructor to initialise a Todo coommand.
     *
     * @param input String input from user.
     */
    public TodoCommand(String input) {
        this.input = input;
    }

    /**
     * Runs the logic of the specific command.
     * For Todo, creates a new Todo task with
     * description specified by user.
     *
     * @param tasks List of current tasks.
     * @param ui Interface for users to interact with.
     * @param storage Stores updates and changes to drive.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, TaskFile storage) {

        try {
            // retrieve task
            Task task = Todo.handle(this.input, 1);
            storage.saveTask(input, false);
            tasks.add(task);
            ui.printNewTask(task, tasks.size());
        } catch (IOException e) {
            ui.printError("Could not save: " + e.getMessage());
        }  catch (DateTimeParseException e) {
            // do nothing
        }
    }
}
