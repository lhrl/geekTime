package org.geektimes.rest.util;

import javax.ws.rs.core.MultivaluedMap;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * Desc: Form表单工具类
 * User: 刘浪
 * Date: 2021-03-31 22:42
 */
public class FormUtils {


    public static String serializeForm(MultivaluedMap<String, String> formData, Charset charset) {
        StringBuilder builder = new StringBuilder();
        formData.forEach((name, values) -> {
            values.forEach(value -> {
                try {
                    if (builder.length() != 0) {
                        builder.append('&');
                    }
                    builder.append(URLEncoder.encode(name, charset.name()));
                    if (value != null) {
                        builder.append('=');
                        builder.append(URLEncoder.encode(value, charset.name()));
                    }
                } catch (UnsupportedEncodingException ex) {
                    throw new IllegalStateException(ex);
                }
            });
        });

        return builder.toString();
    }

}
