import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mocha {
    private static final String BR = "____________________________________________________________";
    private static boolean isRunning = true;
    private static List<String> commands = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(BR + "\n Hello! I'm Mocha");
        System.out.println(" What can I do for you? \n" + BR);

        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("bye")) {
                isRunning = false;
                System.out.println(BR + "\n Bye. Hope to see you again soon! \n" + BR);
                break;
            }
            if (input.toLowerCase().equals("list")) {
                System.out.println(BR);
                for (int i = 1; i <= commands.size(); i++) {
                    System.out.println(i + ". " + commands.get(i - 1));
                }
                System.out.println(BR + "\n");
            } else {
                commands.add(input);
                System.out.println(BR + "\n" + input + "\n" + BR);
            }

        }

    }
}
