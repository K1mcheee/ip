package mocha;

import mocha.task.Task;

import java.util.Scanner;

public class Ui {
    /**Breakline to separate texts*/
    private static final String BR = "===================";
    private Scanner scanner = new Scanner(System.in);

    /**
     * Prints default response for personality.
     */
    public void response() {
        System.out.println("\u2615 Brewing response \u2615... \n" + BR);
    }

    /**
     * Prints goodbye message when user types "bye".
     */
    public void goodBye() {
        System.out.println(BR + "\n Bye. Hope to see you again soon! \n" + BR); //ui
    }

    public void delete(Task task) {
        System.out.println("Alright, I have removed this task:");
        System.out.println(task);
    }

    /**
     * Prints error message to user.
     *
     * @param error Error message generated.
     */
    public void printError(String error) {
        System.out.println(error);
    }

    /**
     * Helper function to print when task is added.
     *
     * @return Standard reply that task is updated.
     */
    public String printNew() {
        return " Got it. I've added this task: ";
    }

    /**
     * Helper function to print whenever list is updated.
     *
     * @return  number of tasks in list.
     */
    public String printUpdates(int len) {
        return String.format(" Now you have %d tasks in the list.", len);
    }

    public void printTask(Task task) {
        System.out.println(task);
    }

    public void printTaskList(TaskList tasks) {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i - 1));
        }
    }

    public void printNewTask(Task task, int len) {
        System.out.println(this.printNew() + "\n" + task
                + "\n" + this.printUpdates(len));
        
    }

}
