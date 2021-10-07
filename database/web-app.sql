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

