# 第二章：走向自动装配

## spring 装配有四种方式

* Spring模式注解装配@Profile("online") + @Component，当profile=online时加载
* Spring模块装配@EnableXXX + @Import(MyEnableSelector.class) + 实现ImportSelector接口，实现同时返回N个bean
* Spring条件装配@ConditionalOnXXX + @Conditional(MyCondition.class) + Condition接口，返回true则加载该bean
* Spring自动加载autoConfiguration