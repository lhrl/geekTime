package org.geektimes.configuration.microprofile.config.source.servlet;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;
import org.geektimes.configuration.microprofile.config.DefaultConfig;
import org.geektimes.projects.common.initializer.MyApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.logging.Logger;

/**
 * Desc: 系统配置类启动器
 * User: 刘浪
 * Date: 2021-03-23 01:14
 */
public class MyConfigApplicationInitializer implements MyApplicationInitializer {

    private static Logger logger = Logger.getLogger(MyConfigApplicationInitializer.class.getName());

    public static ThreadLocal<Config> configThreadLocal = new ThreadLocal<>();

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.info("MyConfigApplicationInitializer onStartup......");
        ServletContextConfigSource servletContextConfigSource = new ServletContextConfigSource(servletContext);
        // 获取当前 ClassLoader
        ClassLoader classLoader = servletContext.getClassLoader();
        ConfigProviderResolver configProviderResolver = ConfigProviderResolver.instance();
        ConfigBuilder configBuilder = configProviderResolver.getBuilder();
        // 配置 ClassLoader
        configBuilder.forClassLoader(classLoader);
        // 默认配置源（内建的，静态的）
        configBuilder.addDefaultSources();
        // 通过发现配置源（动态的）
        configBuilder.addDiscoveredConverters();
        // 增加扩展配置源（基于 Servlet 引擎）
        configBuilder.withSources(servletContextConfigSource);
        // 获取 Config
        Config config = configBuilder.build();
        // 注册 Config 关联到当前 ClassLoader
        configProviderResolver.registerConfig(config, classLoader);
        //基于servletContext传递config
        servletContext.setAttribute("config", config);
        //TODO 基于threadLocal传递config?
        configThreadLocal.set(config);
    }


}
