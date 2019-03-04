# demo
这是一个demo项目，用于实践各种Java组件,包括SpringBoot/SpringCloud,但不仅限于此。
# 目录
- [1. swagger2](#1-swagger2)
- [2. SpringMVC异常统一处理](#2-springmvc异常统一处理)
- [3. 异步任务](#3-异步任务)
- [4. lombok](#4-lombok)
- [5. redis做集中式缓存](#5-redis做集中式缓存)
- [6. 自定义SpringBoot-starter开发](#6-自定义springboot-starter开发)
- [7. Spring Cloud Alibaba Nacos](#7-spring_cloud_alibaba_nacos)
- [8. 自定义注解方式redis分布式锁](#8-自定义注解方式redis分布式锁)
- [9. logback使用](#9-logback使用)
## 1. swagger2

访问url：http://localhost:8080/swagger-ui.html

配置方式见:Swagger2AppConfig

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

使用方式：
- 1. intellij idea 安装 lombok plugin 插件， 
- 2. 添加下述jar依赖
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

参考项目:demo_springboot_starter and demo_springboot_starter_example

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
## 7. Spring Cloud Alibaba Nacos

## 8. 自定义注解方式redis分布式锁
注解:@DistributedLock @DistributedParam
分布式锁key生成器:LockAopKeyGenerator
生成器注册:BeanConfigs.lockKeyGenerator()
锁拦截器:LockMethodInterceptor

使用用例:UserServiceImpl.getUserById

## 9. logback使用
使用范例见:resources/logback.xml
### o. 依赖
```xml
<!-- json输出格式插件-->
    <dependency>
        <groupId>net.logstash.logback</groupId>
        <artifactId>logstash-logback-encoder</artifactId>
    </dependency>
<!-- 使用slf4j输出日志-->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
 </dependency>
<!-- 一下两个包是logback日志-->
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-core</artifactId>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
    </dependency>
```

### o. 设置属性
```xml
<!--读取spring容器中的配置属性-->
<springProperty scope="context" name="appName" source="spring.application.name" />
<!--定义属性-->
<property name="LOG_PATH" value="phantom-log" />

```
### a. <filter>标签 常见过滤器有:LevelFilter/ThresholdFilter/EvaluatorFilter,分别是日志级别过滤器/临界值过滤器(过滤掉低于指定临界值的日志)/求值过滤器

```xml
<!--ch.qos.logback.classic.filter.LevelFilter配置:-->
    <!-- 节点<onMatch>/<onMismatch>的值有三个可选:DENY，NEUTRAL，ACCEPT -->
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
    <!-- 例子:过滤掉非INFO级别的日志-->
        <!-- 过滤器配置的日志级别 -->
        <level>INFO</level>
        <!-- 配置符合过滤条件的操作 -->
        <onMatch>ACCEPT</onMatch>
        <!-- 配置不符合过滤条件的操作 -->
        <onMismatch>DENY</onMismatch>
    </filter>
    
<!--ch.qos.logback.classic.filter.ThresholdFilter配置:-->
    <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
    <!-- 例子:过滤掉低于指定临界值的日志 -->
        <level>INFO</level>
    </filter>
    
    
<!--ch.qos.logback.core.filter.EvaluatorFilter配置:-->
    <!-- 节点<onMatch>/<onMismatch>的值有三个可选:DENY，NEUTRAL，ACCEPT -->
    <filter class="ch.qos.logback.core.filter.EvaluatorFilter">  
    <!-- 例子:过滤掉所有日志消息中不包含“billing”字符串的日志 -->
        <evaluator> 
        <!-- 
            默认为 ch.qos.logback.classic.boolex.JaninoEventEvaluator
            鉴别器，常用的鉴别器是JaninoEventEvaluato，也是默认的鉴别器，它以任意的java布尔值表达式作为求值条件，
            求值条件在配置文件解释过成功被动态编译，布尔值表达式返回true就表示符合过滤条件。
            evaluator有个子标签<expression>，用于配置求值条件
        -->
            <expression>return message.contains("billing");</expression>
        </evaluator>
        <OnMatch>ACCEPT</OnMatch>  
        <OnMismatch>DENY</OnMismatch>  
    </filter>
```
#### a.a EvaluatorFilter过滤器中<expression>标签中表达式可以使用的字段以及说明(说明格式:name|type|description):

event|LoggingEvent|与记录请求相关联的原始记录事件，下面所有变量都来自event，例如，event.getMessage()返回下面"message"相同的字符串

message|String|日志的原始消息，例如，设有logger mylogger，"name"的值是"AUB"，对于 mylogger.info("Hello {}",name); "Hello {}"就是原始消息

formatedMessage|String|日志被各式话的消息，例如，设有logger mylogger，"name"的值是"AUB"，对于 mylogger.info("Hello {}",name); "Hello Aub"就是格式化后的消息

logger|String|logger名

loggerContext|LoggerContextVO|日志所属的logger上下文

level|int|级别对应的整数值，所以 level > INFO 是正确的表达式

timeStamp|long|创建日志的时间戳

marker|Marker|与日志请求相关联的Marker对象，注意“Marker”有可能为null，所以你要确保它不能是null

mdc|Map|包含创建日志期间的MDC所有值得map。访问方法是： mdc.get("myKey") 。mdc.get()返回的是Object不是String，要想调用String的方法就要强转，例如，((String) mdc.get("k")).contains("val") .MDC可能为null，调用时注意

throwable|java.lang.Throwable|如果没有异常与日志关联"throwable" 变量为 null. 不幸的是， "throwable" 不能被序列化。在远程系统上永远为null，对于与位置无关的表达式请使用下面的变量throwableProxy

throwableProxy|IThrowableProxy|与日志事件关联的异常代理。如果没有异常与日志事件关联，则变量"throwableProxy" 为 null. 当异常被关联到日志事件时，"throwableProxy" 在远程系统上不会为null

#### a.b `<matcher>`说明
`<matcher>`是`<evaluator>`的子标签,尽管可以使用String类的matches()方法进行模式匹配，但会导致每次调用过滤器时都会创建一个新的Pattern对象，为了消除这种开销，可以预定义一个或多个matcher对象，定以后就可以在求值表达式中重复引用.
`<matcher>`中包含两个子标签，一个是`<name>`，用于定义matcher的名字，求值表达式中使用这个名字来引用matcher；另一个是`<regex>`，用于配置匹配条件
```xml
<filter class="ch.qos.logback.core.filter.EvaluatorFilter">
    <evaluator>  
        <matcher>
          <Name>odd</Name>
          <!-- filter out odd numbered statements -->
          <regex>statement [13579]</regex>
        </matcher>
        <expression>odd.matches(formattedMessage)</expression>
    </evaluator>
    <OnMismatch>NEUTRAL</OnMismatch>
    <OnMatch>DENY</OnMatch>
</filter>
```
### b. `<encoder>`标签
[logstash-logback-encoder学习文档](https://www.jianshu.com/p/a26da0c55255)
下面是常见的几个json格式输出的encoder:
#### b.a net.logstash.logback.encoder.LogstashEncoder
```xml
<encoder class="net.logstash.logback.encoder.LogstashEncoder">
    <throwableConverter class="net.logstash.logback.stacktrace.ShortenedThrowableConverter">
        <!--正则匹配的日志信息不输出-->
        <exclude>sun\.reflect\..*\.invoke.*</exclude>
        <exclude>net\.sf\.cglib\.proxy\.MethodProxy\.invoke</exclude>
        <exclude>org.*</exclude>
        <exclude>*main*</exclude>
        <rootCauseFirst>true</rootCauseFirst>
    </throwableConverter>
</encoder>
```
#### b.b net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder

#### b.c net.logstash.logback.encoder.LogstashEncoderLoggingEventCompositeJsonEncoder