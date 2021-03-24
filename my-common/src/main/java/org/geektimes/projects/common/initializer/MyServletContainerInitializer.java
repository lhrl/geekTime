package org.geektimes.projects.common.initializer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Desc: Servlet启动加载器 {@link ServletContainerInitializer
 * User: 刘浪
 * Date: 2021-03-23 01:19
 */
@HandlesTypes(MyApplicationInitializer.class)
public class MyServletContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> webAppInitializerClasses, ServletContext servletContext) throws ServletException {
        List<MyApplicationInitializer> initializers = new LinkedList<>();
        if (webAppInitializerClasses != null) {
            for (Class<?> waiClass : webAppInitializerClasses) {
                if (!waiClass.isInterface() && !Modifier.isAbstract(waiClass.getModifiers()) &&
                        MyApplicationInitializer.class.isAssignableFrom(waiClass)) {
                    try {
                        Constructor<?> constructor = waiClass.getDeclaredConstructor();
                        constructor.setAccessible(true);
                        initializers.add((MyApplicationInitializer)
                                constructor.newInstance());
                    } catch (Throwable ex) {
                        throw new ServletException("Failed to instantiate MyApplicationInitializer class", ex);
                    }
                }
            }
        }

        if (initializers.isEmpty()) {
            servletContext.log("No config MyApplicationInitializer types detected on classpath");
            return;
        }

        servletContext.log(initializers.size() + " config MyApplicationInitializer detected on classpath");
        for (MyApplicationInitializer initializer : initializers) {
            initializer.onStartup(servletContext);
        }
    }
}
