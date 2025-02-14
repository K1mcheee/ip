package mocha.command;

import mocha.TaskFile;
import mocha.TaskList;
import mocha.Ui;

/**
 * Encapsulates a List command.
 *
 * @author K1mcheee
 */
public class ListCommand extends Command {

    /**
     * Runs the logic of the specific command.
     * For List, lists the current tasks.
     *
     * @param tasks List of current tasks.
     * @param ui Interface for users to interact with.
     * @param storage Stores updates and changes to drive.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, TaskFile storage) {
        ui.br();
        assert(tasks != null);
        ui.printTaskList(tasks);
        ui.br();
    }
}
