public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, TaskFile storage) {
        ui.br();
        ui.printTaskList(tasks);
        ui.br();
    }
}
