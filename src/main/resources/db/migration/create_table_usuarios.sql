create table usuarios(
                         id bigint not null auto_increment,
                         userName varchar(100) not null,
                         senha varchar(255) not null,

                         primary key(id)
);