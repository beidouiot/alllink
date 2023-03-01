# alllink
万联物联网平台
基于Java8,Spring Boot2.2.13 ,SpringMVC,AdminLTE等开发。支持物模型管理,多种设备,多种厂家,统一管理。统一设备连接管理,多协议适配(TCP,MQTT,HTTP等),灵活接入不同厂家不同协议等设备。实时数据处理,设备告警,消息通知,数据分析,数据可视化等, 能帮助您快速建立物联网相关业务系统。

平台Web端代码地址：https://github.com/beidouiot/alllink-web
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
