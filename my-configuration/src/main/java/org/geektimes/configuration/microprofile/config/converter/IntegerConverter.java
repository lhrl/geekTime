package org.geektimes.configuration.microprofile.config.converter;

/**
 * Desc: IntegerConverter
 * User: 刘浪
 * Date: 2021-03-23 00:12
 */
public class IntegerConverter extends AbstractConverter<Integer> {
    @Override
    protected Integer doConvert(String value) {
        return Integer.valueOf(value);
    }

}
