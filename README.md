# 领课教育-领课网络在线教育系统

### 项目介绍
领课教育是基于领课团队多年的在线教育开发和运营经验的产品，打造一个全行业都适用的在线教育系统。

##### 如果对你有用，请给个star！

### 相关链接
##### 前端工程（roncoo-education-web）：[码云地址](https://gitee.com/roncoocom/roncoo-education-web) | [Github地址](https://github.com/roncoo/roncoo-education-web)
##### 配置工程（roncoo-education-config）：[码云地址](https://gitee.com/roncoocom/roncoo-education-config) | [Github地址](https://github.com/roncoo/roncoo-education-config)
##### 使用文档：[码云地址](https://gitee.com/roncoocom/roncoo-education/wikis/Home) | [Github地址](https://github.com/roncoo/roncoo-education/wiki)
#### 前端演示地址：[领课教育](http://edu.os.roncoo.com/)
#### 后台演示地址：[管理后台](http://roncoo.vicp.net/boss/login)

### 功能介绍
* 权限管理功能，多角色多用户自定义配置
* 系统配置功能，自定义进行站点配置及第三方参数配置
* 讲师管理功能，讲师申请入驻，后台具有审核功能
* 课程管理功能，讲师管理自有课程，后台具有审核功能
* 用户登录功能，同一时间只允许同一个账号在同一个地方登录，防止账号共享
* 广告管理功能，后台自定义广告设置，增加营销效果
* 支付功能，系统无缝集成了龙果支付

### TODO
* 动态更新配置(spring cloud bus + kafka + spring cloud config)
* 日志收集统计(kafka + Elasticsearch + Logstash + Kibana)
* 链路追踪监控(spring cloud sleuth + kafka + zipkin)

### 技术选型
| 核心技术框架                           | 注册中心                                  | 服务网关                                   | 负载均衡                                     |
| ----------------- | ----------------- | ----------------- | ------------------ | 
| Spring Cloud      | Eureka            | Zuul              | Feign + Ribbon     | 
| 断路器                                      | 持久层                                       | 连接池                                      | 缓存                                            | 
| Hystrix           | Mybatis           | Druid             | Redis              | 
| 模板引擎                                  | 日志框架                                   | 常用工具                                   | 版本维护                                     | 
| Freemarker        | Logback           | Hutool            | Spring IO Platform | 

### 流程图说明
* 系统架构图
![系统架构图](/doc/images/01.jpg)

* 课程播放流程
![课程播放流程](/doc/images/course.png)

* 播放鉴权流程
![播放鉴权流程](/doc/images/course_callback.png)

* 课程下单流程
![课程下单流程](/doc/images/pay.png)

* 下单回调流程
![下单回调流程](/doc/images/pay_callback.png)

### 项目结构

```
├─roncoo-education -----------------------------父项目，公共依赖
│  │
│  ├─roncoo-education-course -------------------课程模块，包括订单模块
│  │  │
│  │  ├─roncoo-education-course-common ---------共用工程
│  │  │
│  │  ├─roncoo-education-course-feign ----------接口工程，供其他工程模块使用
│  │  │
│  │  └─roncoo-education-course-service --------服务工程，其他接口服务
│  │
│  ├─roncoo-education-crontab-plan -------------定时任务，处理过期订单和统计等
│  │
│  ├─roncoo-education-gateway-api --------------网关工程
│  │
│  ├─roncoo-education-server-admin -------------监控中心
│  │
│  ├─roncoo-education-server-config ------------配置中心
│  │
│  ├─roncoo-education-server-eureka ------------注册中心
│  │
│  ├─roncoo-education-system -------------------系统基础工程
│  │  │
│  │  ├─roncoo-education-system-common ---------共用工程
│  │  │
│  │  ├─roncoo-education-system-feign ----------接口工程，供其他工程模块使用
│  │  │
│  │  └─roncoo-education-system-service --------服务工程，其他接口服务
│  │
│  ├─roncoo-education-user ---------------------用户工程
│  │  │
│  │  ├─roncoo-education-user-common -----------共用工程
│  │  │
│  │  ├─roncoo-education-user-feign ------------接口工程，供其他工程模块使用
│  │  │
│  │  └─roncoo-education-user-service ----------服务工程，其他接口服务
│  │
│  ├─roncoo-education-web-boss -----------------管理后台工程
│  │
│  ├─doc
│  │  │
│  │  ├─images --------------------------------项目演示截图
│  │  │
│  │  ├─lombok.jar ----------------------------Eclipse使用，放到Eclipse的根目录即可
│  │  │
└──└──└─*.sql----------------------------------项目SQL脚本：带有demo数据
```

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

![图](/doc/images/lombok1.png)

### 官方QQ群（加群免费获取sql脚本）
<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=7d1b7f1a78715320d09ed841c66d6bf0fa7b9105964cc327e9483e459b46abd4"><img border="0" src="https://pub.idqqimg.com/wpa/images/group.png" alt="在线教育系统-领课" title="在线教育系统-领课"> 826617734</a>

## 项目推荐
##### roncoo-recharge：[码云地址](https://gitee.com/roncoocom/roncoo-recharge) | [Github地址](https://github.com/roncoo/roncoo-recharge)
##### roncoo-jui-springboot：[码云地址](https://gitee.com/roncoocom/roncoo-jui-springboot) | [Github地址](https://github.com/roncoo/roncoo-jui-springboot)