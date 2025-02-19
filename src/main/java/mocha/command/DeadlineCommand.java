package mocha.command;

import mocha.MochaException;

import mocha.TaskFile;
import mocha.TaskList;
import mocha.Ui;

import mocha.task.Deadline;
import mocha.task.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a deadline command.
 *
 * @author K1mcheee
 */
public class DeadlineCommand extends Command {
    /**String entered by user*/
    private String input;

    /**
     * Constructor to initialise a
     * Deadline command.
     *
     * @param input String entered by user.
     */
    public DeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Runs the logic of the specific command.
     * For Deadline, creates a new Deadline Task,
     * and adds to list of current task.
     *
     * @param tasks List of current tasks.
     * @param ui Interface for users to interact with.
     * @param storage Stores updates and changes to drive.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, TaskFile storage) {
        try {
            // retrieve task and deadline
            Task task = Deadline.handle(input, 1);
            storage.saveTask(input, false);
            tasks.add(task);
            ui.printNewTask(task, tasks.size());

        } catch (IOException e) {
            ui.printError("Could not save: " + e.getMessage());
        } catch (DateTimeParseException e) {
            ui.printError("Invalid date: " + e.getMessage());
        } catch (MochaException e) {
            ui.printError(e.getMessage());
        }
    }
}
