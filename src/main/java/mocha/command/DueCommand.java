package mocha.command;

import mocha.Parser;
import mocha.TaskFile;
import mocha.TaskList;
import mocha.Ui;

import mocha.task.Event;
import mocha.task.Task;
import mocha.task.Todo;

import java.time.LocalDate;

/**
 * Encapsulates a Due command.
 *
 * @author K1mcheee
 */
public class DueCommand extends Command {


    private void printLocalDateEvent(Event e, Ui ui) {
        boolean isBefore = Parser.parseDate(e.handleFromDate()).isBefore(LocalDate.now());
        boolean isAfter = Parser.parseDate(e.handleFromDate()).isAfter(LocalDate.now());
        boolean isCurrent = Parser.parseDate(e.handleFromDate()).equals(LocalDate.now());

        // checks if date today is within from and to dates
        if ((isBefore || isCurrent) && (isAfter || isCurrent)) {
            ui.printTask(e);
        }
    }

    private void printLocalDateTimeEvent(Event e, Ui ui) {
        boolean isBefore = Parser.parseDateTime(e.handleFromDate()).isBefore(LocalDate.now());
        boolean isAfter = Parser.parseDateTime(e.handleFromDate()).isAfter(LocalDate.now());
        boolean isCurrent = Parser.parseDateTime(e.handleFromDate()).equals(LocalDate.now());

        // checks if date today is within from and to dates
        if ((isBefore || isCurrent) && (isAfter || isCurrent)) {
            ui.printTask(e);
        }
    }

    private void printLocalDateTask(Task task, Ui ui) {
        // if event, check if date today is within from and to dates
        if (task instanceof Event e) {
            printLocalDateEvent(e, ui);
        }
        // print other tasks if due today
        if (Parser.parseDate(task.handleDueDate()).equals(LocalDate.now())) {
            ui.printTask(task);
        }

    }

    private void printLocalDateTimeTask(Task task, Ui ui) {
        if (task instanceof Event e) {
            printLocalDateTimeEvent(e, ui);
        }

        if (Parser.parseDateTime(task.handleDueDate()).equals((LocalDate.now()))) {
            ui.printTask(task);
        }
    }
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
                printLocalDateTask(tasks.get(i), ui);
            }

            // handles tasks with time
            if (tasks.get(i).hasTime()) {
                // print task if due today
                printLocalDateTimeTask(tasks.get(i), ui);

            }
        }
        ui.br();
    }
}
