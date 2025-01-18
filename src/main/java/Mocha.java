import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mocha {
    private static final String BR = "____________________________________________________________";
    private static boolean isRunning = true;
    private static List<Task> commands = new ArrayList<>();
    private static Task task = null;

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
    private static String printUpdates() {
        return String.format(" Now you have %d tasks in the list. \n", commands.size());
    }

    public static void main(String[] args) {
        System.out.println(BR + "\n Hello! I'm Mocha");
        System.out.println(" What can I do for you? \n" + BR);

        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            String input = scanner.nextLine();
            // check for bye command to exit
            if (input.toLowerCase().equals("bye")) {
                isRunning = false;
                System.out.println(BR + "\n Bye. Hope to see you again soon! \n" + BR);
                break;
            }
            // check for command to print list
            if (input.toLowerCase().equals("list")) {
                System.out.println(BR);
                for (int i = 1; i <= commands.size(); i++) {
                    System.out.println(i + "." + commands.get(i - 1));
                }
                System.out.println(BR + "\n");
            } else {
                // parse input
                String[] split = input.split(" ");
                String[] date = input.split("/");
                String tmp = split[0].toLowerCase();


                // check for keywords mark and unmark
                if (tmp.equals("mark") || tmp.equals("unmark")) {
                    int idx = Integer.parseInt(split[1]);

                    if (idx < 1 || idx > commands.size()) {
                        throw new IndexOutOfBoundsException("Task does not exist");
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
                } else {
                    // switch case to respond to other input
                    String name = "";
                    String dueDate = "";
                    switch (tmp) {
                        case "todo":
                            // retrieve task
                            for (int i = 1; i < split.length; i++) {
                                name += " " + split[i];
                            }
                            task = new Todo(name);
                            commands.add(task);
                            System.out.println(BR + printNew() + task + "\n" + printUpdates() + BR);
                            break;
                        case "deadline":
                            // retrieve task
                            String[] cmd = date[0].split(" ");
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

                        default:
                            throw new IllegalArgumentException("Invalid command");
                    }
                }

            }

        }

    }
}
