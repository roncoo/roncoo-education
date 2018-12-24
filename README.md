# 领课在线教育系统

### 项目介绍
领课在线教育系统是基于领课团队多年的在线教育开发和运营经验的产品，打造一个全行业都适用的在线教育系统。

### 功能介绍
* 权限管理功能，多角色多用户自定义配置
* 系统配置功能，自定义进行站点配置及第三方参数配置
* 讲师管理功能，讲师申请入驻，后台具有审核功能
* 课程管理功能，讲师管理自有课程，后台具有审核功能
* 用户登录功能，同一时间只允许同一个账号在同一个地方登录，防止账号共享
* 广告管理功能，后台自定义广告设置，增加营销效果
* 支付功能，系统无缝集成了龙果支付

### 技术选型
| 核心技术框架                           | 注册中心                                  | 服务网关                                   | 负载均衡                                     |
| ----------------- | ----------------- | ----------------- | ------------------ | 
| Spring Cloud      | Eureka            | Zuul              | Feign + Ribbon     | 
| 断路器                                      | 持久层                                       | 连接池                                      | 缓存                                            | 
| Hystrix           | Mybatis           | Druid             | Redis              | 
| 模板引擎                                  | 日志框架                                   | 常用工具                                   | 版本维护                                     | 
| Freemarker        | Logback           | Hutool            | Spring IO Platform | 

### 加速maven构建
在maven的settings.xml 文件里配置mirrors的子节点，添加如下mirror
```
<mirror>
    <id>nexus-aliyun</id>
    <mirrorOf>*</mirrorOf>
    <name>Nexus aliyun</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
</mirror>
```

### Lombok使用
Lombok是一个可以通过简单的注解形式来帮助我们简化消除一些必须有但显得很臃肿的Java代码的工具，通过使用对应的注解，可以在编译源码的时候生成对应的方法。
[官方地址：https://projectlombok.org/](https://projectlombok.org/)

#### 1. Eclipse使用方法
把lombok.jar放入Eclipse的根目录，在eclipse.ini配置文件的最后加上
```
-javaagent:lombok.jar 
```

#### 2. IntelliJ IDEA使用方法
安装插件，如图所示

![流程图](/doc/images/lombok1.png)

#### 官方QQ群
<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=7d1b7f1a78715320d09ed841c66d6bf0fa7b9105964cc327e9483e459b46abd4">826617734 <img border="0" src="https://pub.idqqimg.com/wpa/images/group.png" alt="在线教育系统-领课" title="在线教育系统-领课"></a>

## 项目推荐
##### roncoo-recharge：[码云地址](https://gitee.com/roncoocom/roncoo-recharge) | [Github地址](https://github.com/roncoo/roncoo-recharge)
##### roncoo-jui-springboot：[码云地址](https://gitee.com/roncoocom/roncoo-jui-springboot) | [Github地址](https://github.com/roncoo/roncoo-jui-springboot)