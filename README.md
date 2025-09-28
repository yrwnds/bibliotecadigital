# Biblioteca Digital

Este repositório contém um projeto em Java usando o framework Spring Data JPA.

## Descrição do projeto
Seu propósito é a representação de uma biblioteca digital, em que alunos usuários poderão livremente acessar os livros disponíveis no catálogo e pegá-los emprestados.
Ambos usuários e administradores poderão acessar o acervo digital e suas respectivas telas usando sua matrícula e senha.
* Funções disponibilizadas para os **usuários** incluem: Pegar um livro, devolver um livro, buscar um livro por título, autor, categoria, ISBN e linguagem.

* Funções disponibilizadas para os **administradores** incluem: Atualizar um livro ou categoria, deletar ou adicionar um livro ou categoria, atualizar um empréstimo, atualizar um cadastro.

Há um número limitado de exemplares disponíveis para cada livro digital. Um usuário poderá ter, no máximo, três empréstimos de livro ativos ao mesmo tempo.

Quando um empréstimo é realizado, o usuário terá o livro por uma semana. O usuário pode renovar seu empréstimo, adicionando sete dias a sua data de prazo de devolvimento. Há um limite de quatro renovações consecutivas.
