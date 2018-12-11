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
        使用Futrue<>返回值进行回调处理
    
    自定义线程池：
        配置参照:org.xjc.demo.configs.TaskPollConfig
        使用时，只需要在@Async中的value添加Bean的名字
