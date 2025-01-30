import java.io.IOException;
import java.time.format.DateTimeParseException;

public class TodoCommand extends Command{
    private String input;

    public TodoCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TaskFile storage) {

        try {
            // retrieve task
            Task task = Todo.handle(this.input, 1);
            storage.saveTask(input, false);
            tasks.add(task);
            ui.printNewTask(task, tasks.size());
        } catch (IOException e) {
            ui.printError("Could not save: " + e.getMessage());
        }  catch (DateTimeParseException e) {
            // do nothing
        }
    }
}
