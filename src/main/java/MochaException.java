public class MochaException extends Exception {

    public MochaException(String message) {
        super(message);
    }

    public static void emptyDescription(String task) throws MochaException{
        throw new MochaException("Task requires a description!\nFor example...\n" + task);
    }
}
