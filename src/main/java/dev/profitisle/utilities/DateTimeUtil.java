package dev.profitisle.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for date and time operations.
 *
 * @author Jawad Zaarour
 */
public final class DateTimeUtil {

    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            DateTimeFormatter.ISO_OFFSET_DATE_TIME,
            DateTimeFormatter.ISO_ZONED_DATE_TIME
    );

    private DateTimeUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Parses a date string to {@link LocalDate}.
     *
     */
    public static LocalDate getDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Parses a date-time string to {@link LocalDate}.
     *
     * @throws DateTimeParseException if the date-time string cannot be parsed
     */
    public static LocalDate parseToDate(String dateTime) {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDateTime.parse(dateTime, formatter).toLocalDate();
            } catch (DateTimeParseException e) {
                // Try next formatter
            }
        }
        throw new DateTimeParseException("Unable to parse date-time: " + dateTime, dateTime, 0);
    }
}
