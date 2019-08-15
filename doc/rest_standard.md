# 接口规范
- 按照restful接口设计规范 
    - GET （SELECT）：从服务器检索特定资源，或资源列表。
    
    - POST （CREATE）：在服务器上创建一个新的资源。
    
    - PUT （UPDATE）：更新服务器上的资源，提供整个资源。
    
    - PATCH （UPDATE）：更新服务器上的资源，仅提供更改的属性。
    
    - DELETE （DELETE）：从服务器删除资源。

- 接口尽量使用名词，禁止使用动词，下面是一些例子

    - GET         /zoos：列出所有动物园
    - POST        /zoos：新建一个动物园
    - GET         /zoos/{id}：获取某个指定动物园的信息
    - PUT         /zoos/{id}：更新某个指定动物园的信息（提供该动物园的全部信息）
    - PATCH       /zoos/{id}：更新某个指定动物园的信息（提供该动物园的部分信息）
    - DELETE      /zoos/{id}：删除某个动物园
    - GET         /zoos/{id}/animals：列出某个指定动物园的所有动物
    - DELETE      /zoos/{zId}/animals/{aId}：删除某个指定动物园的指定动物
