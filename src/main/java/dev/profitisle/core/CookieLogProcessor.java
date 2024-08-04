package dev.profitisle.core;

import dev.profitisle.domains.Cookie;
import dev.profitisle.domains.CookieLog;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Process to find the most active cookies for a specific day.
 *
 * @author Jawad Zaarour
 */
public class CookieLogProcessor implements DataProcessor<CookieLog, List<String>> {

    @Override
    public List<String> process(CookieLog log) {
        // count the occurrences of each cookie
        Map<String, Long> cookieCounts = log
                .cookies()
                .stream()
                .collect(Collectors.groupingBy(Cookie::cookieId, Collectors.counting()));

        // return empty list
        if (cookieCounts.isEmpty()) return Collections.emptyList();

        // determine the most active ones
        long maxCount = Collections.max(cookieCounts.values());
        return cookieCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());
    }
}
