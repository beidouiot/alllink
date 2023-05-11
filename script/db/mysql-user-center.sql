CREATE DATABASE `iot_user_center` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

-- iot_user_center.customer definition

CREATE TABLE `customer` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) NOT NULL COMMENT '客户名称',
  `descr` varchar(500) DEFAULT NULL COMMENT '客户描述',
  `city` varchar(1000) DEFAULT NULL COMMENT '所在城市，省市区信息，采用json格式存储',
  `addr` varchar(200) DEFAULT NULL COMMENT '客户详细地址',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `zip_code` varchar(6) DEFAULT NULL COMMENT '邮编',
  `tenant_id` bigint DEFAULT NULL COMMENT '所属租户id',
  `system_code` varchar(50) DEFAULT NULL COMMENT '所属系统编号',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- iot_user_center.department definition

CREATE TABLE `department` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) NOT NULL COMMENT '部门/组织名称',
  `code` varchar(50) NOT NULL COMMENT '部门/组织编号，不能重复，由系统自动生成',
  `descr` varchar(500) DEFAULT NULL COMMENT '部门/组织描述',
  `sort_no` int NOT NULL COMMENT '排序号',
  `dept_type` varchar(20) NOT NULL COMMENT '部门/组织类型',
  `pid` bigint DEFAULT NULL COMMENT '父id',
  `tenant_id` bigint DEFAULT NULL COMMENT '所属租户id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  `customer_id` bigint DEFAULT NULL COMMENT '所属客户id',
  `system_code` varchar(50) DEFAULT NULL COMMENT '所属系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- iot_user_center.dept_user definition

