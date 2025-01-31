package mocha.command;

import mocha.MochaException;

import mocha.TaskFile;
import mocha.TaskList;
import mocha.Ui;

import java.io.IOException;

/**
 * Encapsulates an Unmark command.
 *
 * @author K1mcheee
 */
public class UnmarkCommand extends Command {
    /**Index of task*/
    private int idx;

    /**
     * Initializes an Unmark Command.
     *
     * @param idx Index of task of interest.
     */
    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Runs the logic of the specific command.
     * For Unamrk, marks specific task as undone.
     *
     * @param tasks List of current tasks.
     * @param ui Interface for users to interact with.
     * @param storage Stores updates and changes to drive.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, TaskFile storage) {
        ui.br();
        try {
            if (idx < 1 || idx > tasks.size()) {
                throw new MochaException("Task does not exist in the list! List has " + tasks.size() + " items");
            }
            tasks.get(idx - 1).unmark();
            storage.updateTask(tasks);
        }  catch (MochaException e) {
            ui.printError(e.getMessage());

        } catch (IOException e) {
                ui.printError("Could not update: " + e.getMessage());
        }
        ui.br();
    }
}
