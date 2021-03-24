package org.geektimes.configuration.microprofile.config.source;

import java.util.Map;

/**
 * Desc: 动态配置源
 * User: 刘浪
 * Date: 2021-03-23 00:56
 */
public class DynamicConfigSource extends MapBasedConfigSource {


    private Map configData;

    public DynamicConfigSource() {
        super("DynamicConfigSource", 500);
    }

    @Override
    protected void prepareConfigData(Map configData) throws Throwable {
        this.configData = configData;
    }
}
