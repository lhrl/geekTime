package org.geektimes.configuration.microprofile.config.converter;

/**
 * Desc: BooleanConverter
 * User: 刘浪
 * Date: 2021-03-23 00:14
 */
public class BooleanConverter extends AbstractConverter<Boolean> {
    @Override
    protected Boolean doConvert(String value) {
        return Boolean.parseBoolean(value);
    }
}
