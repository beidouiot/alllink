# alllink
万联物联网平台
基于Java8,Spring Boot2.2.13 ,SpringMVC,AdminLTE等开发。支持物模型管理,多种设备,多种厂家,统一管理。统一设备连接管理,多协议适配(TCP,MQTT,HTTP等),灵活接入不同厂家不同协议等设备。实时数据处理,设备告警,消息通知,数据分析,数据可视化等, 能帮助您快速建立物联网相关业务系统。

平台Web端代码地址：https://github.com/beidouiot/alllink-web

---
Alllink技术栈
---
~~~mermaid
graph LR
技术栈-->A(开发语言)
A-->Java8
技术栈-->B(SpringBoot2.2.13)
技术栈-->C(微服务)
C-->Spring-Cloud-Hoxton-SR10
技术栈-->D(注册配置中心)
D-->Nacos
技术栈-->E(认证鉴权)
E-->OAuth2
技术栈-->F(分布式ID)
F-->雪花算法
技术栈-->G(分布式事务)
G-->Seata
技术栈-->H(分布式任务调度)
H-->XXL-JOB
技术栈-->I(设备消息通讯)
I-->MQTT
技术栈-->J(消息中间件)
J-->Pulsar
技术栈-->K(关系型数据库)
K-->MySQL8
技术栈-->L(非关系型数据库)
L-->Influxdb&Redis
技术栈-->M(链路监控)
M-->Skyworking
技术栈-->N(日志采集分析)
N-->ELK
~~~
---
Alllink模块
---
~~~mermaid
graph LR
Alllink-->A(api-gateway-web)
A-->北向微服务网关
Alllink-->B(commoon)
B-->通用组件模块
Alllink-->C(dao)
C-->数据持久化及业务逻辑模块
Alllink-->D(device-center)
D-->设备中心
Alllink-->E(feign)
E-->外部调用接口模块
Alllink-->F(oauth2-auth-server-center)
F-->OAuth2认证中心
Alllink-->G(user-center)
G-->用户中心
~~~
---
Alllink功能模块
---
|功能1 |功能1.1 |
| :----: | :-----: |
| 行业类别管理 |  |
| 产品类别管理 |  |
| 产品管理 |  |
| 标准物模型管理 | 属性模型管理 |
| 标准物模型管理 | 事件模型管理 |
| 标准物模型管理 | 指令模型管理 |
| 产品物模型管理 | 属性模型管理 |
| 产品物模型管理 | 事件模型管理 |
| 产品物模型管理 | 指令模型管理 |
| 设备管理 |  |
| 设备数据采集 | MQTT协议采集物模型数据 |
| 设备数据采集 | HTTP协议采集物模型数据 |
| 设备固件OTA升级 |  |
| 设备操作 | 设备指令下发 |
| 设备操作 | 设备属性下发 |
| 设备操作 | 设备事件下发 |
| 租户管理 |  |
| 客户管理 |  |
| 用户管理 |  |
| 租户行业管理 |  |
| 园区管理 |  |
| 职位管理 |  |
| 部门管理 |  |
| 菜单管理 |  |
| 角色管理 |  |
| 权限管理 |  |
| 设备状态 |  |
| 设备故障分析 |  |
| 设备智能预警 |  |
| 产品智能分析 |  |
| 设备活跃度分析 |  |
| 设备操作分析 |  |
| 场景联动管理 |  |
| 设备SN码管理 |  |
| 设备注册管理 |  |
| 设备工况分析 |  |
| 工单管理 |  |
| 运维管理 |  |
