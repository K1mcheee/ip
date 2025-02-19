package mocha.command;

import mocha.MochaException;
import mocha.TaskFile;
import mocha.TaskList;
import mocha.Ui;

import java.io.IOException;

public class TagCommand extends Command{
    private String tag;
    private int idx;

    public TagCommand(String tag, int idx) {
        this.tag = tag;
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TaskFile storage) {
        try {
            if (idx < 1 || idx > tasks.size()) {
                throw new MochaException("Task does not exist in the list! List has " + tasks.size() + " items");
            }
            tasks.get(idx - 1).tag(this.tag);
            storage.updateTask(tasks);
        } catch (MochaException e) {
            ui.printError(e.getMessage());
        } catch (IOException e) {
            ui.printError("Could not update: " + e.getMessage());
        }
    }
}
