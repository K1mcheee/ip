package mocha.command;

import mocha.TaskFile;
import mocha.TaskList;
import mocha.Ui;

import mocha.task.Event;
import mocha.task.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates an Event command.
 *
 * @author K1mcheee
 */
public class EventCommand extends Command {
    /**String entered by user*/
    private String input;

    /**
     * Constructor to initialise an Event command.
     *
     * @param input String entered by user.
     */
    public EventCommand(String input) {
        this.input = input;
    }

    /**
     * Runs the logic of the specific command.
     * For Event, creates a new Event with
     * to and from dates specified by user.
     *
     * @param tasks List of current tasks.
     * @param ui Interface for users to interact with.
     * @param storage Stores updates and changes to drive.
     */
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
