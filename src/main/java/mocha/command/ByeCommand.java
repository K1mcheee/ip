package mocha.command;

import mocha.TaskFile;
import mocha.TaskList;
import mocha.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, TaskFile storage) {
        goodbye();
        ui.goodBye();
    }
}
