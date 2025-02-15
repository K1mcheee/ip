package mocha.command;

import mocha.TaskFile;
import mocha.TaskList;
import mocha.Ui;

import mocha.task.Task;

/**
 * Encapsulates a Find command.
 *
 * @author K1mcheee
 */
public class FindCommand extends Command {
    /**keyword to search in list*/
    private String keyword;

    /**
     * Initializes a Find command.
     *
     * @param keyword Keyword to search.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Runs the logic of the specified command.
     * For Find, prints the list of tasks that match
     * the keyword specified.
     *
     * @param tasks List of current tasks.
     * @param ui Interface for users to interact with.
     * @param storage Stores updates and changes to drive.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, TaskFile storage) {
        ui.br();
        System.out.println("Here are the matching tasks in your list:");
        
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            if (task.getName().contains(keyword) || task.getTag().contains(keyword)) {

                ui.printTask(task);
            }
        }
        ui.br();
    }
}
