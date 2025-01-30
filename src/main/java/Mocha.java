import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Encapsulates the functionality of a chatbot
 *
 * @author K1mcheee
 */
public class Mocha {
    private static boolean isRunning = true;
    private static Task task = null;

    private TaskFile taskFile; //storage
    //private List<Task> commands = new ArrayList<>(); //tasklist


    private Ui ui;
    private TaskList taskList;

    public Mocha() {
        this.ui = new Ui();
        this.taskFile = new TaskFile("data/mocha.txt"); //storage
        try {
            this.taskList = new TaskList(this.taskFile.loadTask()); //tasklist
        } catch (FileNotFoundException e) {
            ui.printError("Error could not load file: " + e.getMessage());
        }


    }


    private LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }

    private LocalDate parseDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                .toLocalDate();
    }

    /**
     * Parse and validate the input entered by user and
     * responds accordingly. If user enters invalid input,
     * corresponding advice on the error is printed.
     *
     * @param input String entered by user.
     * @throws MochaException if user enters invalid commands.
     */
    private void validateInput(String input) throws MochaException {
        // parse input
        String[] split = input.split(" ");
        String[] date = input.split("/");
        String tmp = split[0].toLowerCase();

        // check for bye command to exit
        if (input.toLowerCase().equals("bye")) {
            isRunning = false;
            ui.goodBye();

        }  else if (input.toLowerCase().equals("due")) {
            ui.br();
            for (int i = 0; i < taskList.size(); i++) {
                // checks for tasks with LocalDate
                if (!task.hasTime() && !(task instanceof Todo)) {
                    // print task if due today
                    if (parseDate(task.handleDueDate()).equals(LocalDate.now())) {
                        ui.printTask(task);
                    }

                    // if event, check if date today is within from and to dates
                    if (task instanceof Event e) {
                        if (((parseDate(e.handleFromDate()).isBefore(LocalDate.now()))
                                || ((parseDate(e.handleFromDate()).equals(LocalDate.now()))))
                                && (parseDate(e.handleDueDate()).isAfter(LocalDate.now())
                                || ((parseDate(e.handleFromDate()).equals(LocalDate.now()))))) {
                            ui.printTask(e);
                        }
                    }
                }

                // handles tasks with time
                if (task.hasTime()) {
                    if (parseDateTime(task.handleDueDate()).equals((LocalDate.now()))) {
                        ui.printTask(task);
                    }

                    if (task instanceof Event e) {
                        if (parseDateTime(e.handleFromDate()).isBefore((LocalDate.now()))
                                || parseDateTime(e.handleFromDate()).equals((LocalDate.now()))
                                && (parseDateTime(e.handleDueDate()).isAfter((LocalDate.now()))
                                || parseDateTime(e.handleDueDate()).equals((LocalDate.now())))) {
                            ui.printTask(e);
                        }
                    }
                }

            }
            ui.br();
        }   else if (input.toLowerCase().equals("list")) {
            // check for command to print list
            ui.br();
            ui.printTaskList(this.taskList);
            ui.br();
        } else {
            // check for keywords mark and unmark
            if (tmp.equals("mark") || tmp.equals("unmark") || tmp.equals("delete")) {
                int idx = Integer.parseInt(split[1]);

                if (idx < 1 || idx > taskList.size()) {
                    throw new MochaException("Task does not exist in the list! List has " + taskList.size() + " items");
                }

                if (tmp.equals("mark")) {
                    ui.br();
                    taskList.get(idx - 1).mark();

                    try {
                        this.taskFile.updateTask(taskList);
                    } catch (IOException e) {
                        ui.printError("Could not update: " + e.getMessage());
                    }
                    ui.br();
                }
                if (tmp.equals("unmark")) {
                    ui.br();
                    taskList.get(idx - 1).unmark();

                    try {
                        this.taskFile.updateTask(taskList);
                    } catch (IOException e) {
                        ui.printError("Could not update: " + e.getMessage());
                    }
                    ui.br();
                }
                if (tmp.equals("delete")) {
                    ui.br();
                    ui.delete(taskList.get(idx - 1));
                    taskList.remove(idx - 1);

                    try {
                        this.taskFile.updateTask(taskList);
                    } catch (IOException e) {
                        ui.printError("Could not update: " + e.getMessage());
                    }

                    ui.printUpdates(this.taskList.size());
                    ui.br();

                }
            } else {

                // switch case to respond to input
                switch (tmp) {
                    case "todo":
                        if (split.length < 2) {
                            MochaException.emptyDescription("todo have 5 cups of bubble tea");
                        }
                        try {
                            // retrieve task
                            task = Todo.handle(input, 1);
                            this.taskFile.saveTask(input, false);
                            taskList.add(task);
                            ui.printNewTask(task, taskList.size());
                        } catch (IOException e) {
                            ui.printError("Could not save: " + e.getMessage());
                        }  catch (DateTimeParseException e) {
                            // do nothing
                        }
                        break;
                    case "deadline":
                        if (split.length < 2) {
                            MochaException.emptyDescription("deadline finish the bubble tea /by today");
                        }
                        if (date.length < 2) {
                            throw new MochaException("Remember to add a due date using:\n /by duedate");
                        }
                        try {
                            // retrieve task and deadline
                            task = Deadline.handle(input, 1);
                            this.taskFile.saveTask(input, false);
                            taskList.add(task);
                            ui.printNewTask(task, taskList.size());

                        } catch (IOException e) {
                            ui.printError("Could not save: " + e.getMessage());
                            ui.br();
                        } catch (DateTimeParseException e) {
                            ui.printError("Invalid date: " + e.getMessage());
                            ui.br();
                        }
                        break;

                    case "event":
                        if (split.length < 2) {
                            MochaException.emptyDescription("event Try all bubble teas /from Monday /to Friday");
                        }
                        if (date.length < 3) {
                            throw new MochaException("events require a from and to date!\n /from fromDate /to toDate");
                        }
                        try {
                            // retrieve task, from and to date
                            task = Event.handle(input, 1);
                            this.taskFile.saveTask(input, false);
                            taskList.add(task);
                            ui.printNewTask(task, taskList.size());
                        } catch (IOException e) {
                            ui.printError("Could not save: " + e.getMessage());
                            ui.br();
                        } catch (DateTimeParseException e) {
                            ui.printError("Invalid date: " + e.getMessage());
                            ui.br();
                        }
                        break;

                    default:
                        throw new MochaException("Searching through tasks... \n" +
                                "sorry, it seems I have not learnt this command!");
                }
            }

        }

    }

    public void run() {
        ui.welcome();
        Scanner scanner = new Scanner(System.in); //ui

        while (isRunning) {
            String input = scanner.nextLine(); //parser

            try {
                Parser.validateInput(input); //parser
            } catch (MochaException e) {
                this.ui.printError(e.getMessage());
            }

        }
    }

    public static void main(String[] args) {

            new Mocha().run();

    }
}
