# 领课教育 - 领课网络在线教育系统

### 项目介绍（如果对你有用，请给个star！）
> 领课教育系统（roncoo-education）是基于领课网络多年的在线教育平台开发和运营经验打造出来的产品，致力于打造一个全行业都适用的分布式在线教育系统。基于MIT协议，无论是个人还是公司都可以利用该系统快速搭建一个属于自己的在线教育平台。系统前台采用前后端分离模式，vue.js为核心框架，后台采用Spring Cloud为核心框架，Eureka为注册中心，Config为配置中心，SBA为应用监控，Zipkin为链路监控。

### 前台功能介绍
* 页面展示，课程程的展示和购买功能、讲师招募等功能
* 个人中心，具有个人信息设置、密码修改、订单管理、学习记录等功能
* 讲师中心，讲师信息管理、课程管理（课程的添加、修改）、收益管理等功能

### 后台功能介绍
* 权限管理功能，多角色多用户自定义配置
* 系统配置功能，自定义进行站点配置及第三方参数配置
* 讲师管理功能，讲师申请入驻，后台具有审核功能
* 课程管理功能，讲师管理自有课程，后台具有审核功能
* 用户登录功能，同一时间只允许同一个账号在同一个地方登录，防止账号共享
* 广告管理功能，后台自定义广告设置，增加营销效果
* 支付功能模块，系统无缝集成了龙果支付

### 相关工程
##### 前端工程（roncoo-education-web）：[码云地址](https://gitee.com/roncoocom/roncoo-education-web) | [Github地址](https://github.com/roncoo/roncoo-education-web)
##### 配置工程（roncoo-education-config）：[码云地址](https://gitee.com/roncoocom/roncoo-education-config) | [Github地址](https://github.com/roncoo/roncoo-education-config)

### 使用文档
##### [项目介绍](https://blog.roncoo.com/article/1105321762337357826) | [部署文档](https://blog.roncoo.com/article/1103554925858197505) | [常见问题](https://blog.roncoo.com/article/1105309620724858882)

### 演示地址
##### 前端演示地址：[领课教育](http://edu.os.roncoo.com/) | 后台演示地址：[管理后台](http://roncoo.vicp.net/boss/login)

### 商业合作
* 如果想定制开发，我们提供有偿服务支持
* 如果想运营平台，我们云平台提供平台入驻
* 商业合作联系QQ：513781560 
* 官网地址：[http://www.roncoo.net](http://www.roncoo.net/index.html)

### 升级说明：2.0.0 版本
* 集成了链路监控：roncoo-education-server-zipkin
* 新增站内信功能，推荐课程功能，发送验证码功能，密码修改功能
* 增加文件存储类型，修改bug若干
* 升级swagger-models版本， 更换Swagger的UI为swagger-bootstrap-ui

### TODO
* 动态更新配置(spring cloud bus + kafka + spring cloud config)
* 日志收集统计(kafka + Elasticsearch + Logstash + Kibana)

### 项目截图
![SBA](/doc/images/img_01.png)
![zipkin](/doc/images/img_02.png)

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

### 官方QQ群（加群免费获取sql脚本）
<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=7d1b7f1a78715320d09ed841c66d6bf0fa7b9105964cc327e9483e459b46abd4"><img border="0" src="https://pub.idqqimg.com/wpa/images/group.png" alt="在线教育系统-领课" title="在线教育系统-领课"> 826617734</a>

## 项目推荐
##### roncoo-recharge：[码云地址](https://gitee.com/roncoocom/roncoo-recharge) | [Github地址](https://github.com/roncoo/roncoo-recharge)
##### roncoo-jui-springboot：[码云地址](https://gitee.com/roncoocom/roncoo-jui-springboot) | [Github地址](https://github.com/roncoo/roncoo-jui-springboot)
