create TABLE user
(
    user_id  int AUTO_INCREMENT primary key,
    nickname VARCHAR(10),
    point    BIGINT
);

create TABLE orders
(
    order_id    INT AUTO_INCREMENT PRIMARY KEY,
    ordered_at  DATETIME,
    total_price bigint,
    user_id     int,
    foreign key (user_id) references user (user_id)
);

create table point_record
(
    point_record_id int AUTO_INCREMENT primary key,
    point           int,
    point_status    enum ('CHARGE','USE'),
    user_id         int,
    foreign key (user_id) references user (user_id)
);

create table order_detail
(
    order_detail_id int AUTO_INCREMENT primary key,
    price           int,
    quantity        smallint,
    coffee_id       int,
    order_id        int,
    foreign key (coffee_id) references coffee (id)
);

create table coffee
(
    id          int AUTO_INCREMENT primary key,
    created_at  DATETIME,
    modified_at DATETIME,
    name        VARCHAR(10),
    price       int
);

insert into coffee (id, created_at, modified_at, name, price)
values (1, now(), null, '아메리카노', 1500),
       (2, now(), null, '바닐라라떼', 3300),
       (3, now(), null, '딸기라떼', 3500),
       (4, now(), null, '레몬에이드', 3000);

alter table user rename to users;

alter table order_detail
    add foreign key (order_id) references orders (order_id);