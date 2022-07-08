create table if not exists tb_user
(
    id   int not null primary key auto_increment,
    name varchar(255) default null,
    age  int          default 0
) engine = InnoDB
  default CHARSET = utf8mb4;
