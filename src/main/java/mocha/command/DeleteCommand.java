package mocha.command;

import mocha.MochaException;

import mocha.TaskFile;
import mocha.TaskList;
import mocha.Ui;

import java.io.IOException;

/**
 * Encapsulates a Delete command.
 *
 * @author K1mcheee
 */
public class DeleteCommand extends Command {
    /**Indicates index of task*/
    private int idx;

    /**
     * Initializes Delete Command with index.
     *
     * @param idx index of task to be deleted.
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Runs the logic of the specific command.
     * For Delete, removes task at specified index
     * from the list.
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
            ui.delete(tasks.get(idx - 1));
            tasks.remove(idx - 1);
            storage.updateTask(tasks);
        } catch (MochaException e) {
            ui.printError(e.getMessage());
        } catch (IOException e) {
            ui.printError("Could not update: " + e.getMessage());
        }
        ui.printUpdates(tasks.size());
        ui.br();
    }
}
