public class ByeCommand extends Command {
    @Override
    public void execute(TaskList task, Ui ui, TaskFile storage) {
        goodbye();
        ui.goodBye();
    }
}
