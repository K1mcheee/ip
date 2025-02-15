package mocha;

import mocha.command.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }

    public static LocalDate parseDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                .toLocalDate();
    }

    private static Command parseTaskCommand(String tmp, String input,
                                            String[] split, String[] date) throws MochaException {
        switch (tmp) {
        case "todo":
            if (split.length < 2) {
                MochaException.emptyDescription("todo have 5 cups of bubble tea");
            }
            return new TodoCommand(input);
        case "deadline":
            if (split.length < 2) {
                MochaException.emptyDescription("deadline finish the bubble tea /by today");
            }
            if (date.length < 2) {
                throw new MochaException("Remember to add a due date using:\n /by duedate");
            }
            return new DeadlineCommand(input);
        case "event":
            if (split.length < 2) {
                MochaException.emptyDescription("event Try all bubble teas /from Monday /to Friday");
            }
            if (date.length < 3) {
                throw new MochaException("events require a from and to date!\n /from fromDate /to toDate");
            }
            return new EventCommand(input);
        default:
            throw new MochaException("Searching through tasks... \n" +
                    "sorry, it seems I have not learnt this command!");
        }
    }

    private static Command parseOtherCommand(String tmp, String input,
                                               String[] split, String[] keywords) throws MochaException {
        // check for validity
        if (tmp.equals("mark") || tmp.equals("unmark") || tmp.equals("untag")
                || tmp.equals("delete") || tmp.equals("find") || tmp.equals("tag")) {
            if (split.length < 2) {
                throw new MochaException("Specify the task number after the command!");
            }
        }
        if (tmp.equals("find")) {
            String keyword = split[1];
            return new FindCommand(keyword);
        }
        int idx = Integer.parseInt(split[1]);

        if (tmp.equals("tag")) {
            String tag = keywords[1];
            return new TagCommand(tag, idx);
        }
        return switch (tmp) {
        case "mark" -> new MarkCommand(idx);
        case "unmark" ->  new UnmarkCommand(idx);
        case "delete" -> new DeleteCommand(idx);
        case "untag" -> new UntagCommand(idx);
        default ->  throw new MochaException("Invalid command");
        };
    }

    /**
     * Parse and validate the input entered by user and
     * responds accordingly. If user enters invalid input,
     * corresponding advice on the error is printed.
     *
     * @param input String entered by user.
     * @throws MochaException if user enters invalid commands.
     */
    public static Command validateInput(String input) throws MochaException {
        // parse input
        String[] split = input.split(" ");
        String[] date = input.split("/");
        String tmp = split[0].toLowerCase();
        Command c;

        // check for single-word commands
        switch (tmp) {
        case "bye" -> c = new ByeCommand(); // check for command to exit
        case "due" -> c = new DueCommand(); // check for command to print due tasks
        case "list" -> c = new ListCommand(); // check for command to print list
        case "find", "mark", "unmark", "delete", "tag", "untag" -> c = parseOtherCommand(tmp, input, split, date);
        default -> c = parseTaskCommand(tmp, input, split, date);
        }

        return c;

    }
}
