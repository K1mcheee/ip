package mocha.task;

import mocha.MochaException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void staticHandle_validDate_success() throws MochaException {
        // handle input with just date
        assertEquals((new Deadline(" sleep", LocalDate.parse("2025-01-30"))).toString(),
                (Deadline.handle("deadline sleep /by 2025-01-30", 1)).toString());

        // handle input with date and time
        assertEquals((new Deadline(" sleep", LocalDateTime.parse("2025-01-30 2359",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")))).toString(),
                (Deadline.handle("deadline sleep /by 2025-01-30 2359", 1)).toString());

    }
    
    @Test
    public void static_handle_invalidDateFormat_exceptionThrown() throws MochaException {
        try {
            assertEquals("Invalid date/time! Input as yyyy-mm-dd for date and HHmm for time!",
                    Deadline.handle("deadline sleep /by 2024-1-1", 1));
            fail();
        } catch (MochaException e) {
            assertEquals("Invalid date/time! Input as yyyy-mm-dd for date and HHmm for time!",
                    e.getMessage());
        }
    }

    @Test
    public void static_handle_invalidDate_exceptionThrown() throws MochaException {
        try {
            assertEquals(0, Deadline.handle("deadline sleep /by 2025-02-30", 1));
            fail();
        } catch (DateTimeParseException e) {
            assertEquals("Text '2025-02-30' could not be parsed: Invalid date 'FEBRUARY 30'",
                    e.getMessage());
        }
    }

    @Test
    public void static_handle_missingDate_exceptionThrown() throws MochaException {
        try {
            assertEquals(0, Deadline.handle("deadline sleep /by", 1));
            fail();
        } catch (MochaException e) {
            assertEquals("Invalid date/time! Input as yyyy-mm-dd for date and HHmm for time!",
                    e.getMessage());
        }
    }


}
