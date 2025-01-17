import java.util.Scanner;

public class Mocha {
    private static final String BR = "____________________________________________________________";
    private static boolean isRunning = true;

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
            System.out.println(BR + "\n" + input + "\n" + BR);
        }

    }
}
