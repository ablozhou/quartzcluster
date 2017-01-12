# Spring boot Quartz 集群
- 采用Gradle构建
- 基于Mysql做的Quartz集群
- 可以同时同一个任务只支持一个服务器执行

# 环境
- Java 1.8
- Gradle 1.4+
- SpringBoot 1.4.3
- Spring 4.3.5
- Quartz 2.2.1+
- MySql

# 配置
- mysql数据库中新建一个库
- 将quartz.sql导入该库
- 修改 application-dev.properties中
    - server.port端口号
    - spring.datasource 配置
    - 
- 修改 quartz.properties 中
    - org.quartz.dataSource.myDS.URL地址

# 用法
- java -jar quartzcluster.jar -Dinfo
- 源码执行 gradle bootRun
- eclipse 中运行

- 可以在不同机器起两个实例或者在一台机器修改server.port为不同端口起两个实例,通过停止一个实例查看quartz任务切换.
- 访问localhost:8080可以查看加载了哪些类
- output和logs.log可以查看任务执行情况