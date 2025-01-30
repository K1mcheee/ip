import java.io.IOException;

public class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TaskFile storage) {
        ui.br();
        try {
            if (idx < 1 || idx > tasks.size()) {
                throw new MochaException("Task does not exist in the list! List has " + tasks.size() + " items");
            }
            tasks.get(idx - 1).unmark();
            storage.updateTask(tasks);
        }  catch (MochaException e) {
            ui.printError(e.getMessage());

        } catch (IOException e) {
                ui.printError("Could not update: " + e.getMessage());
        }
        ui.br();
    }
}
