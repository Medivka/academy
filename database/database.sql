drop schema if exists academy  ;
create schema academy;


create table academy.b_table
(
    id      int auto_increment
        primary key,
    address varchar(255) null,
    amount  int          null,
    height  int          null,
    is_dead varchar(255) null,
    phone   varchar(255) null
);

create table academy.a_table
(
    id       int auto_increment
        primary key,
    apple    varchar(255) null,
    enable   varchar(255) null,
    name     varchar(255) null,
    password varchar(255) null,
    tree     varchar(255) null,
    b        int          null,
    constraint FKqw6fm0x3lvccn3rb5p896c6rg
        foreign key (b) references academy.b_table (id)
);

create table academy.c_table
(
    id       int auto_increment
        primary key,
    age      int          null,
    car      varchar(255) null,
    is_fast  varchar(255) null,
    lastname varchar(255) null,
    memory   int          null,
    name     varchar(255) null,
    a        int          null,
    constraint FKto8j5x4qbclrrjyb865ah58t0
        foreign key (a) references academy.a_table (id)
);

create table academy.single
(
    id int auto_increment
        primary key,
    e  varchar(255) null
);

create table academy.d_table
(
    id        int auto_increment
        primary key,
    cinema    int          null,
    hero      varchar(255) null,
    is_alone  varchar(255) null,
    name      varchar(255) null,
    number    int          null,
    single_id int          unique ,
    constraint FKjeei4uwna5gwk6s527b5wkhod
        foreign key (single_id) references academy.single (id)
);

create table academy.b_and_d_table
(
    id_d int not null,
    id_b int not null,
    constraint FKgt0r8t3obukyhmby84ugaxjb0
        foreign key (id_d) references academy.d_table (id),
    constraint FKijv54ie9vdpivrdfy2bj9emrs
        foreign key (id_b) references academy.b_table (id)
);

INSERT INTO `academy`.`b_table` (`address`, `amount`, `height`, `is_dead`, `phone`) VALUES ('moscow', '1', '2', 'no', '123');
INSERT INTO `academy`.`b_table` (`address`, `amount`, `height`, `is_dead`, `phone`) VALUES ('brest', '3', '4', 'asd', '456');
INSERT INTO `academy`.`b_table` (`address`, `amount`, `height`, `is_dead`, `phone`) VALUES ('minsk', '5', '6', 'efg', '789');
INSERT INTO `academy`.`b_table` (`address`, `amount`, `height`, `is_dead`, `phone`) VALUES ('kaluga', '7', '8', 'ada', '1011');

INSERT INTO `academy`.`a_table` (`apple`, `enable`, `name`, `password`, `tree`, `b`) VALUES ('greem', 'yes', 'prince', '123', '123', '1');
INSERT INTO `academy`.`a_table` (`apple`, `enable`, `name`, `password`, `tree`, `b`) VALUES ('red', 'no', 'prince', '345', '456', '2');
INSERT INTO `academy`.`a_table` (`apple`, `enable`, `name`, `password`, `tree`, `b`) VALUES ('yellow', 'i do not know', 'shine', '678', '159', '1');
INSERT INTO `academy`.`a_table` (`apple`, `enable`, `name`, `password`, `tree`, `b`) VALUES ('brown', 'may be', 'none', '456', '45', '3');

INSERT INTO `academy`.`c_table` (`age`, `car`, `is_fast`, `lastname`, `memory`, `name`, `a`) VALUES ('12', 'wv', 'v2', 'lastname', '2000', 'name', '1');
INSERT INTO `academy`.`c_table` (`age`, `car`, `is_fast`, `lastname`, `memory`, `name`, `a`) VALUES ('18', 'bently', 'v12', 'lastname', '8000', 'name2', '2');
INSERT INTO `academy`.`c_table` (`age`, `car`, `is_fast`, `lastname`, `memory`, `name`, `a`) VALUES ('20', 'bmw', 'v1', 'lastname3', '10000', 'name3', '3');
INSERT INTO `academy`.`c_table` (`age`, `car`, `is_fast`, `lastname`, `memory`, `name`, `a`) VALUES ('36', 'gaz', 'v0.2', 'lastname4', '500', 'name4', '4');

