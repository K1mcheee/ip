package mocha;

import mocha.command.Command;

import java.io.FileNotFoundException;

/**
 * Encapsulates the functionality of a chatbot
 *
 * @author K1mcheee
 */
public class Mocha {
    private static boolean isRunning = true;

    private TaskFile taskFile; //storage
    private Ui ui;
    private TaskList taskList;

    public Mocha(String filePath) {
        this.ui = new Ui();
        this.taskFile = new TaskFile(filePath); //storage

        try {
            this.taskList = new TaskList(this.taskFile.loadTask()); //tasklist
        } catch (FileNotFoundException e) {
            ui.printError("Error could not load file: " + e.getMessage());
        }
    }

    public void run() {
        ui.welcome();
        while (isRunning) {
            String input = ui.read(); //parser
            try {
                Command c = Parser.validateInput(input); //parser
                c.execute(this.taskList, this.ui, this.taskFile);
                isRunning = c.isRunning();
            } catch (MochaException e) {
                this.ui.printError(e.getMessage());
            }

        }
    }

    public static void main(String[] args) {
        new Mocha("data/mocha.txt").run();
    }
}
