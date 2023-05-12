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
| 功能1 | 功能1.1 |
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

---
线上交流
---
QQ群号：561606431  

群二维码：  

![avatar][doge]
[doge]:data:image/png;base64,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uc99zuwPlsO8kc/VV1/dq+yynTp79uwwY8YMVzKUve666/aK//Wvfz289tprAYTFCMhz8eLF7b/xG9oKkinUG4RRp556aiafYcOG5crW6mPl/8Ybb2QvMHUi/MJLSr/97W/DqFGjcuW9+eabat8wEuSOvpFptTa6hNjwSP0WiJBr3Fl4RwGDEPR506ZNy5jHwNuJQXzRRRdljGQcmABhUfjMZz6TgRhpCBQCD4Pg7LPPDjfccEN4z3ve07WuBUDArXn99df3GrAsBAMfdQPP5mOPPRbuu+++bKBCBnh4B20FtysnFuT54x//OICLlUAEKOM4cQPK5o/4J5xwQjj99NN7gZr5AmwA80knnaS2C3XnpCknBuRBuRx55JHt5+c0oXsm1K51Vh9l1C+ByNn00ksvdYkFYITW4KwvNVqcX/wNAwHa9Mwzz8yAh///05/+1KvMugPB0lQWEHEdUNOqaMO4cePaAAUQMTFhdaDJDPHxxp8EupU/4n/1q1/N3g489thjwwsvvJDTtgDZVVddlb19CCBioiyzAqCWj4WNieTOO+/MaXzXAOgHkfolEOPlC2dUaDAuywgsNI4dik7E7IylDjRKPBNrANWWahMnTsy04bx587IBce6554apU6cGPDwJTcTl2vPPP68ux4rGgxeI8VIwBj8GPTUky+Dym3/vt99+GWi+8Y1vZJrswAMPDI888kgYP358pjEBrDL5UyPG+QC4YLM+8cQTs0nv/e9/fyarIo1YJA/2H1izqeGRH/qTf3fSpv0Ae7kq9lsgakCh5tOAGC+XqBkALAzA2267TV1ucn8oNSjKBhDxWi+WlFyqVgWitt9FfQl8aOOipSOX6BKIoOLfbbfdwqOPPpotCwkITEJcygKA999/f9h2221VjRgPepl/vDTFJIj/MMmBTR0T0YMPPhi+853vBDyGEwNR2xsWaTruB7HsLtpOsM/7G/BkffstELVZE43TAKotHRmvqCOLlqssA0DEXlLb25QdFNpARPnQXNgfEYixxjrrrLPC5Zdfrh6WgEIeb05g/zZ//vyA568nT56caRO0G8ADUADUG2+8Mft3s802y2lEK3/IjJoVZTz88MO9DsigzcCgHe8RudQHxX68z+ZEFE88XhkiLfKTB3fe9E2J12+ByE6N92wejdjptJKHNRjA8SyMR1kwuHHYgSUXBjL/rQtI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
