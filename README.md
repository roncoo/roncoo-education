# 领课在线教育开源系统

### 项目介绍
领课在线教育开源系统是基于领课团队多年的在线教育开发和运营经验的产品

### 功能介绍
* 具有录播功能，直播功能即将上线
* 讲师入驻功能，讲师自主管理课程
* 课程管理功能，讲师上传的课程后台有审核功能
* 单用户登录功能，同一时间只允许同一个账号在同一个地方登录，防止账号共享
* 支付功能，系统无缝集成了龙果支付面

### 技术选
* 核心技术框架：Spring Cloud

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

## 项目推荐
### roncoo-recharge：[码云地址](https://gitee.com/roncoocom/roncoo-recharge) | [Github地址](https://github.com/roncoo/roncoo-recharge)
### roncoo-jui-springboot：[码云地址](https://gitee.com/roncoocom/roncoo-jui-springboot) | [Github地址](https://github.com/roncoo/roncoo-jui-springboot)