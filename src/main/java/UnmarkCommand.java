import java.io.IOException;

public class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList task, Ui ui, TaskFile storage) {
        ui.br();
        task.get(idx - 1).unmark();

        try {
            storage.updateTask(task);
        } catch (IOException e) {
            ui.printError("Could not update: " + e.getMessage());
        }
        ui.br();
    }
}
