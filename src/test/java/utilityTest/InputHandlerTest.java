package utilityTest;

import org.TODO.utility.InputHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputHandlerTest {
    private InputHandler inputHandler;
    private InputStream originalSystemIn;

    @BeforeEach
    public void setUp() {
        inputHandler = new InputHandler();
        originalSystemIn = System.in;
    }

    @AfterEach
    public void tearDown() {
        System.setIn(originalSystemIn);
    }

    @Test
    public void testGetStringInputValidInput() {
        String expectedInput = "Hello, World!";
        provideMockInput(expectedInput);

        String actualInput = inputHandler.getStringInput();

        assertEquals(expectedInput, actualInput);
    }

    @Test
    public void testGetStringInputEmptyInputThenValidInput() {
        String expectedInput = "Hello";
        provideMockInput("\n" + expectedInput);

        String actualInput = inputHandler.getStringInput();

        assertEquals(expectedInput, actualInput);
    }

    @Test
    public void testGetIntegerInputValidInput() {
        int expectedInput = 42;
        provideMockInput(String.valueOf(expectedInput));

        int actualInput = inputHandler.getIntegerInput();

        assertEquals(expectedInput, actualInput);
    }

    @Test
    public void testGetIntegerInputInvalidInputThenValidInput() {
        int expectedInput = 42;
        provideMockInput("Invalid\n" + expectedInput);

        int actualInput = inputHandler.getIntegerInput();

        assertEquals(expectedInput, actualInput);
    }

    private void provideMockInput(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);
        inputHandler = new InputHandler();
    }
}
