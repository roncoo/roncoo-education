### 版本升级日志
---

###### v13.0.0-RELEASE

1. 大版本升级到：spring boot 2.6.3，ribbon改用为loadbalancer
2. 增加分布式事务组件，alibaba seata，用户课程购买流程，保证全局事务
3. 工程架构优化调整，移除app-job工程，单独使用roncoo-job(和官方的xxl-job-admin完全一致);
4. 增加点播私有化视频播放功能，使其能够本地私有化播放视频
5. 增加文档课时功能，课时可以选择文档(word、excel、ppt、pdf等)，不限于音视频
6. 增加对象存储类型支持：阿里云OSS
7. 优化打包流程，修复打包配置错误bug

---

###### v12.0.0-RELEASE

1. 增加课程收藏和课程评论功能
2. 增加登录日志功能，统计注册和登录、课程用户学习统计等功能
3. 增加用户登录token的过期时间控制
4. 新增保利威视频云初始化功能，不再需要登录其后台进行手动设置
5. 短信通道增加领课云短信平台的支持
6. 优化MinIO的配置和修改获取方式
7. 解决Admin端长时间登录，出现菜单过期问题
8. 优化网关全局错误处理方式
9. bug修复：对上传的图片进行类型限制

---

###### v11.0.0-RELEASE

1. 业务流程重新设计，优化现在的业务流程(课程由管理员维护，不再依赖讲师)
2. 对接官方支付渠道(包括支付宝和微信)，解耦龙果支付
3. 定时任务改用xxl-job，解决分布式调度问题
4. 重构后端工程项目，进行升级和优化（特别提醒：不兼容之前的版本）
5. Admin工程架构升级和页面重构优化(由vue2.0升级为vue3.0)
6. Web工程代码重构和页面优化

---

###### v10.0.0-RELEASE

1. 升级spring boot主版本，多个组件版本升级
2. 全新架构，重构项目结构
3. 增加视频类型和存储类型的说明，增加minio支持
4. 切换网关组件为Spring CLoud Gateway
5. 增加nacos_cofig.zip配置，nacos可以一键导入
6. 将file_storage的表file_type修改为file_classify
7. 网关增加聚合接口文档，更方便接口文档的查看和对接
8. 增加搜索功能，管理后台一键导入es功能

---

###### v9.0.0-RELEASE

1. app-job的启动bug修复
2. 主分支切换为alibaba分支
3. 修复升级后的freemark页面不显示的bug
4. 修复docker-compose在alibaba分支下的bug
5. 修复nacos_config.zip的配置名称错误问题
6. 移除多余的配置，上传配置调整
7. 升级swaager的UI，更换为knife4j-spring-ui
8. 优化docker-compose.yml

---

###### v8.0.0-RELEASE

1. 升级FastDFS的版本，已修复加载出现的bug
2. 升级swagger2的jar版本
3. 升级spring boot admin，精简server-sba的jar，移除多余的jar
4. 调整日志输出位置，整合日志web输出
5. 修复类型不匹配的bug
6. 修复上传视频校验bug
7. 增加docker部署方式
8. 升级核心框架版本，并修复升级版本导致的问题
9. 修改子项目名称，roncoo-education-server-*修改为roncoo-education-app-*，更符合业务特性

---

###### v7.0.0-RELEASE

1. 整理优化演示数据
2. 修复课程简介为空不显示课程信息
3. roncoo-education-gateway修改为roncoo-education-server-gateway
4. roncoo-education-job修改为roncoo-education-server-job
5. roncoo-education-server-admin修改为roncoo-education-server-sba
6. 修改网关过滤器判断失败，继续执行的bug
7. 修改网关默认的context-path为/api，使前后端的接口一致
8. 增加文件存储方式：FastDFS

---

###### v6.0.0-RELEASE -- 2020.01.28

1. 修改网关配置开发环境地址与测试环境地址一致
2. 移除roncoo-druid，替换为alibaba-druid
3. 移除server-zipkin, 官方建议单独部署
4. 拦截器优化权限校验
5. 移除多余的图片

---

###### v5.0.0-RELEASE -- 2019.10.28

1. 移除roncoo-education-web-boss工程，替换为roncoo-education-admin，单独维护
2. 架构调整，更接近商业版架构（移除roncoo-education-course-common、roncoo-education-system-common、roncoo-education-user-common3个工程）
3. 修改bug和漏洞若干

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