CREATE TABLE `dept_user` (
  `user_id` bigint NOT NULL COMMENT '用户id',
  `dept_id` bigint NOT NULL COMMENT '部门id',
  PRIMARY KEY (`user_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- iot_user_center.industry definition

CREATE TABLE `industry` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) NOT NULL COMMENT '行业名称',
  `code` varchar(100) NOT NULL COMMENT '行业编号',
  `descr` varchar(200) DEFAULT NULL COMMENT '行业描述',
  `sort_no` int NOT NULL COMMENT '排序号',
  `pid` bigint DEFAULT NULL COMMENT '父id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- iot_user_center.menu definition

CREATE TABLE `menu` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) NOT NULL COMMENT '菜单/功能名称',
  `code` varchar(50) NOT NULL COMMENT '菜单/功能编号，不能重复，由系统自动生成',
  `addr` varchar(200) DEFAULT NULL COMMENT '菜单/功能地址',
  `icon` varchar(200) DEFAULT NULL COMMENT '菜单/功能图标',
  `type` varchar(20) NOT NULL COMMENT '菜单/功能类型，枚举型（web菜单、tab、按钮，app菜单）',
  `sort_no` int NOT NULL COMMENT '排序号',
  `pid` bigint DEFAULT NULL COMMENT '父id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  `leaf_flag` bit(1) NOT NULL COMMENT '是否为叶节点',
  `level` int NOT NULL COMMENT '所在树结构层数',
  `has_button` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有按钮',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- iot_user_center.oauth_client_details definition

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `updated_date` datetime NOT NULL,
  `created_by` varchar(100) NOT NULL,
  `updated_by` varchar(100) NOT NULL,
  `delete_flag` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- iot_user_center.park definition

CREATE TABLE `park` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(200) NOT NULL COMMENT '园区/小区名称',
  `type` varchar(20) NOT NULL COMMENT '园区/小区节点类型，枚举（园区/小区、楼栋、单元、楼层、房间）',
  `sort_no` varchar(20) DEFAULT NULL COMMENT '节点排序号',
  `descr` varchar(200) DEFAULT NULL COMMENT '节点描述',
  `pid` bigint DEFAULT NULL COMMENT '父id',
  `tenant_id` bigint DEFAULT NULL COMMENT '所属租户id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  `customer_id` bigint DEFAULT NULL COMMENT '所属客户id',
  `system_code` varchar(50) DEFAULT NULL COMMENT '所属系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- iot_user_center.`position` definition

CREATE TABLE `position` (
  `id` bigint NOT NULL COMMENT '主键id',
  `created_by` varchar(100) NOT NULL COMMENT '创建人（用户登录名）',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  `updated_by` varchar(100) NOT NULL COMMENT '修改人（用户登录名）',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `code` varchar(10) NOT NULL COMMENT '职位编号',
  `descr` varchar(200) NOT NULL COMMENT '职位描述',
  `name` varchar(50) NOT NULL COMMENT '职位名称',
  `tenant_id` bigint DEFAULT NULL COMMENT '所属租户id',
  `customer_id` bigint DEFAULT NULL COMMENT '所属客户id',
  `system_code` varchar(50) DEFAULT NULL COMMENT '所属系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- iot_user_center.`role` definition

CREATE TABLE `role` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `code` varchar(50) NOT NULL COMMENT '角色编号，不能重复，由系统自动生成',
  `descr` varchar(200) DEFAULT NULL COMMENT '角色描述',
  `tenant_id` bigint DEFAULT NULL COMMENT '所属租户id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  `customer_id` bigint DEFAULT NULL COMMENT '所属客户id',
  `system_code` varchar(50) DEFAULT NULL COMMENT '所属系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- iot_user_center.role_menu definition

CREATE TABLE `role_menu` (
  `menu_id` bigint NOT NULL COMMENT '菜单id',
  `role_id` bigint NOT NULL COMMENT '角色id',
  PRIMARY KEY (`menu_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- iot_user_center.role_user definition

CREATE TABLE `role_user` (
  `role_id` bigint NOT NULL COMMENT '角色id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  PRIMARY KEY (`role_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- iot_user_center.tenant definition

CREATE TABLE `tenant` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) NOT NULL COMMENT '租户名称',
  `addr` varchar(500) DEFAULT NULL COMMENT '租户详细地址',
  `descr` varchar(500) DEFAULT NULL COMMENT '租户描述',
  `linkman` varchar(50) DEFAULT NULL COMMENT '租户联系人姓名',
  `email` varchar(50) DEFAULT NULL COMMENT '联系人邮箱,为选填项,邮箱不能重复',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `zip_code` varchar(6) DEFAULT NULL COMMENT '邮编',
  `industry_id` bigint DEFAULT NULL COMMENT '所属行业id',
  `city` varchar(1000) DEFAULT NULL COMMENT '所在城市，省市区信息，采用json格式存储',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '租户状态，状态有：启用（0）、禁用（1）。租户禁用后，租户下的所有用户不能登录、不能做任何操作',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  `system_code` varchar(50) DEFAULT NULL COMMENT '所属系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- iot_user_center.`user` definition

CREATE TABLE `user` (
  `id` bigint NOT NULL COMMENT '主键id',
  `username` varchar(20) NOT NULL COMMENT '登录名',
  `password` varchar(256) NOT NULL COMMENT '密码',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `mobile` varchar(11) NOT NULL COMMENT '手机号',
  `name` varchar(200) NOT NULL COMMENT '姓名',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `sex` int DEFAULT NULL COMMENT '性别,枚举型（1男、2女、3保密）',
  `status` bit(1) NOT NULL COMMENT '状态，状态值：启用（0），禁用（1）',
  `user_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户类型，枚举型（平台管理员、租户管理员、普通用户）',
  `head_portrait` varchar(512) DEFAULT NULL COMMENT '用户头像，存放图片文件base64后的值',
  `weixin` varchar(50) DEFAULT NULL COMMENT '微信号',
  `qq_no` varchar(50) DEFAULT NULL COMMENT 'QQ号',
  `descr` varchar(200) DEFAULT NULL COMMENT '用户描述',
  `identity_no` varchar(100) DEFAULT NULL COMMENT '证件号',
  `identity_type` varchar(100) DEFAULT NULL COMMENT '证件类型，枚举（身份证、军官证、士官证、士兵证、台胞证、护照、绿卡）',
  `tenant_id` bigint DEFAULT NULL COMMENT '所属租户id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- iot_user_center.user_position definition

CREATE TABLE `user_position` (
  `position_id` bigint NOT NULL COMMENT '职位id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  PRIMARY KEY (`position_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--insert user data
INSERT INTO iot_user_center.`user` (id,username,password,email,mobile,name,nickname,sex,status,user_type,head_portrait,weixin,qq_no,descr,identity_no,identity_type,tenant_id,create_date,updated_date,created_by,updated_by,delete_flag,opt_lock_version) VALUES
	 (106882660026023936,'admin','$2a$10$p32TGzI5iBkvVD0rDCMEGe6K/Vw0Iz7jr7lrKUrEzXJZLjqVJk/XK','admin@qq.com','18912345678','平台超级管理员','高管',3,0,'PLM_ADMIN',NULL,NULL,NULL,'平台超级管理员',NULL,NULL,NULL,'2021-03-25 18:14:08','2021-04-06 18:02:44','user','admin',0,1);

--insert role data
INSERT INTO iot_user_center.`role` (id,name,code,descr,tenant_id,create_date,updated_date,created_by,updated_by,delete_flag,opt_lock_version,customer_id,system_code) VALUES
	 (107448355229007872,'admin','-oBo1B0','平台超级管理员角色',NULL,'2021-04-01 00:05:37','2021-04-01 00:05:37','admin','admin',0,0,NULL,NULL);

--insert menu data
INSERT INTO iot_user_center.menu (id,name,code,addr,icon,`type`,sort_no,pid,create_date,updated_date,created_by,updated_by,delete_flag,opt_lock_version,leaf_flag,`level`,has_button) VALUES
	 (107613522955862016,'用户管理','0',NULL,'fa-users','MENU',1,0,'2021-04-02 19:50:53','2021-04-02 19:50:53','admin','admin',0,0,0,1,0),
	 (107613664466436096,'用户添加','0-1-1','uc/user/v1/add','fa-user-plus','BUTTON',1,147438075271708672,'2021-04-02 19:53:08','2022-06-12 12:20:22','admin','admin',0,10,1,3,0),
	 (107613788299067392,'系统管理','1',NULL,'fa-share-alt','MENU',2,0,'2021-04-02 19:55:06','2021-04-02 19:55:06','admin','admin',0,0,0,1,0),
	 (107613839504179200,'菜单管理','1-1',NULL,'fa-navicon','MENU',1,107613788299067392,'2021-04-02 19:55:55','2021-04-02 19:55:55','admin','admin',0,0,0,2,0),
	 (107613911113531392,'菜单列表','1-1-1','uc/menu/v1/menuList','fa-server','MENU',1,107613839504179200,'2021-04-02 19:57:03','2022-06-16 11:08:06','admin','admin',0,2,1,4,1),
	 (107614130884575232,'角色管理','1-2',NULL,'fa-paw','MENU',2,107613788299067392,'2021-04-02 20:00:33','2021-04-02 20:00:33','admin','admin',0,0,0,2,0),
	 (107614178373533696,'角色添加','1-2-1-1','uc/role/v1/add','fa-pencil','BUTTON',1,147376568370462720,'2021-04-02 20:01:18','2021-04-02 20:01:18','admin','admin',0,0,1,4,0),
	 (107709122035580928,'角色修改','1-2-1-2','uc/role/v1/update','fa-pencil-square-o','BUTTON',2,147376568370462720,'2021-04-03 21:10:23','2021-04-03 21:10:23','admin','admin',0,0,1,4,0),
	 (107854253073104896,'角色删除','1-2-1-3','uc/role/v1/logicalDelete','fa-times','BUTTON',3,147376568370462720,'2021-04-05 11:37:11','2021-04-05 11:37:11','admin','admin',0,0,1,4,0),
	 (107855116817661952,'角色查询','1-2-1-4','uc/role/v1/findPage','fa-hourglass','BUTTON',4,147376568370462720,'2021-04-05 11:50:55','2021-04-05 11:50:55','admin','admin',0,0,1,4,0);
INSERT INTO iot_user_center.menu (id,name,code,addr,icon,`type`,sort_no,pid,create_date,updated_date,created_by,updated_by,delete_flag,opt_lock_version,leaf_flag,`level`,has_button) VALUES
	 (107893999973433344,'修改用户','0-1-2','uc/user/v1/update','fa-user','BUTTON',2,147438075271708672,'2021-04-05 22:08:57','2021-04-05 22:08:57','admin','admin',0,0,1,3,0),
	 (108335280411377664,'退出','0-2','uc/user/v1/logout','fa-sign-out','BUTTON',5,147438075271708672,'2021-04-10 19:02:54','2021-04-10 19:02:54','admin','admin',0,0,1,3,0),
	 (146828535889133568,'权限配置','1-3-1','uc/authority/v1/list','fa-times','MENU',3,146828961699069952,'2022-06-09 16:16:44','2022-06-09 16:16:44','admin','admin',0,0,1,3,1),
	 (146828961699069952,'权限管理','1-3','','fa-times','MENU',4,107613788299067392,'2022-06-09 16:23:30','2022-06-09 16:23:30','admin','admin',0,0,0,2,0),
	 (146829262708539392,'客户列表','4-1','uc/customer/v1/customerList','fa-server','MENU',1,147441480389099520,'2022-06-09 16:28:17','2022-06-16 10:48:25','admin','admin',0,1,1,2,1),
	 (147371949749698560,'查询全部菜单','1-1-1-1','uc/menu/v1/findAllTree','fa-times','BUTTON',1,107613911113531392,'2022-06-15 16:14:03','2022-06-15 16:14:03','admin','admin',0,0,1,4,0),
	 (147372140316852224,'查询用户菜单','1-1-1-2','uc/menu/v1/findUserAllTree','fa-times','BUTTON',2,107613911113531392,'2022-06-15 16:17:05','2022-06-15 16:17:05','admin','admin',0,0,1,4,0),
	 (147375951736471552,'菜单彻底删除','1-1-1-3','uc/menu/v1/realDelete','fa-times','BUTTON',3,107613911113531392,'2022-06-15 17:17:40','2022-06-15 17:17:40','admin','admin',0,0,1,4,0),
	 (147375979931631616,'菜单删除','1-1-1-4','uc/menu/v1/logicalDelete','fa-times','BUTTON',4,107613911113531392,'2022-06-15 17:18:07','2022-06-15 17:18:07','admin','admin',0,0,1,4,0),
	 (147376079486582784,'菜单增加','1-1-1-5','uc/menu/v1/add','fa-times','BUTTON',5,107613911113531392,'2022-06-15 17:19:42','2022-06-15 17:19:42','admin','admin',0,0,1,4,0);
INSERT INTO iot_user_center.menu (id,name,code,addr,icon,`type`,sort_no,pid,create_date,updated_date,created_by,updated_by,delete_flag,opt_lock_version,leaf_flag,`level`,has_button) VALUES
	 (147376106165501952,'菜单修改','1-1-1-6','uc/menu/v1/update','fa-times','BUTTON',6,107613911113531392,'2022-06-15 17:20:07','2022-06-15 17:20:07','admin','admin',0,0,1,4,0),
	 (147376228566827008,'菜单查询','1-1-1-7','uc/menu/v1/findPage','fa-times','BUTTON',7,107613911113531392,'2022-06-15 17:22:04','2022-06-15 17:22:04','admin','admin',0,0,1,4,0),
	 (147376568370462720,'角色列表','1-2-1','uc/role/v1/roleList','fa-server','MENU',1,107614130884575232,'2022-06-15 17:27:28','2022-06-16 11:07:12','admin','admin',0,1,1,3,1),
	 (147377054078205952,'配置功能','1-3-1-1','uc/authority/v1/funcList','fa-times','BUTTON',1,146828535889133568,'2022-06-15 17:35:11','2022-06-15 17:35:11','admin','admin',0,0,1,4,0),
	 (147377111413293056,'配置用户','1-3-1-2','uc/authority/v1/userList','fa-times','BUTTON',2,146828535889133568,'2022-06-15 17:36:06','2022-06-15 17:36:06','admin','admin',0,0,1,4,0),
	 (147438075271708672,'用户列表','0-1','uc/user/v1/userList','fa-server','MENU',1,107613522955862016,'2022-06-16 09:45:06','2022-06-16 11:07:26','admin','admin',0,1,1,2,1),
	 (147438637661814784,'查询所有用户','0-1-3','uc/user/v1/findAll','fa-times','BUTTON',4,147438075271708672,'2022-06-16 09:54:02','2022-06-16 09:54:02','admin','admin',0,0,1,3,0),
	 (147438763664998400,'用户查询','0-1-4','uc/user/v1/findPage','fa-times','BUTTON',5,147438075271708672,'2022-06-16 09:56:02','2022-06-16 09:56:02','admin','admin',0,0,1,3,0),
	 (147439677932044288,'查询角色功能Id','1-2-1-5','uc/role/v1/findMenuIds','fa-paw','BUTTON',6,147376568370462720,'2022-06-16 10:10:34','2022-06-16 10:10:34','admin','admin',0,0,1,4,0),
	 (147440201643327488,'角色彻底删除','1-2-1-6','uc/role/v1/realDelete','fa-paw','BUTTON',7,147376568370462720,'2022-06-16 10:18:54','2022-06-16 10:18:54','admin','admin',0,0,1,4,0);
INSERT INTO iot_user_center.menu (id,name,code,addr,icon,`type`,sort_no,pid,create_date,updated_date,created_by,updated_by,delete_flag,opt_lock_version,leaf_flag,`level`,has_button) VALUES
	 (147440717029965824,'租户管理','3','','fa-cubes','MENU',2,0,'2022-06-16 10:27:05','2022-06-16 10:30:43','admin','admin',0,1,0,1,0),
	 (147440945313349632,'租户列表','3-1','uc/tenant/v1/tenantList','fa-server','MENU',1,147440717029965824,'2022-06-16 10:30:43','2022-06-16 10:30:43','admin','admin',0,0,1,2,1),
	 (147441127200391168,'查询全部租户','3-1-1','uc/tenant/v1/findAll','fa-cubes','BUTTON',5,147440945313349632,'2022-06-16 10:33:36','2022-06-16 10:33:36','admin','admin',0,0,1,3,0),
	 (147441162525868032,'增加租户','3-1-2','uc/tenant/v1/add','fa-cubes','BUTTON',1,147440945313349632,'2022-06-16 10:34:10','2022-06-16 10:34:10','admin','admin',0,0,1,3,0),
	 (147441179440447488,'修改租户','3-1-3','uc/tenant/v1/update','fa-cubes','BUTTON',2,147440945313349632,'2022-06-16 10:34:26','2022-06-16 10:34:26','admin','admin',0,0,1,2,0),
	 (147441234283069440,'删除租户','3-1-4','uc/tenant/v1/logicalDelete','fa-cubes','BUTTON',3,147440945313349632,'2022-06-16 10:35:18','2022-06-16 10:35:18','admin','admin',0,0,1,3,0),
	 (147441282127495168,'彻底删除租户','3-1-5','uc/tenant/v1/realDelete','fa-cubes','BUTTON',4,147440945313349632,'2022-06-16 10:36:04','2022-06-16 10:36:04','admin','admin',0,0,1,3,0),
	 (147441328737746944,'查询租户','3-1-6','uc/tenant/v1/findPage','fa-cubes','BUTTON',6,147440945313349632,'2022-06-16 10:36:48','2022-06-16 10:36:48','admin','admin',0,0,1,3,0),
	 (147441480389099520,'客户管理','4','','fa-life-ring','MENU',4,0,'2022-06-16 10:39:13','2022-06-16 10:39:13','admin','admin',0,0,0,1,0),
	 (147441818155352064,'增加客户','4-1-1','uc/customer/v1/add','fa-life-ring','BUTTON',1,146829262708539392,'2022-06-16 10:44:35','2022-06-16 10:44:35','admin','admin',0,0,1,3,0);
INSERT INTO iot_user_center.menu (id,name,code,addr,icon,`type`,sort_no,pid,create_date,updated_date,created_by,updated_by,delete_flag,opt_lock_version,leaf_flag,`level`,has_button) VALUES
	 (147441830122749952,'修改客户','4-1-2','uc/customer/v1/update','fa-life-ring','BUTTON',2,146829262708539392,'2022-06-16 10:44:47','2022-06-16 10:44:47','admin','admin',0,0,1,3,0),
	 (147441854572396544,'删除客户','4-1-3','uc/customer/v1/logicalDelete','fa-life-ring','BUTTON',3,146829262708539392,'2022-06-16 10:45:10','2022-06-16 10:45:10','admin','admin',0,0,1,3,0),
	 (147441877281406976,'彻底删除客户','4-1-4','uc/customer/v1/realDelete','fa-life-ring','BUTTON',4,146829262708539392,'2022-06-16 10:45:32','2022-06-16 10:45:32','admin','admin',0,0,1,3,0),
	 (147441922005270528,'查询客户','4-1-5','uc/customer/v1/findPage','fa-life-ring','BUTTON',5,146829262708539392,'2022-06-16 10:46:14','2022-06-16 10:46:14','admin','admin',0,0,1,3,0),
	 (147441935649341440,'查询全部客户','4-1-6','uc/customer/v1/findAll','fa-life-ring','BUTTON',6,146829262708539392,'2022-06-16 10:46:27','2022-06-16 10:46:27','admin','admin',0,0,1,3,0),
	 (147458910242996224,'查询所有菜单信息','1-1-1-8','uc/menu/v1/findAll','fa-times','BUTTON',8,107613911113531392,'2022-06-16 15:16:15','2022-06-16 15:16:15','admin','admin',0,0,1,3,0),
	 (147459278281637888,'保存角色功能配置','1-3-1-3','uc/role/v1/configureMenu','fa-times','BUTTON',3,146828535889133568,'2022-06-16 15:22:06','2022-06-16 15:22:06','admin','admin',0,0,1,3,0),
	 (147462024470724608,'查询角色用户Id','1-3-1-4','uc/role/v1/findUserIds','fa-times','BUTTON',7,146828535889133568,'2022-06-16 16:05:45','2022-06-16 16:05:45','admin','admin',0,0,1,3,0),
	 (147462103523917824,'保存角色用户配置','1-3-1-5','uc/role/v1/configureUser','fa-times','BUTTON',8,146828535889133568,'2022-06-16 16:07:01','2022-06-16 16:07:01','admin','admin',0,0,1,3,0),
	 (147536581724471296,'查询一个用户','0-1-6','uc/user/v1/findOne','fa-times','BUTTON',8,147438075271708672,'2022-06-17 11:50:49','2022-06-17 11:50:49','admin','admin',0,0,1,2,0);
INSERT INTO iot_user_center.menu (id,name,code,addr,icon,`type`,sort_no,pid,create_date,updated_date,created_by,updated_by,delete_flag,opt_lock_version,leaf_flag,`level`,has_button) VALUES
	 (147541774261288960,'彻底删除用户','0-1-7','uc/user/v1/realDelete','fa-times','BUTTON',7,147438075271708672,'2022-06-17 13:13:21','2022-06-17 13:13:21','admin','admin',0,0,1,2,0),
	 (147541804904873984,'删除用户','0-1-8','uc/user/v1/logicalDelete','fa-times','BUTTON',8,147438075271708672,'2022-06-17 13:13:50','2022-06-17 13:13:50','admin','admin',0,0,1,2,0),
	 (147545549315244032,'查询角色详情','1-2-1-7','uc/role/v1/findOne','fa-times','BUTTON',8,147376568370462720,'2022-06-17 14:13:21','2022-06-17 14:13:21','admin','admin',0,0,1,3,0),
	 (147551027783008256,'租户详情','3-1-7','uc/tenant/v1/findOne','fa-times','BUTTON',7,147440945313349632,'2022-06-17 15:40:26','2022-06-17 15:40:26','admin','admin',0,0,1,2,0),
	 (147551049879650304,'客户详情','4-1-7','uc/customer/v1/findOne','fa-times','BUTTON',7,146829262708539392,'2022-06-17 15:40:47','2022-06-17 15:40:47','admin','admin',0,0,1,2,0),
	 (147983322833747968,'产品管理','4','','fa-times','MENU',1,0,'2022-06-22 10:11:34','2022-06-22 10:11:34','admin','admin',0,0,0,0,0),
	 (147984777557835776,'产品类型列表','4-1-1','pc/productType/productTypeList','fa-times','MENU',1,147985166727380992,'2022-06-22 10:34:42','2022-06-22 10:46:12','admin','admin',0,1,1,2,1),
	 (147985166727380992,'产品类型管理','4-1','','fa-times','MENU',1,147983322833747968,'2022-06-22 10:40:53','2022-06-22 10:40:53','admin','admin',0,0,0,1,0),
	 (147985501116170240,'产品类型查询','4-1-1-1','pc/productType/v1/findPage','fa-times','BUTTON',1,147984777557835776,'2022-06-22 10:46:12','2022-06-22 10:46:12','admin','admin',0,0,1,3,0),
	 (147985558081110016,'查询一个产品类型','4-1-1-2','pc/productType/v1/findOne','fa-times','BUTTON',2,147984777557835776,'2022-06-22 10:47:06','2022-06-22 10:47:06','admin','admin',0,0,1,3,0);
INSERT INTO iot_user_center.menu (id,name,code,addr,icon,`type`,sort_no,pid,create_date,updated_date,created_by,updated_by,delete_flag,opt_lock_version,leaf_flag,`level`,has_button) VALUES
	 (147985602991620096,'产品类型增加','4-1-1-3','pc/productType/v1/add','fa-times','BUTTON',3,147984777557835776,'2022-06-22 10:47:49','2022-06-22 10:47:49','admin','admin',0,0,1,3,0),
	 (147985625633521664,'产品类型修改','4-1-1-4','pc/productType/v1/update','fa-times','BUTTON',4,147984777557835776,'2022-06-22 10:48:10','2022-06-22 10:48:10','admin','admin',0,0,1,3,0),
	 (147985665891500032,'产品类型删除','4-1-1-5','pc/productType/v1/logicalDelete','fa-times','BUTTON',5,147984777557835776,'2022-06-22 10:48:49','2022-06-22 10:48:49','admin','admin',0,0,1,3,0),
	 (147985712696786944,'彻底删除产品类型','4-1-1-6','pc/productType/v1/realDelete','fa-times','BUTTON',6,147984777557835776,'2022-06-22 10:49:33','2022-06-22 10:49:33','admin','admin',0,0,1,3,0),
	 (149008881381539840,'增加标准属性模型','4-1-1-7','pc/standard/pm/v1/add','fa-cubes','BUTTON',1,147984777557835776,'2022-07-03 17:52:23','2022-07-03 17:52:23','admin','admin',0,0,1,3,0),
	 (149078840634769408,'增加标准事件','4-1-1-8','pc/standard/em/v1/add','fa-cubes','BUTTON',2,147984777557835776,'2022-07-04 12:24:21','2022-07-04 12:24:21','admin','admin',0,0,1,3,0),
	 (149078859425251328,'增加标准指令','4-1-1-9','pc/standard/cm/v1/add','fa-cubes','BUTTON',3,147984777557835776,'2022-07-04 12:24:39','2022-07-04 12:24:39','admin','admin',0,0,1,3,0),
	 (149096153499041792,'查询标准物模型','4-1-1-10','pc/standard/model/v1/findOne','fa-cubes','BUTTON',4,147984777557835776,'2022-07-04 16:59:32','2022-07-04 16:59:32','admin','admin',0,0,1,3,0),
	 (149096176661037056,'发布标准物模型','4-1-1-11','pc/standard/model/v1/pubModel','fa-cubes','BUTTON',5,147984777557835776,'2022-07-04 16:59:54','2022-07-04 16:59:54','admin','admin',0,0,1,3,0),
	 (149257377081720832,'修改标准属性模型','4-1-1-12','pc/standard/pm/v1/update','fa-life-ring','BUTTON',6,147984777557835776,'2022-07-06 11:42:07','2022-07-06 11:42:07','admin','admin',0,0,1,3,0);
INSERT INTO iot_user_center.menu (id,name,code,addr,icon,`type`,sort_no,pid,create_date,updated_date,created_by,updated_by,delete_flag,opt_lock_version,leaf_flag,`level`,has_button) VALUES
	 (149257397499592704,'修改标准事件模型','4-1-1-13','pc/standard/em/v1/update','fa-life-ring','BUTTON',7,147984777557835776,'2022-07-06 11:42:27','2022-07-06 11:42:27','admin','admin',0,0,1,3,0),
	 (149257413312118784,'修改标准指令模型','4-1-1-14','pc/standard/cm/v1/update','fa-life-ring','BUTTON',8,147984777557835776,'2022-07-06 11:42:42','2022-07-06 11:42:42','admin','admin',0,0,1,3,0),
	 (149276078123253760,'产品列表','4-2','pc/product/v1/productList','fa-times','MENU',2,147983322833747968,'2022-07-06 16:39:22','2022-07-06 17:22:42','admin','admin',0,2,1,1,1),
	 (149278804700823552,'增加产品','4-2-1','pc/product/v1/add','fa-times','BUTTON',1,149276078123253760,'2022-07-06 17:22:42','2022-07-06 17:22:42','admin','admin',0,0,1,2,0),
	 (149278827598577664,'修改产品','4-2-2','pc/product/v1/update','fa-times','BUTTON',2,149276078123253760,'2022-07-06 17:23:04','2022-07-06 17:23:04','admin','admin',0,0,1,2,0),
	 (149278856846508032,'查询产品','4-2-3','pc/product/v1/findPage','fa-times','BUTTON',3,149276078123253760,'2022-07-06 17:23:32','2022-07-06 17:23:32','admin','admin',0,0,1,2,0),
	 (149278923177328640,'删除产品','4-2-4','pc/product/v1/logicalDelete','fa-times','BUTTON',4,149276078123253760,'2022-07-06 17:24:35','2022-07-06 17:24:35','admin','admin',0,0,1,2,0),
	 (149278956390973440,'彻底删除产品','4-2-5','pc/product/v1/realDelete','fa-times','BUTTON',6,149276078123253760,'2022-07-06 17:25:07','2022-07-06 17:25:07','admin','admin',0,0,1,2,0),
	 (149356742080200704,'查询所有产品类别','4-1-1-15','pc/productType/v1/findAll','fa-times','BUTTON',9,147984777557835776,'2022-07-07 14:01:29','2022-07-07 14:01:29','admin','admin',0,0,1,3,0),
	 (149359886547812352,'产品详情','4-2-6','pc/product/v1/findOne','fa-times','BUTTON',10,149276078123253760,'2022-07-07 14:51:28','2022-07-07 14:51:28','admin','admin',0,0,1,2,0);
INSERT INTO iot_user_center.menu (id,name,code,addr,icon,`type`,sort_no,pid,create_date,updated_date,created_by,updated_by,delete_flag,opt_lock_version,leaf_flag,`level`,has_button) VALUES
	 (149455902591680512,'产品物模型详情','4-2-7','pc/product/model/v1/findOne','fa-times','BUTTON',5,149276078123253760,'2022-07-08 16:17:36','2022-07-08 16:17:36','admin','admin',0,0,1,2,0),
	 (149455948857999360,'发布产品物模型','4-2-8','pc/product/model/v1/pubModel','fa-times','BUTTON',6,149276078123253760,'2022-07-08 16:18:20','2022-07-08 16:18:20','admin','admin',0,0,1,2,0),
	 (149456015940648960,'新增产品指令物模型','4-2-9','pc/product/cm/v1/add','fa-times','BUTTON',7,149276078123253760,'2022-07-08 16:19:24','2022-07-08 16:19:24','admin','admin',0,0,1,2,0),
	 (149456036163485696,'修改产品指令物模型','4-2-10','pc/product/cm/v1/update','fa-times','BUTTON',8,149276078123253760,'2022-07-08 16:19:43','2022-07-08 16:19:43','admin','admin',0,0,1,2,0),
	 (149456132154327040,'新增产品事件物模型','4-2-11','pc/product/em/v1/add','fa-times','BUTTON',9,149276078123253760,'2022-07-08 16:21:15','2022-07-08 16:21:15','admin','admin',0,0,1,2,0),
	 (149456153204490240,'修改产品事件物模型','4-2-12','pc/product/em/v1/update','fa-times','BUTTON',10,149276078123253760,'2022-07-08 16:21:35','2022-07-08 16:21:35','admin','admin',0,0,1,2,0),
	 (149456192045842432,'修改产品属性物模型','4-2-13','pc/product/pm/v1/update','fa-times','BUTTON',11,149276078123253760,'2022-07-08 16:22:12','2022-07-08 16:22:12','admin','admin',0,0,1,2,0),
	 (149456203784650752,'新增产品属性物模型','4-2-14','pc/product/pm/v1/add','fa-times','BUTTON',12,149276078123253760,'2022-07-08 16:22:23','2022-07-08 16:22:23','admin','admin',0,0,1,2,0),
	 (149911334835716096,'设备管理','5','','fa-server','MENU',6,0,'2022-07-13 16:56:30','2022-07-13 16:56:30','admin','admin',0,0,0,0,0),
	 (149911617121812480,'设备列表','5-1','ds/deviceInfo/v1/deviceInfoList','fa-server','MENU',1,149911334835716096,'2022-07-13 17:00:59','2022-07-13 17:03:19','admin','admin',0,1,1,1,1);
INSERT INTO iot_user_center.menu (id,name,code,addr,icon,`type`,sort_no,pid,create_date,updated_date,created_by,updated_by,delete_flag,opt_lock_version,leaf_flag,`level`,has_button) VALUES
	 (149911764077641728,'增加设备','5-1-1','ds/deviceInfo/v1/add','fa-life-ring','BUTTON',1,149911617121812480,'2022-07-13 17:03:19','2022-07-13 17:03:19','admin','admin',0,0,1,2,0),
	 (149911786991124480,'修改设备','5-1-2','ds/deviceInfo/v1/update','fa-life-ring','BUTTON',2,149911617121812480,'2022-07-13 17:03:41','2022-07-13 17:03:41','admin','admin',0,0,1,2,0),
	 (149911811122003968,'删除设备','5-1-3','ds/deviceInfo/v1/logicalDelete','fa-life-ring','BUTTON',3,149911617121812480,'2022-07-13 17:04:04','2022-07-13 17:04:04','admin','admin',0,0,1,2,0),
	 (149911833743982592,'彻底删除设备','5-1-4','ds/deviceInfo/v1/realDelete','fa-life-ring','BUTTON',4,149911617121812480,'2022-07-13 17:04:26','2022-07-13 17:04:26','admin','admin',0,0,1,2,0),
	 (149911870881398784,'查询所有设备','5-1-5','ds/deviceInfo/v1/findAll','fa-life-ring','BUTTON',5,149911617121812480,'2022-07-13 17:05:01','2022-07-13 17:05:01','admin','admin',0,0,1,2,0),
	 (149911900418736128,'查询所有网关','5-1-6','ds/deviceInfo/v1/findAllGateway','fa-life-ring','BUTTON',6,149911617121812480,'2022-07-13 17:05:29','2022-07-13 17:05:29','admin','admin',0,0,1,2,0),
	 (149911920006135808,'查询设备','5-1-7','ds/deviceInfo/v1/findPage','fa-life-ring','BUTTON',7,149911617121812480,'2022-07-13 17:05:48','2022-07-13 17:05:48','admin','admin',0,0,1,2,0),
	 (149911942942687232,'查询设备详情','5-1-8','ds/deviceInfo/v1/findOne','fa-life-ring','BUTTON',8,149911617121812480,'2022-07-13 17:06:10','2022-07-13 17:06:10','admin','admin',0,0,1,2,0),
	 (149912624691150848,'查询所有产品','4-2-15','pc/product/v1/findAll','fa-times','BUTTON',8,149276078123253760,'2022-07-13 17:17:00','2022-07-13 17:17:00','admin','admin',0,0,1,2,0),
	 (150061829049024512,'设备最新采集数据查询','5-1-9','ds/deviceNewData/v1/findAllDeviceNewData','fa-times','BUTTON',10,149911617121812480,'2022-07-15 08:48:32','2022-07-15 08:48:32','admin','admin',0,0,1,2,0);
INSERT INTO iot_user_center.menu (id,name,code,addr,icon,`type`,sort_no,pid,create_date,updated_date,created_by,updated_by,delete_flag,opt_lock_version,leaf_flag,`level`,has_button) VALUES
	 (170652337599152128,'设备数据采集','5-1-10','ds/deviceData/v1/add','fa-times','BUTTON',13,149911617121812480,'2023-02-27 15:25:51','2023-02-27 15:25:51','admin','admin',0,0,1,2,0);

--insert role menu data
INSERT INTO iot_user_center.role_menu (menu_id,role_id) VALUES
	 (107613522955862016,107448355229007872),
	 (107613664466436096,107448355229007872),
	 (107613788299067392,107448355229007872),
	 (107613839504179200,107448355229007872),
	 (107613911113531392,107448355229007872),
	 (107614130884575232,107448355229007872),
	 (107614178373533696,107448355229007872),
	 (107709122035580928,107448355229007872),
	 (107854253073104896,107448355229007872),
	 (107855116817661952,107448355229007872);
INSERT INTO iot_user_center.role_menu (menu_id,role_id) VALUES
	 (107893999973433344,107448355229007872),
	 (108335280411377664,107448355229007872),
	 (146828535889133568,107448355229007872),
	 (146828961699069952,107448355229007872),
	 (146829262708539392,107448355229007872),
	 (147371949749698560,107448355229007872),
	 (147372140316852224,107448355229007872),
	 (147375951736471552,107448355229007872),
	 (147375979931631616,107448355229007872),
	 (147376079486582784,107448355229007872);
INSERT INTO iot_user_center.role_menu (menu_id,role_id) VALUES
	 (147376106165501952,107448355229007872),
	 (147376228566827008,107448355229007872),
	 (147376568370462720,107448355229007872),
	 (147377054078205952,107448355229007872),
	 (147377111413293056,107448355229007872),
	 (147438075271708672,107448355229007872),
	 (147438637661814784,107448355229007872),
	 (147438763664998400,107448355229007872),
	 (147439677932044288,107448355229007872),
	 (147440201643327488,107448355229007872);
INSERT INTO iot_user_center.role_menu (menu_id,role_id) VALUES
	 (147440717029965824,107448355229007872),
	 (147440945313349632,107448355229007872),
	 (147441127200391168,107448355229007872),
	 (147441162525868032,107448355229007872),
	 (147441179440447488,107448355229007872),
	 (147441234283069440,107448355229007872),
	 (147441282127495168,107448355229007872),
	 (147441328737746944,107448355229007872),
	 (147441480389099520,107448355229007872),
	 (147441818155352064,107448355229007872);
INSERT INTO iot_user_center.role_menu (menu_id,role_id) VALUES
	 (147441830122749952,107448355229007872),
	 (147441854572396544,107448355229007872),
	 (147441877281406976,107448355229007872),
	 (147441922005270528,107448355229007872),
	 (147441935649341440,107448355229007872),
	 (147458910242996224,107448355229007872),
	 (147459278281637888,107448355229007872),
	 (147462024470724608,107448355229007872),
	 (147462103523917824,107448355229007872),
	 (147536581724471296,107448355229007872);
INSERT INTO iot_user_center.role_menu (menu_id,role_id) VALUES
	 (147541774261288960,107448355229007872),
	 (147541804904873984,107448355229007872),
	 (147545549315244032,107448355229007872),
	 (147551027783008256,107448355229007872),
	 (147551049879650304,107448355229007872),
	 (147983322833747968,107448355229007872),
	 (147984777557835776,107448355229007872),
	 (147985166727380992,107448355229007872),
	 (147985501116170240,107448355229007872),
	 (147985558081110016,107448355229007872);
INSERT INTO iot_user_center.role_menu (menu_id,role_id) VALUES
	 (147985602991620096,107448355229007872),
	 (147985625633521664,107448355229007872),
	 (147985665891500032,107448355229007872),
	 (147985712696786944,107448355229007872),
	 (149008881381539840,107448355229007872),
	 (149078840634769408,107448355229007872),
	 (149078859425251328,107448355229007872),
	 (149096153499041792,107448355229007872),
	 (149096176661037056,107448355229007872),
	 (149257377081720832,107448355229007872);
INSERT INTO iot_user_center.role_menu (menu_id,role_id) VALUES
	 (149257397499592704,107448355229007872),
	 (149257413312118784,107448355229007872),
	 (149276078123253760,107448355229007872),
	 (149278804700823552,107448355229007872),
	 (149278827598577664,107448355229007872),
	 (149278856846508032,107448355229007872),
	 (149278923177328640,107448355229007872),
	 (149278956390973440,107448355229007872),
	 (149356742080200704,107448355229007872),
	 (149359886547812352,107448355229007872);
INSERT INTO iot_user_center.role_menu (menu_id,role_id) VALUES
	 (149455902591680512,107448355229007872),
	 (149455948857999360,107448355229007872),
	 (149456015940648960,107448355229007872),
	 (149456036163485696,107448355229007872),
	 (149456132154327040,107448355229007872),
	 (149456153204490240,107448355229007872),
	 (149456192045842432,107448355229007872),
	 (149456203784650752,107448355229007872),
	 (149911334835716096,107448355229007872),
	 (149911617121812480,107448355229007872);
INSERT INTO iot_user_center.role_menu (menu_id,role_id) VALUES
	 (149911764077641728,107448355229007872),
	 (149911786991124480,107448355229007872),
	 (149911811122003968,107448355229007872),
	 (149911833743982592,107448355229007872),
	 (149911870881398784,107448355229007872),
	 (149911900418736128,107448355229007872),
	 (149911920006135808,107448355229007872),
	 (149911942942687232,107448355229007872),
	 (149912624691150848,107448355229007872),
	 (150061829049024512,107448355229007872);
INSERT INTO iot_user_center.role_menu (menu_id,role_id) VALUES
	 (170652337599152128,107448355229007872);

--insert role user data
INSERT INTO iot_user_center.role_user (role_id,user_id) VALUES
	 (107448355229007872,106882660026023936);

--insert oauth2 client data
INSERT INTO iot_user_center.oauth_client_details (client_id,resource_ids,client_secret,`scope`,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove,created_date,updated_date,created_by,updated_by,delete_flag) VALUES
	 ('plm','user_center_api','$2a$10$p32TGzI5iBkvVD0rDCMEGe6K/Vw0Iz7jr7lrKUrEzXJZLjqVJk/XK','read,write','sms,password,refresh_token','http://127.0.0.1','ROLE_PROJECT_ADMIN',6000,18000,NULL,'true','2021-04-01 00:05:37','2021-04-01 00:05:37','admin','admin',0);