package dev.profitisle.core;

/**
 * Represents a function that processes input data and produces output results.
 *
 * @param <I> The type of the input data.
 * @param <O> The type of the output data.
 */
@FunctionalInterface
public interface DataProcessor<I, O> {

    O process(I data) throws Exception;
}
