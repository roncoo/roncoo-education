#### 端口设置
|              service              |       port      | 
| --------------------------------- | --------------- |
| roncoo-education-app-gateway      |     5840        |
| roncoo-education-app-job          |     5820        |
| roncoo-education-app-sba          |     5800        |
| roncoo-education-system           |     5710        |
| roncoo-education-user             |     5720        |
| roncoo-education-course           |     5730        |
-------------------------------------------------------

#### 如何进行docker部署
```
1. maven打包
# mvn clean package

2. 利用docker-compose构建
# docker-compose build

3. 启动docker-compose
# docker-compose up -d
```
> 特别说明：请提前配置好MySQL、Redis、Elasticsearch  

