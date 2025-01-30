package mocha;

/**
 * Encapsulates a custom exception thrown that
 * deals with specific input errors to Mocha
 * that users make
 *
 * @author K1mcheee
 */
public class MochaException extends Exception {

    /**
     * Constructor for the exception,
     * specifies message to be printed.
     *
     * @param message Specific message to inform user the error.
     */
    public MochaException(String message) {
        super(message);
    }

    /**
     * Deals with the error where users did not
     * provide a description after the command.
     *
     * @param task String of the specific message
     *             according to the error made.
     * @throws MochaException for handling of error.
     */
    public static void emptyDescription(String task) throws MochaException{
        throw new MochaException("Task requires a description!\nFor example...\n" + task);
    }

    public static void invalidDateTime() throws MochaException{
        throw new MochaException("Invalid date/time! Input as yyyy-mm-dd for date and tttt for time!");
    }
}
