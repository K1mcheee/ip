package mocha.command;

import mocha.TaskFile;
import mocha.TaskList;
import mocha.Ui;

public abstract class Command {
    private boolean isRunning = true;

    public abstract void execute(TaskList tasks, Ui ui, TaskFile storage);

    public boolean isRunning() {
        return isRunning;
    }

    protected void goodbye() {
        isRunning = false;
    }
}
