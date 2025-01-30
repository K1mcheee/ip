package mocha.command;

import mocha.MochaException;
import mocha.TaskFile;
import mocha.TaskList;
import mocha.Ui;
import mocha.task.Deadline;
import mocha.task.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TaskFile storage) {
        try {
            // retrieve task and deadline
            Task task = Deadline.handle(input, 1);
            storage.saveTask(input, false);
            tasks.add(task);
            ui.printNewTask(task, tasks.size());

        } catch (IOException e) {
            ui.printError("Could not save: " + e.getMessage());
            ui.br();
        } catch (DateTimeParseException e) {
            ui.printError("Invalid date: " + e.getMessage());
            ui.br();
        } catch (MochaException e) {
            ui.printError(e.getMessage());
        }
    }
}
