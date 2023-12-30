#### 端口设置
| service                          | port | 
|----------------------------------|------|
| roncoo-education-gateway         | 7700 |
| roncoo-education-system          | 7710 |
| roncoo-education-user            | 7720 |
| roncoo-education-course          | 7730 |
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

