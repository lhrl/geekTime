package org.geektimes.web.mvc.context;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Desc: web mvc上下文
 * User: 刘浪
 * Date: 2021-03-08 17:19
 */
public class WebComponentContext {

    //存放实例的容器resource name->component
    private static Map<String, Object> componentMap = new LinkedHashMap<>();

    public static Map<String, Object> getComponentMap() {
        return componentMap;
    }
    public static void setComponentMap(Map<String, Object> componentMap) {
        WebComponentContext.componentMap = componentMap;
    }
}
