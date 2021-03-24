package org.geektimes.configuration.microprofile.config.converter;

/**
 * Desc: StringConverter
 * User: 刘浪
 * Date: 2021-03-23 00:11
 */
public class StringConverter extends AbstractConverter<String> {

    @Override
    protected String doConvert(String value) {
        return value;
    }

}
