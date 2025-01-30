package mocha.command;

import mocha.TaskFile;
import mocha.TaskList;
import mocha.Ui;
import mocha.task.Event;
import mocha.task.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private String input;

    public EventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TaskFile storage) {
        try {
            // retrieve task, from and to date
            Task task = Event.handle(input, 1);
            storage.saveTask(input, false);
            tasks.add(task);
            ui.printNewTask(task, tasks.size());
        } catch (IOException e) {
            ui.printError("Could not save: " + e.getMessage());
            ui.br();
        } catch (DateTimeParseException e) {
            ui.printError("Invalid date: " + e.getMessage());
            ui.br();
        }
    }
}
