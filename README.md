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
