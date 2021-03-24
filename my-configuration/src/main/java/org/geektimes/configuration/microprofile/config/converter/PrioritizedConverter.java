package org.geektimes.configuration.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * Desc:
 * User: 刘浪
 * Date: 2021-03-23 00:18
 */
public class PrioritizedConverter<T> implements Converter<T>, Comparable<PrioritizedConverter> {

    private final int priority;

    private final Converter<T> converter;

    public PrioritizedConverter(Converter<T> converter,int priority) {
        this.converter = converter;
        this.priority = priority;
    }


    public int getPriority() {
        return priority;
    }

    public Converter<T> getConverter() {
        return converter;
    }

    @Override
    public T convert(String s) throws IllegalArgumentException, NullPointerException {
        return converter.convert(s);
    }

    @Override
    public int compareTo(PrioritizedConverter other) {
        return Integer.compare(other.getPriority(), this.getPriority());
    }
}
