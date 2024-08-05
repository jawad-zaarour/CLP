package cli;

import dev.profitisle.cli.ClpMain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClpMainTest implements CliTests {

    @Test
    @DisplayName("Verify correct results of finding most active cookies")
    public void shouldFindMostActiveCookies() throws IOException {
        // Prepare the arguments
        LocalDate date = LocalDate.of(2018, 12, 9);
        String[] args = {"-f", FILE_CSV, "-d", date.toString()};

        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Run the main method with arguments
        ClpMain.main(args);

        // Restore System.out
        System.setOut(originalOut);

        // Normalize line endings and trim extra spaces
        String original = outContent.toString();
        String trim = original.trim();
        String output = trim.replace("\r\n", "\n");
        List<String> expectedOutput = List.of(
                "AtY0laUfhglK3lC7"
        );

        // Split output by lines and check if it matches the expected output
        List<String> actualOutput = List.of(output.split("\n"));
        assertEquals(expectedOutput, actualOutput, "Output does not match expected result");
    }

    @Test
    @DisplayName("Verify a file not found message when file does not exist")
    public void shouldReturnFileNotFoundMessage() throws IOException {
        // Prepare the arguments with a non-existent file
        LocalDate date = LocalDate.of(2018, 12, 9);
        String[] args = {"-f", FILE_NOT_EXIST, "-d", date.toString()};

        // Capture the error output
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        // Run the main method with arguments
        ClpMain.main(args);

        // Restore System.err
        System.setErr(originalErr);

        // Check the error output
        String errorOutput = errContent.toString().trim();
        String expectedErrorMessage = "File not found: src\\test\\resources\\no_exist_file.csv";

        assertEquals(expectedErrorMessage, errorOutput);
    }

    @Test
    @DisplayName("Verify file name parameter is required")
    public void shouldReturnMissingFileNameParamMessage() throws IOException {
        // Prepare the arguments with a non-existent file
        LocalDate date = LocalDate.of(2018, 12, 9);
        String[] args = {"-d", date.toString()};

        // Capture the error output
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        // Run the main method with arguments
        ClpMain.main(args);

        // Restore System.err
        System.setErr(originalErr);

        // Check the error output
        String errorOutput = errContent.toString().trim();
        String expectedErrorMessage = "Missing required option: '--file=<filename>'";

        assertEquals(expectedErrorMessage, errorOutput);
    }

    @Test
    @DisplayName("Verify date parameter is required")
    public void shouldReturnMissingDateParamMessage() throws IOException {
        // Prepare the arguments with a non-existent date
        String[] args = {"-f", FILE_CSV};

        // Capture the error output
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        // Run the main method with arguments
        ClpMain.main(args);

        // Restore System.err
        System.setErr(originalErr);

        // Check the error output
        String errorOutput = errContent.toString().trim();
        String expectedErrorMessage = "Missing required option: '--date=<date>'";

        assertEquals(expectedErrorMessage, errorOutput);
    }

    @Test
    @DisplayName("Verify both date and file name parameters are required")
    public void shouldReturnMissingParamsMessage() throws IOException {
        // Prepare the arguments with no param or argument
        String[] args = {};

        // Capture the error output
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        // Run the main method with arguments
        ClpMain.main(args);

        // Restore System.err
        System.setErr(originalErr);

        // Check the error output
        String errorOutput = errContent.toString().trim();
        String expectedErrorMessage = "Missing required options: '--file=<filename>', '--date=<date>'";

        assertEquals(expectedErrorMessage, errorOutput);
    }
}
