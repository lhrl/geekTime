package org.geektimes.configuration.microprofile.config.source;

import java.util.Map;

/**
 * Desc: 操作系统环境变量 ConfigSource
 * User: 刘浪
 * Date: 2021-03-23 00:54
 */
public class OperationSystemEnvironmentVariablesConfigSource extends MapBasedConfigSource {

    public OperationSystemEnvironmentVariablesConfigSource() {
        super("Operation System Environment Variables", 300);
    }

    @Override
    protected void prepareConfigData(Map configData) throws Throwable {
        configData.putAll(System.getenv());
    }
}
