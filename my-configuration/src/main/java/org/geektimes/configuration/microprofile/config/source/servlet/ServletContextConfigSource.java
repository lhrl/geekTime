package org.geektimes.configuration.microprofile.config.source.servlet;

import org.geektimes.configuration.microprofile.config.source.MapBasedConfigSource;

import javax.servlet.ServletContext;
import java.util.Enumeration;
import java.util.Map;

/**
 * Desc: ServletContextConfigSource
 * User: 刘浪
 * Date: 2021-03-23 01:12
 */
public class ServletContextConfigSource extends MapBasedConfigSource {


    public ServletContextConfigSource(ServletContext servletContext) {
        super("ServletContext Init Parameters", 500, servletContext);
    }

    @Override
    protected void prepareConfigData(Map configData) throws Throwable {
        Enumeration<String> parameterNames = servletContext.getInitParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            configData.put(parameterName, servletContext.getInitParameter(parameterName));
        }
    }
}
