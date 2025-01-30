package mocha.command;

import mocha.TaskFile;
import mocha.TaskList;
import mocha.Ui;

/**
 * Abstract class that encapsulates Commands.
 *
 * @author K1mcheee
 */
public abstract class Command {
    /**Tracks if program is running*/
    private boolean isRunning = true;

    /**
     * Runs the logic of the specific command.
     *
     * @param tasks List of current tasks.
     * @param ui Interface for users to interact with.
     * @param storage Stores updates and changes to drive.
     */
    public abstract void execute(TaskList tasks, Ui ui, TaskFile storage);

    /**
     * Returns state of program.
     *
     * @return boolean whether the program is running or not.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Allows child class to terminate program.
     */
    protected void goodbye() {
        isRunning = false;
    }
}
