package dev.profitisle.core;

import java.io.IOException;

/**
 * Read data from a variety of datasource's
 *
 * @param <T> The type of results data.
 */
@FunctionalInterface
public interface DataReader<T> {
    T get() throws IOException;
}
