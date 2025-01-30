import java.io.IOException;

public class MarkCommand extends Command {
    private int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }
    @Override
    public void execute(TaskList task, Ui ui, TaskFile storage) {
        ui.br();
        task.get(idx - 1).mark();

        try {
            storage.updateTask(task);
        } catch (IOException e) {
            ui.printError("Could not update: " + e.getMessage());
        }
        ui.br();
    }
}
