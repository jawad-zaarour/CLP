package core;

import dev.profitisle.core.CookieLogProcessor;
import dev.profitisle.domains.CookieLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CookieLogProcessorTest implements CoreTests {

    private CookieLogProcessor processor;

    @BeforeEach
    public void setUp() {
        processor = new CookieLogProcessor();
    }

    @ParameterizedTest
    @MethodSource("processTestParams")
    @DisplayName("Verify correct active cookies matching")
    public void shouldMatchCorrectActiveCookies(CookieLog cookies, List<String> expectedCookies) {
        List<String> mostActiveCookies = processor.process(cookies);

        assertEquals(expectedCookies, mostActiveCookies);
    }
}