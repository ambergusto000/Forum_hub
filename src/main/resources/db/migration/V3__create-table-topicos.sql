create table topicos(
                        id bigint not null auto_increment,
                        titulo varchar(100) not null,
                        mensagem varchar(255) not null,
                        data_inc datetime not null,
                        status ENUM('NAO_RESPONDIDO', 'RESPONDIDO', 'EM_ANALISE', 'FECHADO') DEFAULT 'NAO_RESPONDIDO',
                        autor_id bigint not null,
                        curso_id bigint not null,

                        primary key(id),

                        constraint fk_topicos_autor_id foreign key (autor_id) references usuarios(id),
                        constraint fk_topicos_curso_id foreign key (curso_id) references cursos(id)
);RESOLVIDO';