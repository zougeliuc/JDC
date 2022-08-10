create table tb_cart
(
    cid     int auto_increment
        primary key,
    uid     int            not null,
    gid     int            not null,
    cnt     int            not null,
    price   decimal(10, 2) not null,
    opttime datetime       null
);

create table tb_favorite
(
    fid   int auto_increment
        primary key,
    uid   int            not null,
    gid   int            not null,
    price decimal(10, 2) null,
    ftime datetime       null
);

create table tb_goods
(
    gid   int auto_increment
        primary key,
    url   varchar(255)             null,
    price decimal(10, 2)           null,
    title varchar(255)             null,
    shop  varchar(50)              null,
    type  varchar(20) charset utf8 null
);

create table tb_permission
(
    pid   int auto_increment
        primary key,
    pname varchar(16) not null
);

create table tb_role
(
    rid   int auto_increment
        primary key,
    rname varchar(16) not null
);

create table tb_role_permission
(
    rpid int auto_increment
        primary key,
    rid  int null,
    pid  int null
);

create table tb_user
(
    uid      int auto_increment
        primary key,
    username varchar(16)  not null,
    password varchar(64)  not null,
    phone    varchar(11)  null,
    sex      varchar(8)   null,
    info     varchar(256) null,
    address  varchar(64)  null,
    showname varchar(16)  null,
    regtime  datetime     null,
    email    varchar(32)  null
);

create table tb_user_role
(
    urid int auto_increment
        primary key,
    uid  int null,
    rid  int null
);


