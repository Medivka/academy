CREATE TABLE b_table
(
    id      int PRIMARY KEY AUTO_INCREMENT,
    address varchar(255) NULL,
    amount  int NULL,
    height  int NULL,
    is_dead varchar(255) NULL,
    phone   varchar(255) NULL
);

CREATE TABLE a_table
(
    id       int PRIMARY KEY AUTO_INCREMENT,
    apple    varchar(255) NULL,
    enable   varchar(255) NULL,
    name     varchar(255) NULL,
    password varchar(255) NULL,
    tree     varchar(255) NULL,
    b        int NULL
);

CREATE TABLE c_table
(
    id       int PRIMARY KEY AUTO_INCREMENT,
    age      int NULL,
    car      varchar(255) NULL,
    is_fast  varchar(255) NULL,
    lastname varchar(255) NULL,
    memory   int NULL,
    name     varchar(255) NULL,
    a        int NULL
);

CREATE TABLE single
(
    id int PRIMARY KEY AUTO_INCREMENT,
    e  varchar(255) NULL
);

CREATE TABLE d_table
(
    id        int PRIMARY KEY AUTO_INCREMENT,
    cinema    int NULL,
    hero      varchar(255) NULL,
    is_alone  varchar(255) NULL,
    name      varchar(255) NULL,
    number    int NULL,
    single_id int UNIQUE
);

CREATE TABLE b_and_d_table
(
    id_d int NOT NULL,
    id_b int NOT NULL
);
INSERT INTO `b_table` (`address`, `amount`, `height`, `is_dead`, `phone`)
VALUES ('moscow', '1', '2', 'no', '123');
INSERT INTO `b_table` (`address`, `amount`, `height`, `is_dead`, `phone`)
VALUES ('brest', '3', '4', 'asd', '456');
INSERT INTO `b_table` (`address`, `amount`, `height`, `is_dead`, `phone`)
VALUES ('minsk', '5', '6', 'efg', '789');
INSERT INTO `b_table` (`address`, `amount`, `height`, `is_dead`, `phone`)
VALUES ('kaluga', '7', '8', 'ada', '1011');
INSERT INTO `a_table` ( `apple`, `enable`, `name`, `password`, `tree`
                      , `b`)
VALUES ( 'greem', 'yes', 'prince', '123', '123'
       , '1');
INSERT INTO `a_table` ( `apple`, `enable`, `name`, `password`, `tree`
                      , `b`)
VALUES ( 'red', 'no', 'prince', '345', '456'
       , '2');
INSERT INTO `a_table` ( `apple`, `enable`, `name`, `password`, `tree`
                      , `b`)
VALUES ( 'yellow', 'i do not know', 'shine', '678', '159'
       , '1');
INSERT INTO `a_table` ( `apple`, `enable`, `name`, `password`, `tree`
                      , `b`)
VALUES ( 'brown', 'may be', 'none', '456', '45'
       , '3');
INSERT INTO `c_table` ( `age`, `car`, `is_fast`, `lastname`, `memory`
                      , `name`, `a`)
VALUES ( '12', 'wv', 'v2', 'lastname', '2000'
       , 'name', '1');
INSERT INTO `c_table` ( `age`, `car`, `is_fast`, `lastname`, `memory`
                      , `name`, `a`)
VALUES ( '18', 'bently', 'v12', 'lastname', '8000'
       , 'name2', '2');
INSERT INTO `c_table` ( `age`, `car`, `is_fast`, `lastname`, `memory`
                      , `name`, `a`)
VALUES ( '20', 'bmw', 'v1', 'lastname3', '10000'
       , 'name3', '3');
INSERT INTO `c_table` ( `age`, `car`, `is_fast`, `lastname`, `memory`
                      , `name`, `a`)
VALUES ( '36', 'gaz', 'v0.2', 'lastname4', '500'
       , 'name4', '4');
INSERT INTO `single` (`e`)
VALUES ('ON');
INSERT INTO `single` (`e`)
VALUES ('OFF');
INSERT INTO `single` (`e`)
VALUES ('IN');
INSERT INTO `single` (`e`)
VALUES ('AT');
INSERT INTO `d_table` ( `cinema`, `hero`, `is_alone`, `name`, `number`
                      , `single_id`)
VALUES ( '1', 'halk', 'maybe', 'HALK', '1'
       , '1');
INSERT INTO `d_table` ( `cinema`, `hero`, `is_alone`, `name`, `number`
                      , `single_id`)
VALUES ( '2', 'spider-man', 'maybe', 'PITER', '1'
       , '2');
INSERT INTO `d_table` ( `cinema`, `hero`, `is_alone`, `name`, `number`
                      , `single_id`)
VALUES ( '3', 'ironman', 'maybe', 'STARK', '2'
       , '3');
INSERT INTO `d_table` ( `cinema`, `hero`, `is_alone`, `name`, `number`
                      , `single_id`)
VALUES ( '4', 'Turtules', 'maybe', 'DONATELO', '3'
       , '4');
INSERT INTO `b_and_d_table` (`id_d`, `id_b`)
VALUES ('1', '1');
INSERT INTO `b_and_d_table` (`id_d`, `id_b`)
VALUES ('1', '2');
INSERT INTO `b_and_d_table` (`id_d`, `id_b`)
VALUES ('2', '3');
INSERT INTO `b_and_d_table` (`id_d`, `id_b`)
VALUES ('2', '1');
INSERT INTO `b_and_d_table` (`id_d`, `id_b`)
VALUES ('1', '3');
INSERT INTO `b_and_d_table` (`id_d`, `id_b`)
VALUES ('3', '1');
INSERT INTO `b_and_d_table` (`id_d`, `id_b`)
VALUES ('4', '1');
INSERT INTO `b_and_d_table` (`id_d`, `id_b`)
VALUES ('1', '4');
