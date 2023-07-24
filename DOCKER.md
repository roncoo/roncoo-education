#### 端口设置
| service                          | port | 
|----------------------------------|------|
| roncoo-education-gateway         | 8180 |
| roncoo-education-system          | 8184 |
| roncoo-education-user            | 8186 |
| roncoo-education-course          | 8188 |
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

