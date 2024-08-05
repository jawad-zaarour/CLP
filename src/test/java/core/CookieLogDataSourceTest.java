package core;

import dev.profitisle.core.CookieLogFileDataReader;
import dev.profitisle.domains.Cookie;
import dev.profitisle.domains.CookieLog;
import utilities.LogCreator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CookieLogDataSourceTest implements CoreTests {

    private CookieLogFileDataReader logFileDataReader;

    @ParameterizedTest
    @MethodSource("filterTestParams")
    @DisplayName("Validate filtered cookies for given date")
    public void shouldValidateFilteredCookiesForGivenDate(String date, List<Cookie> expectedCookies) throws IOException {
        logFileDataReader = LogCreator.newCookieFileReader(FILE, date);

        CookieLog result = logFileDataReader.get();

        assertAll("Filtered cookies assertions",
                () -> assertEquals(expectedCookies.size(), result.cookies().size()),
                () -> assertEquals(expectedCookies, result.cookies())
        );
    }

    @Test
    @DisplayName("Validate no matches for a date with no cookies")
    public void shouldReturnEmptyForDateWithNoCookies() throws IOException {
        logFileDataReader = LogCreator.newCookieFileReader(FILE, "2018-12-05");

        CookieLog result = logFileDataReader.get();

        assertEquals(0, result.cookies().size());
    }

    @Test
    @DisplayName("Validate no return for an empty file")
    public void shouldReturnEmptyForEmptyFile() throws IOException {
        logFileDataReader = LogCreator.newCookieFileReader(FILE_EMPTY, "2018-12-05");

        CookieLog result = logFileDataReader.get();

        assertNotNull(result);
        assertEquals(0, result.cookies().size());
    }

    @ParameterizedTest
    @MethodSource("filterTestParamsLargeFile")
    @Disabled
    @DisplayName("Validate filtered cookies for a large file")
    public void shouldValidateFilteredCookiesForLargeFile(String date, int expectedSize) throws IOException {
        logFileDataReader = LogCreator.newCookieFileReader(FILE_LARGE, date);

        CookieLog result = logFileDataReader.get();

       assertEquals(expectedSize, result.cookies().size());
    }

    @Test
    @DisplayName("Verify that NoSuchFileException is thrown when file does not exist")
    public void shouldThrowNoSuchFileExceptionWhenFileNotFound() {
        logFileDataReader = LogCreator.newCookieFileReader(FILE_NOT_EXIST, "2018-12-05");

        assertThrows(
                NoSuchFileException.class,
                () -> logFileDataReader.get()
        );
    }

    @Test
    @DisplayName("Verify that DateTimeParseException is thrown for invalid dates in log file")
    public void shouldThrowDateTimeParseExceptionForInvalidDates() {
        logFileDataReader = LogCreator.newCookieFileReader(FILE_INVALID, "2018-12-05");

        assertThrows(
                DateTimeParseException.class,
                () -> logFileDataReader.get()
        );
    }
}