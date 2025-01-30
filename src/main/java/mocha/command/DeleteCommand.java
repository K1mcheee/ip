package mocha.command;

import mocha.MochaException;
import mocha.TaskFile;
import mocha.TaskList;
import mocha.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

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
