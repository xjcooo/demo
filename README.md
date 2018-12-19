# demo
这是一个demo项目，用于实践各种Java组件。
## swagger2
访问url：http://localhost:8080/swagger-ui.html
## SpringMVC异常统一处理
SpringMVC异常分两种：1.访问页面异常，2.restful访问异常
处理两种异常需要定义两种不同的异常进行抛出，然后分别定义处理方式，详细见GlobalControllerHandler and HelloController
## 异步任务
参照org.xjc.demo.task.AsyncTask
使用方式:
    启动类上添加@EnableAsync，然后使用@Async注解异步方法
    使用Futrue<>返回值进行回调处理-回调结果可以通过其get方法获取
        V get() throws InterruptedException, ExecutionException;
        V get(long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException;
    备注：get方法中还定义了该线程执行的超时时间，
        通过执行这个测试我们可以观察到执行时间超过5秒的时候，
        这里会抛出超时异常，该执行线程就能够因执行超时而释放回线程池，
        不至于一直阻塞而占用资源。

自定义线程池：
    配置参照:org.xjc.demo.configs.TaskPollConfig
    使用时，只需要在@Async中的value添加Bean的名字

自定义线程池优雅退出配置：
    executor.setWaitForTasksToCompleteOnShutdown(true);
    executor.setAwaitTerminationSeconds(60);    

## lombok
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
    @Setter
    @Getter
    @Data
    @Log(这是一个泛型注解，具体有很多种形式)
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @NonNull
    @Cleanup
    @ToString
    @RequiredArgsConstructor
    @Value
    @SneakyThrows
    @Synchronized
还有log日志注解方式：
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
