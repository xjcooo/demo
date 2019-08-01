create schema demo_test collate utf8_general_ci;

create table demo_authorization
(
	id int auto_increment comment '主键'
		primary key,
	name varchar(20) not null comment '权限名',
	url varchar(50) not null comment '权限url',
	tag varchar(50) not null comment '权限标识',
	enabled tinyint(1) default 1 null comment '是否可用(默认为true,表示可用)',
	create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
	modify_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
	constraint demo_authorization_name_uindex
		unique (name)
)
	comment '权限表';

create table demo_role
(
	id int auto_increment comment '主键'
		primary key,
	name varchar(20) not null comment '角色名',
	`desc` varchar(100) null comment '角色描述',
	enabled tinyint(1) default 1 null comment '是否可用(默认ture, 表示可用)',
	create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
	modify_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
	constraint demo_role_name_uindex
		unique (name)
)
	comment '角色表';

create table demo_user
(
	id int auto_increment comment '主键'
		primary key,
	username varchar(50) not null comment '用户名-用于登录',
	name varchar(50) null comment '用户全名',
	expired tinyint(1) default 0 null comment '是否过期(默认false,表示不过期)',
	enabled tinyint(1) default 1 null comment '是否可用(默认true,表示可用)',
	password varchar(100) not null comment '密码',
	locked tinyint(1) default 0 null comment '账号锁定(默认false,表示不锁定)',
	certificate_expired tinyint(1) default 0 null comment '证书过期(默认false,表示没过期)',
	create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
	last_login_time datetime default CURRENT_TIMESTAMP null comment '最后登录时间',
	modify_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
	constraint demo_user_username_uindex
		unique (username)
)
	comment '用户表';

