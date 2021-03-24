package org.geektimes.configuration.microprofile.config.converter;

/**
 * Desc: ByteConverter
 * User: 刘浪
 * Date: 2021-03-23 00:14
 */
public class ByteConverter extends AbstractConverter<Byte> {

    @Override
    protected Byte doConvert(String value) {
        return Byte.valueOf(value);
    }

}
