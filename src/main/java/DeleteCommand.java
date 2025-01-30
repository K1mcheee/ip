import java.io.IOException;

public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TaskFile storage) {
        ui.br();
        ui.delete(tasks.get(idx - 1));
        tasks.remove(idx - 1);

        try {
            storage.updateTask(tasks);
        } catch (IOException e) {
            ui.printError("Could not update: " + e.getMessage());
        }

        ui.printUpdates(tasks.size());
        ui.br();
    }
}
