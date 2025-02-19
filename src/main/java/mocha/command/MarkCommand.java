package mocha.command;

import mocha.MochaException;

import mocha.TaskFile;
import mocha.TaskList;
import mocha.Ui;

import java.io.IOException;

/**
 * Encapsulates a Mark command.
 *
 * @author K1mcheee
 */
public class MarkCommand extends Command {
    /**Index of task*/
    private int idx;

    /**
     * Constructor to initialise a Mark command.
     *
     * @param idx Index of task of interest.
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Runs the logic of the specific command.
     * For Mark, marks task as done.
     *
     * @param tasks List of current tasks.
     * @param ui Interface for users to interact with.
     * @param storage Stores updates and changes to drive.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, TaskFile storage) {

        try {
            if (idx < 1 || idx > tasks.size()) {
                throw new MochaException("Task does not exist in the list! List has " + tasks.size() + " items");
            }
            tasks.get(idx - 1).mark();
            storage.updateTask(tasks);
        } catch (MochaException e) {
            ui.printError(e.getMessage());
        } catch (IOException e) {
            ui.printError("Could not update: " + e.getMessage());
        }
    }
}
