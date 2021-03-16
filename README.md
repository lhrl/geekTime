### 小马哥极客时间
#### 第三周作业：
需求一：整合 https://jolokia.org/，实现一个自定义 JMX MBean，通过 Jolokia 做 Servlet 代理
#### 步骤：
* mvn clean package
* java -jar user-web/target/user-web-v1-SNAPSHOT-war-exec.jar

#### 写入MBean：
http://localhost:8080/jolokia/write/com.lhrl.jmx:type=Student/Name/zhangsan
#### 读取MBean：
http://localhost:8080/jolokia/read/com.lhrl.jmx:type=Student