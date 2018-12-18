# 龙果充值平台

### 项目介绍
1. 具备话费充值、流量充值、话费卡兑换功能；
2. 可以拓展其他充值兑换业务，比如虚拟币充值；
3. 也适用于支付、鉴权等业务功能的拓展；

### 项目特色
* 使用Spring Boot2.0，集成Shiro和freemark及其标签的使用
* 接口和实现类进行代码分离，让接口对接和维护更简单

### 技术选型
1. 核心技术框架：Spring Boot
2. 数据库连接池：Druid
3. 持久层的框架：MyBatis
4. 权限管理框架：Shiro
5. 后台页面框架：B-JUI

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

### 项目结构

```
├─roncoo-recharge -----------------------------父项目，公共依赖
│  │
│  ├─roncoo-recharge-api
│  │  │
│  │  ├─roncoo-recharge-api-core --------------上游接口的封装：对接上游，只需要对其接口进行实现
│  │  │
│  │  ├─roncoo-recharge-api-longguo -----------上游接口实现demo1
│  │  │
│  │  └─roncoo-recharge-api-roncoo ------------上游接口实现demo2
│  │
│  ├─roncoo-recharge-boss ---------------------运营管理后台
│  │
│  ├─roncoo-recharge-common -------------------工程共用模块
│  │
│  ├─roncoo-recharge-crontab ------------------定时任务工程
│  │
│  ├─roncoo-recharge-gateway ------------------网关工程：核心流程请看帮助文档
│  │
│  ├─roncoo-recharge-util ---------------------工具类
│  │
│  ├─doc
│  │  │
│  │  ├─images --------------------------------项目演示截图
│  │  │
│  │  ├─lombok.jar ----------------------------Eclipse使用，放到Eclipse的根目录即可
│  │  │
└──└──└─rc_recharge.sql------------------------项目SQL脚本：带有demo数据
```

### 管理后台
* 账户: fengyw 密码: roncoo 

### 帮助文档
![流程图](/doc/images/90.png)

### 官方QQ群
* QQ3群: 738785494
* QQ2群: 601146630 (满)
* QQ1群: 213097382 (满)

### 项目截图
![话费卡兑换](/doc/images/1.png)
![流量充值](/doc/images/2.png)
![话费充值](/doc/images/3.png)
![菜单管理](/doc/images/4.png)
![角色管理](/doc/images/5.png)
![用户管理](/doc/images/6.png)
![商品管理](/doc/images/7.png)
![商品管理](/doc/images/8.png)

## 项目推荐
### roncoo-recharge：[码云地址](https://gitee.com/roncoocom/roncoo-recharge) | [Github地址](https://github.com/roncoo/roncoo-recharge)
### roncoo-jui-springboot：[码云地址](https://gitee.com/roncoocom/roncoo-jui-springboot) | [Github地址](https://github.com/roncoo/roncoo-jui-springboot)