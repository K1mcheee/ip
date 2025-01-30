package mocha.command;

import mocha.Parser;
import mocha.TaskFile;
import mocha.TaskList;
import mocha.Ui;
import mocha.task.Event;
import mocha.task.Todo;

import java.time.LocalDate;

/**
 * Encapsulates a Due command.
 *
 * @author K1mcheee
 */
public class DueCommand extends Command {

    /**
     * Runs the logic of the specific command.
     * For Due, prints out from the list
     * of existing tasks those which current date
     * falls within its range.
     *
     * @param tasks List of current tasks.
     * @param ui Interface for users to interact with.
     * @param storage Stores updates and changes to drive.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, TaskFile storage) {
        ui.br();
        for (int i = 0; i < tasks.size(); i++) {
            // checks for tasks with LocalDate
            if (!tasks.get(i).hasTime() && !(tasks.get(i) instanceof Todo)) {
                // print task if due today
                if (Parser.parseDate(tasks.get(i).handleDueDate()).equals(LocalDate.now())) {
                    ui.printTask(tasks.get(i));
                }

                // if event, check if date today is within from and to dates
                if (tasks.get(i) instanceof Event e) {
                    if (((Parser.parseDate(e.handleFromDate()).isBefore(LocalDate.now()))
                            || ((Parser.parseDate(e.handleFromDate()).equals(LocalDate.now()))))
                            && (Parser.parseDate(e.handleDueDate()).isAfter(LocalDate.now())
                            || ((Parser.parseDate(e.handleFromDate()).equals(LocalDate.now()))))) {
                        ui.printTask(e);
                    }
                }
            }

            // handles tasks with time
            if (tasks.get(i).hasTime()) {
                if (Parser.parseDateTime(tasks.get(i).handleDueDate()).equals((LocalDate.now()))) {
                    ui.printTask(tasks.get(i));
                }

                if (tasks.get(i) instanceof Event e) {
                    if (Parser.parseDateTime(e.handleFromDate()).isBefore((LocalDate.now()))
                            || Parser.parseDateTime(e.handleFromDate()).equals((LocalDate.now()))
                            && (Parser.parseDateTime(e.handleDueDate()).isAfter((LocalDate.now()))
                            || Parser.parseDateTime(e.handleDueDate()).equals((LocalDate.now())))) {
                        ui.printTask(e);
                    }
                }
            }
        }
        ui.br();
    }
}
