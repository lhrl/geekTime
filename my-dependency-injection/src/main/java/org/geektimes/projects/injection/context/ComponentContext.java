package org.geektimes.projects.injection.context;

import org.geektimes.projects.common.aware.Aware;
import org.geektimes.projects.common.function.ThrowableAction;
import org.geektimes.web.mvc.context.WebComponentContext;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.naming.*;
import javax.servlet.ServletContext;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Stream;

/**
 * Desc: 全局上下文
 * User: 刘浪
 * Date: 2021-03-24 16:36
 */
public class ComponentContext {

    private static final String CONTEXT_NAME = ComponentContext.class.getName() + ".ROOT";
    //Servlet容器上下文
    private static ServletContext servletContext;
    //用于依赖查找的JNDI上下文
    private Context jndiContext;
    //存放实例的容器resource name->component
    private Map<String, Object> componentMap = new LinkedHashMap<>();

    public static ComponentContext getInstance() {
        return (ComponentContext) servletContext.getAttribute(CONTEXT_NAME);
    }

    public void init(ServletContext servletContext)throws RuntimeException {
        servletContext.setAttribute(CONTEXT_NAME, this);
        ComponentContext.servletContext = servletContext;
        //1.初始化jndi上下文
        initJndiContext();
        //2.实例化组件
        instantiateComponent();
        //3.初始化组件
        initializeComponent();
    }

    private void initializeComponent() {
        componentMap.values().forEach(component -> {
            //3.1属性填充 @Resource注入
            populateComponent(component);
            //3.2初始化组件 @PostConstruct
            initializeComponent(component);
            //3.3回调aware接口
            invokeAwareInterfaces(component);
        });
    }

    private void initializeComponent(Object component) {
        Class<?> componentClass = component.getClass();
        //方法非静态 没有参数
        Stream.of(componentClass.getMethods()).filter(
                method -> !Modifier.isStatic(method.getModifiers()) && method.getParameterCount() == 0
                        && method.isAnnotationPresent(PostConstruct.class)).
                forEach(method -> {
                    try {
                        method.invoke(component);
                    } catch (Exception e) {
                        throw new RuntimeException(e.getCause());
                    }
                });
    }

    private void populateComponent(Object component) {
        Class<?> componentClass = component.getClass();
        //字段非静态并且标注了@Reousrce注解
        Stream.of(componentClass.getDeclaredFields()).filter(
                field-> !Modifier.isStatic(field.getModifiers())&& field.isAnnotationPresent(Resource.class)).
                forEach(field->{
                    field.setAccessible(true);
                    Resource resource = field.getAnnotation(Resource.class);
                    String targetName = resource.name();
                    Object injectComponent = getComponent(targetName);
                    try {
                        field.set(component,injectComponent);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e.getCause());
                    }
                });
    }

    private void instantiateComponent() {
        //遍历获取所有组件名称
        List<String> componentNames = listAllComponentNames();
        //依赖查找所有组件，实例化对象
        componentNames.forEach(componentName -> componentMap.put(componentName, getComponent(componentName)));
        //web上下文设置组件
        WebComponentContext.setComponentMap(componentMap);
    }

    private void invokeAwareInterfaces(Object component) {
        if (component instanceof Aware) {
            if (component instanceof ComponentContextAware) {
                ((ComponentContextAware) component).setComponentContext(this);
            }
            //other aware interfaces
        }
    }


    /**
     * 获取所有的组件名称
     *
     * @return
     */
    public List<String> getComponentNames() {
        return new ArrayList<>(componentMap.keySet());
    }

    private List<String> listAllComponentNames() {
        return listComponentNames("/");
    }

    /**
     * 遍历webapp/META-INF/context所定义的组件
     * @param name 组件名称
     * @return 所有组件名称
     */
    private List<String> listComponentNames(String name) {
        try {
            NamingEnumeration<NameClassPair> list = jndiContext.list(name);
            //context没有元素
            if (list == null) {
                return Collections.emptyList();
            }
            LinkedList<String> fullNames = new LinkedList<>();
            while (list.hasMoreElements()) {
                NameClassPair element = list.nextElement();
                String className = element.getClassName();
                Class<?> targetClass = Thread.currentThread().getContextClassLoader().loadClass(className);
                if (Context.class.isAssignableFrom(targetClass)) {
                    //如果当前依旧是目录 还没到实现类 继续递归
                    fullNames.addAll(listComponentNames(element.getName()));
                }else {
                    //已经到具体目标类型，将当前名称绑定到目标类型对象
                    String fullName = name.startsWith("/") ? element.getName() : name + "/" + element.getName();
                    fullNames.add(fullName);
                }
            }
            return fullNames;
        } catch (Throwable e) {
            throw new RuntimeException(e.getCause());
        }
    }

    /**
     * 初始化jndi上下文
     */
    private void initJndiContext() {
        Context context = null;
        try {
            context=new InitialContext();
            this.jndiContext = (Context) context.lookup("java:comp/env");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }finally {
            close(context);
        }
    }

    public void close(Context context) {
        if (context != null) {
            ThrowableAction.execute(context::close);
        }
    }

    public <C> C getComponent(String name){
        C component;
        try {
            component= (C) jndiContext.lookup(name);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return component;
    }

    public void destroy() {
        if (jndiContext != null) {
            try {
                jndiContext.close();
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
