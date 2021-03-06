package org.geektimes.projects.user.context;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;

/**
 * Desc: 全局上下文
 * User: 刘浪
 * Date: 2021-03-06 11:22
 */
public class ComponentContext {

    private static final String CONTEXT_NAME = ComponentContext.class.getName() + ".JNDI";
    //Servlet容器上下文
    private static ServletContext servletContext;
    //用于依赖查找的JNDI上下文
    private Context context;

    public static ComponentContext getInstance() {
        return (ComponentContext) servletContext.getAttribute(CONTEXT_NAME);
    }

    public void init(ServletContext servletContext)throws RuntimeException {
        try {
            context = (Context) new InitialContext().lookup("java:comp/env");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        servletContext.setAttribute(CONTEXT_NAME, this);
        ComponentContext.servletContext = servletContext;
    }

    public <C> C getComponent(String name){
        C component;
        try {
            component= (C) context.lookup(name);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return component;
    }

    public void destroy() {
        if (context != null) {
            try {
                context.close();
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
