#### 端口设置
|                服务名称             |     服务端口     | 
| --------------------------------- | --------------- |
| roncoo-education-server-eureka    |  5761           |
| roncoo-education-server-confg     |  5741           |
| roncoo-education-server-sba       |  5721           |
| roncoo-education-server-job       |  5820           |
| roncoo-education-server-gateway   |  5840           |
| roncoo-education-system           |  5730           |
| roncoo-education-user             |  5720           |
| roncoo-education-course           |  5710           |
-------------------------------------------------------

#### 如何进行docker部署
```
1. maven打包
# mvn clean package
2. 利用docker-compose构建
# docker-compose build
3. 启动
# docker-compose up -d
```
> 特别说明：请提前配置好MySQL、Redis、Elasticsearch  
> 配置文件在 **./roncoo-education-server-config/src/main/resources/config**
