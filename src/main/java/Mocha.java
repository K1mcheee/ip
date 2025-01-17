import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mocha {
    private static final String BR = "____________________________________________________________";
    private static boolean isRunning = true;
    private static List<Task> commands = new ArrayList<>();

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
                    System.out.println(i + ". " + commands.get(i - 1));
                }
                System.out.println(BR + "\n");
            } else {
                // parse input
                String[] split = input.split(" ");
                String tmp = split[0].toLowerCase();

                // check for keywords mark and unmark
                if (tmp.equals("mark") || tmp.equals("unmark")) {
                    int idx = Integer.parseInt(split[1]);

                    if (idx < 1 || idx > commands.size()) {
                        throw new ArrayIndexOutOfBoundsException("Task does not exist");
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
                    // if not keyword, add to list of commands
                    Task task = new Task(input);
                    commands.add(task);
                    System.out.println(BR + "\n added: " + input + "\n" + BR);
                }

            }

        }

    }
}
