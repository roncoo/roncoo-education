### 版本升级日志

 ---
###### v4.0.0-RELEASE -- 没发布
1. 修改sonar检测出来的bug和漏洞
2. 调整接口url风格，修改网关映射规则

 ---
###### v3.0.0-RELEASE -- 2019.05.16
1. 修复视频上传不同步问题
2. 修复部分微服务日志不打印的问题
3. 修改配置中心默认获取方式为本地配置，不再依赖于项目：roncoo-education-config
4. 修改工程名称，更符合业务特性，roncoo-education-crontab-plan修改为：roncoo-education-job，roncoo-education-gateway-api修改为：roncoo-education-gateway
5. 减少ElasticSearch的强依赖，不配置也能启动课程微服务
6. 增加sonar插件，使代码更健壮

 ---
###### v2.0.0-RELEASE -- 2019.03.18
1. 集成了链路监控：roncoo-education-server-zipkin
2. 新增站内信功能
3. 新增推荐课程功能
4. 新增发送验证码功能
5. 新增后台密码修改功能
6. 新增图片、文档、视频可本地存储功能
7. 升级swagger-models版本， 更换Swagger的UI为swagger-bootstrap-ui
8. 优化环境配置，更方便部署

 ---
###### 1.0.0 -- 2019.01.04
初始化
