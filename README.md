# 领课网络-在线教育-开源系统

### 项目介绍
1. 基于领课多年的在线教育经验
2. 具有录播，直播功能

### 项目特色
* 使用Spring Boot2.0，集成Shiro和freemark及其标签的使用
* 接口和实现类进行代码分离，让接口对接和维护更简单

### 技术选型
1. 核心技术框架：Spring Cloud
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

## 项目推荐
### roncoo-recharge：[码云地址](https://gitee.com/roncoocom/roncoo-recharge) | [Github地址](https://github.com/roncoo/roncoo-recharge)
### roncoo-jui-springboot：[码云地址](https://gitee.com/roncoocom/roncoo-jui-springboot) | [Github地址](https://github.com/roncoo/roncoo-jui-springboot)