### 小马哥极客时间
#### 第四周作业：
一、完善 my-dependency-injection 模块
* 脱离 web.xml 配置实现 ComponentContext 自动初始化
* 使用独立模块并且能够在 user-web 中运行成功

二、完善 my-configuration 模块
* Config 对象如何能被 my-web-mvc 使用
    * 通过ServletContext获取
    * 通过 ThreadLocal获取
#### 步骤：
* mvn clean package
* java -jar user-web/target/user-web-v1-SNAPSHOT-war-exec.jar

#### 访问用户配置：
http://localhost:8080/user/config
