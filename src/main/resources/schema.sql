create table sysuser
(
   username varchar(255) not null,
   password varchar(255) not null,
   name varchar(255),
   email varchar(255),
   role varchar(255),
   primary key(username)
);