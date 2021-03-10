package org.geektimes.projects.user.context;

/**
 * Desc: 全局上下文Aware
 * User: 刘浪
 * Date: 2021-03-08 18:33
 */
public interface ComponentContextAware extends Aware {
    /**
     * 注入全局上下文
     * @param componentContext 全局上下文
     */
    void setComponentContext(ComponentContext componentContext);
}