INSERT INTO `academy`.`single` (`e`) VALUES ('ON');
INSERT INTO `academy`.`single` (`e`) VALUES ('OFF');
INSERT INTO `academy`.`single` (`e`) VALUES ('IN');
INSERT INTO `academy`.`single` (`e`) VALUES ('AT');

INSERT INTO `academy`.`d_table` (`cinema`, `hero`, `is_alone`, `name`, `number`, `single_id`) VALUES ('1', 'halk','maybe', 'HALK', '1', '1');
INSERT INTO `academy`.`d_table` (`cinema`, `hero`, `is_alone`, `name`, `number`, `single_id`) VALUES ('2', 'spider-man','maybe', 'PITER', '1','2');
INSERT INTO `academy`.`d_table` (`cinema`, `hero`, `is_alone`, `name`, `number`, `single_id`) VALUES ('3', 'ironman','maybe', 'STARK', '2', '3');
INSERT INTO `academy`.`d_table` (`cinema`, `hero`, `is_alone`, `name`, `number`, `single_id`) VALUES ('4', 'Turtules', 'maybe', 'DONATELO', '3', '4');

INSERT INTO `academy`.`b_and_d_table`(`id_d`, `id_b`)value ('1', '1');
INSERT INTO `academy`.`b_and_d_table`(`id_d`, `id_b`)value ('1', '2');
INSERT INTO `academy`.`b_and_d_table`(`id_d`, `id_b`)value ('2', '3');
INSERT INTO `academy`.`b_and_d_table`(`id_d`, `id_b`)value ('2', '1');
INSERT INTO `academy`.`b_and_d_table`(`id_d`, `id_b`)value ('1', '3');
INSERT INTO `academy`.`b_and_d_table`(`id_d`, `id_b`)value ('3', '1');
INSERT INTO `academy`.`b_and_d_table`(`id_d`, `id_b`)value ('4', '1');
INSERT INTO `academy`.`b_and_d_table`(`id_d`, `id_b`)value ('1', '4');

drop schema if exists profile  ;
create schema profile;

create table profile.profile
(
    id       bigint auto_increment
        primary key,
    password varchar(255) null,
    username varchar(255) null
);

create table profile.role
(
    id   bigint auto_increment
        primary key,
    role varchar(255) null
);

create table profile.profile_role
(
    client_id bigint not null,
    role_id   bigint not null,
    primary key (client_id, role_id),
    constraint FK9sx4d1mv1bx7ycdst08d1grmh
        foreign key (role_id) references profile.role (id),
    constraint FK9uh6ebst0d27ynk42o315r6xj
        foreign key (client_id) references profile.profile (id)
);


INSERT INTO `profile`.`profile` (`password`, `username`) VALUES ('admin', 'admin');
INSERT INTO `profile`.`profile` (`password`, `username`) VALUES ('user', 'user');
INSERT INTO `profile`.`profile` (`password`, `username`) VALUES ('READER', 'READER');
INSERT INTO `profile`.`profile` (`password`, `username`) VALUES ('AUTHOR', 'AUTHOR');
INSERT INTO `profile`.`profile` (`password`, `username`) VALUES ('EDITOR', 'EDITOR');

INSERT INTO `profile`.`role` (`id`, `role`) VALUES ('1', 'ROLE_READER');
INSERT INTO `profile`.`role` (`id`, `role`) VALUES ('2', 'ROLE_AUTHOR');
INSERT INTO `profile`.`role` (`id`, `role`) VALUES ('3', 'ROLE_EDITOR');

INSERT INTO `profile`.`profile_role` (`client_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `profile`.`profile_role` (`client_id`, `role_id`) VALUES ('1', '2');
INSERT INTO `profile`.`profile_role` (`client_id`, `role_id`) VALUES ('1', '3');
INSERT INTO `profile`.`profile_role` (`client_id`, `role_id`) VALUES ('2', '3');
INSERT INTO `profile`.`profile_role` (`client_id`, `role_id`) VALUES ('3', '1');
INSERT INTO `profile`.`profile_role` (`client_id`, `role_id`) VALUES ('4', '2');
INSERT INTO `profile`.`profile_role` (`client_id`, `role_id`) VALUES ('5', '2');
INSERT INTO `profile`.`profile_role` (`client_id`, `role_id`) VALUES ('5', '1');
INSERT INTO `profile`.`profile_role` (`client_id`, `role_id`) VALUES ('5', '3');

