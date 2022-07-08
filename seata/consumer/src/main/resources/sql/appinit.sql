create table if not exists tb_order
(
    id   int not null primary key auto_increment,
    user_id int not null,
    description varchar(255) default null
) engine = InnoDB
  default CHARSET = utf8mb4;
