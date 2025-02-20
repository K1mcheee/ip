package mocha;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void invalidInput_exceptionThrown() {
        try {
            assertEquals(0, Parser.validateInput("todo"));
            fail();
        } catch (MochaException e) {
            assertEquals("Task requires a description!\nFor example...\ntodo have 5 cups of bubble tea",
                    e.getMessage());
        }
    }

    @Test
    public void missingIndex_exceptionThrown() {
        try {
            assertEquals(0, Parser.validateInput("delete"));
            fail();
        } catch (MochaException e) {
            assertEquals("Specify the task number after the command!", e.getMessage());
        }
    }

    @Test
    public void invalidCommand_exceptionThrown() {
        try {
            assertEquals(0, Parser.validateInput("hello"));
            fail();
        } catch (MochaException e) {
            assertEquals("Sorry I am out of beans... \n" +
                    "I do not understand this command!", e.getMessage());
        }
    }
}
