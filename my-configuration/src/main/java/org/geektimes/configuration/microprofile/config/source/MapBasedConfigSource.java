package org.geektimes.configuration.microprofile.config.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Desc: 基于 Map 数据结构 {@link ConfigSource} 的实现
 * User: 刘浪
 * Date: 2021-03-23 00:49
 */
public abstract class MapBasedConfigSource implements ConfigSource{

    private final String name;

    private final int ordinal;

    private final Map<String, String> source;

    protected final ServletContext servletContext;

    protected MapBasedConfigSource(String name, int ordinal,ServletContext servletContext) {
        this.name = name;
        this.ordinal = ordinal;
        this.servletContext = servletContext;
        this.source = getProperties();
    }

    protected MapBasedConfigSource(String name, int ordinal) {
        this(name, ordinal, null);
    }

    /**
     * 获取配置数据 Map
     *
     * @return 不可变 Map 类型的配置数据
     */
    public final Map<String, String> getProperties() {
        Map<String,String> configData = new HashMap<>();
        try {
            prepareConfigData(configData);
        } catch (Throwable cause) {
            throw new IllegalStateException("准备配置数据发生错误",cause);
        }
        return Collections.unmodifiableMap(configData);
    }


    /**
     * 准备配置数据
     *
     * @param configData
     * @throws Throwable
     */
    protected abstract void prepareConfigData(Map configData) throws Throwable;


    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final int getOrdinal() {
        return ordinal;
    }

    @Override
    public Set<String> getPropertyNames() {
        return source.keySet();
    }

    @Override
    public String getValue(String propertyName) {
        return source.get(propertyName);
    }

}
