# demo
这是一个demo项目，用于实践各种Java组件。
# 目录
- [1. swagger2](#1-swagger2)
- [2. SpringMVC异常统一处理](#2-springmvc异常统一处理)
- [3. 异步任务](#3-异步任务)
- [4. lombok](#4-lombok)
- [5. redis做集中式缓存](#5-redis做集中式缓存)
- [6. 自定义SpringBoot-starter开发](#6-自定义springboot-starter开发)
## 1. swagger2
访问url：http://localhost:8080/swagger-ui.html
## 2. SpringMVC异常统一处理
SpringMVC异常分两种：1.访问页面异常，2.restful访问异常
处理两种异常需要定义两种不同的异常进行抛出，然后分别定义处理方式，详细见GlobalControllerHandler and HelloController
## 3. 异步任务
参照org.xjc.demo.task.AsyncTask
* 使用方式:
    启动类上添加@EnableAsync，然后使用@Async注解异步方法
    使用Futrue<>返回值进行回调处理-回调结果可以通过其get方法获取
        V get() throws InterruptedException, ExecutionException;
        V get(long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException;
    备注：get方法中还定义了该线程执行的超时时间，
        通过执行这个测试我们可以观察到执行时间超过5秒的时候，
        这里会抛出超时异常，该执行线程就能够因执行超时而释放回线程池，
        不至于一直阻塞而占用资源。

* 自定义线程池：
    配置参照:org.xjc.demo.configs.TaskPollConfig
    使用时，只需要在@Async中的value添加Bean的名字

* 自定义线程池优雅退出配置：
    executor.setWaitForTasksToCompleteOnShutdown(true);
    executor.setAwaitTerminationSeconds(60);    

## 4. lombok
更多使用说明：
[点击这里](https://www.jianshu.com/p/365ea41b3573)

使用方式：1. intellij idea 安装 lombok plugin 插件， 2. 添加下述jar依赖
```xml
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    <version>1.18.4</version>
    <scope>provided</scope>
    </dependency>
```                   
常见注解：
* @Setter
* @Getter
* @Data
* @Log(这是一个泛型注解，具体有很多种形式)
* @AllArgsConstructor
* @NoArgsConstructor
* @EqualsAndHashCode
* @NonNull
* @Cleanup
* @ToString
* @RequiredArgsConstructor
* @Value
* @SneakyThrows
* @Synchronized
* 还有log日志注解方式：
```java
    //@CommonsLog
    private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(LogExample.class);
    //@JBossLog
    private static final org.jboss.logging.Logger log = org.jboss.logging.Logger.getLogger(LogExample.class);
    //@Log
    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(LogExample.class.getName());
    //@Log4j
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LogExample.class);
    //@Log4j2
    private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(LogExample.class);
    //@Slf4j
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogExample.class);
    //@XSlf4j
    private static final org.slf4j.ext.XLogger log = org.slf4j.ext.XLoggerFactory.getXLogger(LogExample.class);
```                                                                                                                             
## 5. redis做集中式缓存
详见CacheConfig、UserRepository， 测试类：UserRepositoryTest

## 6. 自定义SpringBoot-starter开发
使用的Spring官方的Starter一般采取spring-boot-starter-{name} 的命名方式，如 spring-boot-starter-web 。
    而非官方的Starter，官方建议 artifactId 命名应遵循{name}-spring-boot-starter 的格式。

开发步骤:
1. SpringBoot 在启动时会去依赖的starter包中寻找 resources/META-INF/spring.factories 文件，
    然后根据文件中配置的Jar包去扫描项目所依赖的Jar包，这类似于 Java 的 SPI 机制。
2. 根据 spring.factories配置加载AutoConfigure类
3. 根据 @Conditional注解的条件，进行自动配置并将Bean注入Spring Context 上下文当中。
    也可以使用@ImportAutoConfiguration({MyServiceAutoConfiguration.class}) 指定自动配置哪些类

备注:
```text
    列举SpringBoot中的所有@Conditional注解及作用
    @ConditionalOnBean:当容器中有指定的Bean的条件下  
    @ConditionalOnClass：当类路径下有指定的类的条件下  
    @ConditionalOnExpression:基于SpEL表达式作为判断条件  
    @ConditionalOnJava:基于JVM版本作为判断条件  
    @ConditionalOnJndi:在JNDI存在的条件下查找指定的位置  
    @ConditionalOnMissingBean:当容器中没有指定Bean的情况下  
    @ConditionalOnMissingClass:当类路径下没有指定的类的条件下  
    @ConditionalOnNotWebApplication:当前项目不是Web项目的条件下  
    @ConditionalOnProperty:指定的属性是否有指定的值  
    @ConditionalOnResource:类路径下是否有指定的资源  
    @ConditionalOnSingleCandidate:当指定的Bean在容器中只有一个，或者在有多个Bean的情况下，
        用来指定首选的Bean @ConditionalOnWebApplication:当前项目是Web项目的条件下  
```
