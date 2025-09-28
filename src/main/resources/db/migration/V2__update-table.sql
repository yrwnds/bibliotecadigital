alter table usuarios
    add qt_livros_emprestados int not null DEFAULT 0;

alter table emprestimo
    add qt_renovacoes int not null DEFAULT 0;