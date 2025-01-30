import java.io.IOException;

public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList task, Ui ui, TaskFile storage) {
        ui.br();
        ui.delete(task.get(idx - 1));
        task.remove(idx - 1);

        try {
            storage.updateTask(task);
        } catch (IOException e) {
            ui.printError("Could not update: " + e.getMessage());
        }

        ui.printUpdates(task.size());
        ui.br();
    }
}
