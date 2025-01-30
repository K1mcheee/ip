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
        Command c = null;

        // check for bye command to exit
        if (input.toLowerCase().equals("bye")) {
            c = new ByeCommand();
        }  else if (input.toLowerCase().equals("due")) {
            // check for command to print due tasks
            c = new DueCommand();
        }   else if (input.toLowerCase().equals("list")) {
            // check for command to print list
            c = new ListCommand();
        } else {
            // check for keywords mark and unmark
            if (tmp.equals("mark") || tmp.equals("unmark") || tmp.equals("delete")) {
                int idx = Integer.parseInt(split[1]);

                if (tmp.equals("mark")) {
                    c = new MarkCommand(idx);
                }
                if (tmp.equals("unmark")) {
                    c = new UnmarkCommand(idx);
                }
                if (tmp.equals("delete")) {
                    c = new DeleteCommand(idx);
                }
            } else {
                // switch case to respond to input
                switch (tmp) {
                case "todo":
                    if (split.length < 2) {
                        MochaException.emptyDescription("todo have 5 cups of bubble tea");
                    }
                    c = new TodoCommand(input);
                    break;
                case "deadline":
                    if (split.length < 2) {
                        MochaException.emptyDescription("deadline finish the bubble tea /by today");
                    }
                    if (date.length < 2) {
                        throw new MochaException("Remember to add a due date using:\n /by duedate");
                    }
                    c = new DeadlineCommand(input);
                    break;

                case "event":
                    if (split.length < 2) {
                        MochaException.emptyDescription("event Try all bubble teas /from Monday /to Friday");
                    }
                    if (date.length < 3) {
                        throw new MochaException("events require a from and to date!\n /from fromDate /to toDate");
                    }
                    c = new EventCommand(input);
                    break;

                default:
                    throw new MochaException("Searching through tasks... \n" +
                            "sorry, it seems I have not learnt this command!");
                }
            }
        }
        return c;
    }




}
