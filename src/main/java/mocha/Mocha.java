package mocha;

import mocha.command.Command;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

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

    public String getResponse(String input) {
        // creates a stream to store output
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        // saves old system.out
        PrintStream prev = System.out;
        try {
            ui.response();
            Command c = Parser.validateInput(input); //parser
            c.execute(this.taskList, this.ui, this.taskFile);
        } catch (MochaException e) {
            this.ui.printError(e.getMessage());
        }
        // restore the system.out
        System.setOut(prev);
        return output.toString();
    }

    public static void main(String[] args) {
        //new Mocha("data/mocha.txt").run();
    }
}
