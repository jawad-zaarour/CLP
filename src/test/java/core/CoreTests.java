package core;

import dev.profitisle.domains.Cookie;
import dev.profitisle.domains.CookieLog;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Tag("core")
public interface CoreTests {

    String FILE = "src/test/resources/cookie_log.csv";
    String FILE_LARGE = "src/test/resources/cookie_large.csv";
    String FILE_NOT_EXIST = "src/test/resources/non_existent_file.csv";
    String FILE_EMPTY = "src/test/resources/empty.csv";
    String FILE_INVALID = "src/test/resources/cookie_invalidLog.csv";
    LocalDate ANY_DATE = LocalDate.of(2022, 1, 1);

    static Stream<Arguments> processTestParams() {
        return Stream.of(
                Arguments.of(
                        new CookieLog(Collections.emptyList()),
                        Collections.emptyList()
                ),
                Arguments.of(
                        new CookieLog(List.of(new Cookie("cookie1", ANY_DATE))),
                        List.of("cookie1")
                ),
                Arguments.of(
                        new CookieLog(Arrays.asList(
                                new Cookie("cookie1", ANY_DATE),
                                new Cookie("cookie2", ANY_DATE),
                                new Cookie("cookie2", ANY_DATE),
                                new Cookie("cookie3", ANY_DATE)
                        )),
                        List.of("cookie2")
                ),
                Arguments.of(
                        new CookieLog(Arrays.asList(
                                new Cookie("cookie1", ANY_DATE),
                                new Cookie("cookie1", ANY_DATE),
                                new Cookie("cookie2", ANY_DATE),
                                new Cookie("cookie2", ANY_DATE),
                                new Cookie("cookie3", ANY_DATE)
                        )),
                        List.of("cookie1", "cookie2")
                ),
                Arguments.of(
                        new CookieLog(Arrays.asList(
                                new Cookie("cookie1", ANY_DATE),
                                new Cookie("cookie2", ANY_DATE),
                                new Cookie("cookie3", ANY_DATE)
                        )),
                        List.of("cookie1", "cookie2", "cookie3")
                ),
                // Very large number of cookies, all equally frequent
                Arguments.of(
                        new CookieLog(Stream.iterate(0, i -> i + 1)
                                .limit(100_000)
                                .map(i -> new Cookie("cookie" + i, ANY_DATE))
                                .toList()),
                        Stream.iterate(0, i -> i + 1)
                                .limit(100_000)
                                .map(i -> "cookie" + i)
                                .sorted()
                                .toList()
                ),

                // Cookies with special characters
                Arguments.of(
                        new CookieLog(Arrays.asList(
                                new Cookie("cookie!@#", ANY_DATE),
                                new Cookie("cookie$%^", ANY_DATE),
                                new Cookie("cookie&*(", ANY_DATE),
                                new Cookie("cookie&*(", ANY_DATE),
                                new Cookie("cookie$%^", ANY_DATE)

                        )),
                        List.of("cookie$%^", "cookie&*(")
                ),

                // Cookies with very long IDs
                Arguments.of(
                        new CookieLog(Arrays.asList(
                                new Cookie("a".repeat(1000), ANY_DATE),
                                new Cookie("a".repeat(1000), ANY_DATE),
                                new Cookie("b".repeat(1000), ANY_DATE)
                        )),
                        List.of("a".repeat(1000))
                )
        );
    }

    static Stream<Arguments> filterTestParams() {
        return Stream.of(
                Arguments.of("2018-12-09", List.of(
                        new Cookie("AtY0laUfhglK3lC7", LocalDate.of(2018, 12, 9)),
                        new Cookie("SAZuXPGUrfbcn5UA", LocalDate.of(2018, 12, 9)),
                        new Cookie("5UAVanZf6UtGyKVS", LocalDate.of(2018, 12, 9)),
                        new Cookie("AtY0laUfhglK3lC7", LocalDate.of(2018, 12, 9))
                )),

                Arguments.of("2018-12-07", List.of(
                        new Cookie("4sMM2LxV07bPJzwf", LocalDate.of(2018, 12, 7))
                ))
        );
    }

    static Stream<Arguments> filterTestParamsLargeFile() {
        return Stream.of(
                Arguments.of("2024-07-21", 428071),
                Arguments.of("2024-07-02", 426435)
        );
    }
}
