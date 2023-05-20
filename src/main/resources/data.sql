--drop table users_roles;
--drop table users;
--drop table roles;
--create table if not exists roles
--(
--    id   bigint auto_increment
--        primary key,
--    name varchar(64) not null
--);
--
--create table if not exists users
--(
--    id       bigint auto_increment
--        primary key,
--    email    varchar(64)  not null,
--    name     varchar(20)  not null,
--    password varchar(255) not null,
--    surname  varchar(30)  not null,
--    constraint UK_6dotkott2kjsp8vw4d0m25fb7
--        unique (email)
--);
--
--create table if not exists users_roles
--(
--    user_id bigint not null,
--    role_id bigint not null,
--    primary key (user_id, role_id),
--    constraint FK2o0jvgh89lemvvo17cbqvdxaa
--        foreign key (user_id) references users (id),
--    constraint FKj6m8fwv7oqv74fcehir1a9ffy
--        foreign key (role_id) references roles (id)
--);

insert into users(email, name, password, surname) VALUES ('admin@mail.ru','Admin','$2a$10$E4uwfFKQlCo1MxyU6Z.hle.6Py4PjPyJ0q0qjVmKVwAQHcXve7TgS', 'Adminov');
insert into users(email, name, password, surname) VALUES ('user@mail.ru','User','$2a$10$8QMlDm9RR856Qi/mOGh/nORVUMczZt/i2kIcPGw1JdTh9SpCHNdka', 'Userov');
insert into roles(name) values ('ADMIN');
insert into roles(name) values ('USER');


insert into users_roles(user_id, role_id ) VALUES (1,1);
insert into users_roles(user_id, role_id ) VALUES (1,2);
insert into users_roles(user_id, role_id) VALUES (2,2);