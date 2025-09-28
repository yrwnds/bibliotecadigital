create table usuarios
(
    UUID      UUID DEFAULT gen_random_uuid(),
    id        serial       not null primary key,
    nome      varchar(255) not null,
    matricula varchar(8)   not null unique,
    email     varchar(100) not null unique,
    senha     varchar(32)  not null
);
create table categorias
(
    UUID UUID DEFAULT gen_random_uuid(),
    id   serial      not null primary key,
    nome varchar(50) not null
);

create table livros
(
    ISBN         serial       not null primary key,
    UUID         UUID DEFAULT gen_random_uuid(),
    titulo       varchar(255) not null,
    autor        varchar(255) not null,
    anopublicado int,

    categoria_id int          not null,

    linguagem    varchar(32)  not null,

    n_exemplares int          not null,
    n_disponivel int          not null,

    foreign key (categoria_id) references categorias (id)
);

create table emprestimo
(
    id         serial      not null primary key,
    livro_ISBN int         not null,
    UUID       UUID DEFAULT gen_random_uuid(),
    usuario_id int         not null,
    datapego   timestamptz not null,
    dataprazo  timestamptz not null,
    status     varchar(10) not null, /* ATIVO, DEVOLVIDO */

    foreign key (livro_ISBN) references livros (ISBN),
    foreign key (usuario_id) references usuarios (id)
);

insert into categorias(nome)
values ('Romance'),
       ('Fantasia'),
       ('Sci-fi'),
       ('Terror'),
       ('Biografia'),
       ('Crime'),
       ('Clássico'),
       ('Literatura brasileira'),
       ('Ficção Histórica'),
       ('História'),
       ('Filosofia'),
       ('Psicologia'),
       ('Infantil'),
       ('Infanto-Juvenil'),
       ('Auto-Ajuda'),
       ('Poesia'),
       ('Velho-Oeste'),
       ('Religião'),
       ('Ação');

insert into livros (titulo, autor, anopublicado, categoria_id, linguagem, n_exemplares, n_disponivel)
values ('Dom Casmurro', 'Machado de Assis', '1899', 8, 'Português', 5, 5)