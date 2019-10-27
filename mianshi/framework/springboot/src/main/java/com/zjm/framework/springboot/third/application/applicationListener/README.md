# spring framework事件/监听编程模式

* spring应用事件
    * 普通应用事件：ApplicationEvent
    * 应用上下文事件：ApplicationContextEvent
* spring应用监听器
    * 接口变成模型：ApplicationListener
    * 注解变成模型：@EventListener
* spring应用事件广播器
    * 接口：ApplicationEventMulticaster
    * 实现类：SimpleApplicationEventMulticaster
        * 执行模型：同步或异步