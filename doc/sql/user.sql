create table user
(
    id           bigint auto_increment comment '标识'
        primary key,
    username     varchar(500)                       null comment '用户名称',
    userAccount  varchar(200)                       null comment '账号',
    userPassword varchar(200)                       not null comment '密码',
    gender       tinyint                            null comment '性别',
    avatarUrl    varchar(1024)                      null comment '头像',
    phone        varchar(500)                       null comment '电话',
    email        varchar(200)                       null comment '邮箱',
    userStatus   int      default 1                 null comment '用户状态 默认 1 正常',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    userRole     tinyint  default 0                 null comment '用户角色 默认 0 普通用户   超级管理员 1',
    isDelete     tinyint  default 0                 null comment '是否删除（软删除）默认为0'
)
    comment '用户信息表';