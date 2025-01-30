package mocha.command;

import mocha.TaskFile;
import mocha.TaskList;
import mocha.Ui;

/**
 * Encapsulates a goodbye command.
 *
 * @author K1mcheee
 */
public class ByeCommand extends Command {

    /**
     * Runs the logic of the specific command.
     * For Bye command, terminates program.
     *
     * @param tasks List of current tasks.
     * @param ui Interface for users to interact with.
     * @param storage Stores updates and changes to drive.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, TaskFile storage) {
        goodbye();
        ui.goodBye();
    }
}
