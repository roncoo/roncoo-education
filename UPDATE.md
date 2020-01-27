### 版本升级日志
---
###### v6.1.0-SNAPSHOT -- 接下来
1. 增加视频、图片、文件本地存储功能
2. 增加视频本地播放功能

###### v6.0.0-RELEASE -- 2020.01.28
1. 修改网关配置开发环境地址与测试环境地址一致
2. 移除roncoo-druid，替换为alibaba-druid
3. 移除server-zipkin, 官方建议单独部署
4. 拦截器优化权限校验
5. 移除多余的图片

---
###### v5.0.0-RELEASE -- 2019.10.28
1. 移除roncoo-education-web-boss工程，替换为roncoo-education-admin，单独维护
1. 架构调整，更接近商业版架构（移除roncoo-education-course-common、roncoo-education-system-common、
   roncoo-education-user-common3个工程）
2. 修改bug和漏洞若干

 ---
###### v4.0.0-RELEASE -- 2019.08.16
1. 管理后台使用Vue实现前后端分离
2. 增加roncoo-education-boss工程，推荐使用
3. 保留roncoo-education-web-boss，下一版本将移除
4. 修改sonar检测出来的bug和漏洞
5. 调整接口url风格，修改网关映射规则

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
