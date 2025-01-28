import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Encapsulates the functionality of a chatbot
 *
 * @author K1mcheee
 */
public class Mocha {
    private static final String BR = "____________________________________________________________";
    private static boolean isRunning = true;
    //private static List<Task> commands = new ArrayList<>();
    private static Task task = null;

    private TaskFile taskFile;
    private List<Task> commands = new ArrayList<>();
    public Mocha() {

        this.taskFile = new TaskFile("data/mocha.txt");
        try {
            this.commands = this.taskFile.loadTask();
        } catch (FileNotFoundException e) {
            System.out.println("Error could not load file: " + e.getMessage());
        }


    }

    /**
     * Helper function to print when task is added
     * @return Standard reply that task is updated
     */
    private static String printNew() {
        return "\n Got it. I've added this task: \n";
    }

    /**
     * Helper function to print whenever list is updated
     * @return  number of taasks in list
     */
    private String printUpdates() {
        return String.format(" Now you have %d tasks in the list. \n", this.commands.size());
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
            try {
                this.taskFile.saveTask(this.commands);
            } catch (IOException e) {
                System.out.println("Sorry, I could not save the task!");
            }
            isRunning = false;
            System.out.println(BR + "\n Bye. Hope to see you again soon! \n" + BR);

        } else if (input.toLowerCase().equals("list")) {
            // check for command to print list
            System.out.println(BR);
            for (int i = 1; i <= this.commands.size(); i++) {
                System.out.println(i + "." + this.commands.get(i - 1));
            }
            System.out.println(BR);
        } else {
            // check for keywords mark and unmark
            if (tmp.equals("mark") || tmp.equals("unmark") || tmp.equals("delete")) {
                int idx = Integer.parseInt(split[1]);

                if (idx < 1 || idx > commands.size()) {
                    throw new MochaException("Task does not exist in the list! List has " + commands.size() + " items");
                }

                if (tmp.equals("mark")) {
                    System.out.println(BR);
                    commands.get(idx - 1).mark();
                    System.out.println(BR);
                }
                if (tmp.equals("unmark")) {
                    System.out.println(BR);
                    commands.get(idx - 1).unmark();
                    System.out.println(BR);
                }
                if (tmp.equals("delete")) {
                    System.out.println(BR + "\n Alright, I have removed this task:");
                    System.out.println(commands.get(idx - 1));
                    commands.remove(idx - 1);
                    System.out.println(printUpdates() + BR);

                }
            } else {
                // templates for string printing
                String name = "";
                String dueDate = "";
                String to = "";
                String from = "";

                // retrieves command at 0 idx of array before dueDates
                String[] cmd = date[0].split(" ");

                // switch case to respond to input
                switch (tmp) {
                    case "todo":
                        if (split.length < 2) {
                            MochaException.emptyDescription("todo have 5 cups of bubble tea");
                        }
                        // retrieve task
                        for (int i = 1; i < split.length; i++) {
                            name += " " + split[i];
                        }
                        task = new Todo(name);
                        commands.add(task);
                        System.out.println(BR + printNew() + task + "\n" + printUpdates() + BR);
                        break;
                    case "deadline":
                        if (split.length < 2) {
                            MochaException.emptyDescription("deadline finish the bubble tea /by today");
                        }
                        if (date.length < 2) {
                            throw new MochaException("Remember to add a due date using:\n /by duedate");
                        }
                        // retrieve task
                        for (int i = 1; i < cmd.length; i++) {
                            name += " " + cmd[i];
                        }

                        // retrieve deadline
                        String[] byWhen = date[1].split(" ");
                        for (int i = 1; i < byWhen.length; i++) {
                            dueDate += " " + byWhen[i];
                        }

                        task = new Deadline(name, dueDate);
                        commands.add(task);
                        System.out.println(BR + printNew() + task + "\n" + printUpdates() + BR);
                        break;

                    case "event":
                        if (split.length < 2) {
                            MochaException.emptyDescription("event Try all bubble teas /from Monday /to Friday");
                        }
                        if (date.length < 3) {
                            throw new MochaException("events require a from and to date!\n /from fromDate /to toDate");
                        }
                        // retrieve task
                        for (int i = 1; i < cmd.length; i++) {
                            name += " " + cmd[i];
                        }

                        // retrieve fromDate
                        String[] fromDate = date[1].split(" ");
                        for (int i = 1; i < fromDate.length; i++) {
                            from += " " + fromDate[i];
                        }

                        // retrieve toDate
                        String[] toDate = date[2].split(" ");
                        for (int i = 1; i < toDate.length; i++) {
                            to += " " + toDate[i];
                        }

                        task = new Event(name, from, to);
                        commands.add(task);
                        System.out.println(BR + printNew() + task + "\n" + printUpdates() + BR);
                        break;

                    default:
                        throw new MochaException("Searching through tasks... \n" +
                                "sorry, it seems I have not learnt this command!");
                }
            }

        }

    }

    public static void main(String[] args) {


            Mocha mocha = new Mocha();
            System.out.println(BR + "\n Hello! I'm Mocha");
            System.out.println(" What can I do for you? \n" + BR);

            Scanner scanner = new Scanner(System.in);

            while (isRunning) {
                String input = scanner.nextLine();

                try {
                    mocha.validateInput(input);
                } catch (MochaException e) {
                    System.out.println(BR);
                    System.out.println(e.getMessage());
                    System.out.println(BR);
                }

            }



    }
}
