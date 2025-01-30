import java.time.LocalDate;

public class DueCommand extends Command {

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
