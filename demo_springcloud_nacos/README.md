# spring-cloud-alibaba demo
## 1. 配置中心
使用配置中心功能需要在项目中添加配置文件`bootstrap.properties`,且最简配置如下:
```xml
# 这里的应用名对应 Nacos Config 中的 Data ID，该配置会被其他配置覆盖,例如本例中,会被application.yml中的配置覆盖为alibaba-nacos-discovery-server
#spring.application.name=alibaba-nacos-config-server
# 指定配置名为 book-crud-sca-provider-config 的配置文件,下面配置是后缀, 此项配置可无
spring.cloud.nacos.config.file-extension=yaml
# 注册中心 的地址
spring.cloud.nacos.config.server-addr=nacos:8848
```
本例中的配置对应的nacos的配置的`Data ID`为`alibaba-nacos-discovery-server.yaml`

具体代码参见类`ConfigController`
其中注解`@RefreshScope`表示该类下的配置参数可实时刷新
