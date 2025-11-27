INSERT INTO autores (id, nome, email, dataNascimento, biografia)
VALUES
(1, 'Machado de Assis', 'machado@academia.com', '1839-06-21', 'Fundador da Academia Brasileira de Letras.'),
(2, 'Clarice Lispector', 'clarice@contos.com', '1920-12-10', 'Autora de romances psicológicos e modernistas.'),
(3, 'José de Alencar', 'alencar@literatura.com', '1829-05-01', 'Romancista e político brasileiro.'),
(4, 'Cecília Meireles', 'cecilia@poesia.com', '1901-11-07', 'Poetisa e educadora.'),
(5, 'Jorge Amado', 'jorge@bahia.com', '1912-08-10', 'Romancista baiano, um dos mais traduzidos do Brasil.');

INSERT INTO livros (id, titulo, isbn, dataPublicacao, numeroPaginas, disponivel, autor_id)
VALUES
(1, 'Dom Casmurro', '9788535921780', '1899-01-01', 256, false, 1),
(2, 'Memórias Póstumas de Brás Cubas', '9788535927850', '1881-01-01', 210, true, 1),
(3, 'A Hora da Estrela', '9788520926355', '1977-01-01', 96, true, 2);

INSERT INTO emprestimos (id, nomeUsuario, emailUsuario, dataEmprestimo, dataDevolucaoPrevista, dataDevolucao, observacoes, livro_id)
VALUES
(1, 'João Pereira', 'joao@email.com', '2025-09-25', '2025-10-05', '2025-10-04', 'Devolvido antes do prazo.', 3),
(2, 'Ana Costa', 'ana@email.com', '2025-09-20', '2025-09-27', NULL, 'Leitura prolongada - ainda não devolvido.', 1);

-- Usuários de teste para autenticação baseada em formulários.
-- Estes registros são utilizados pelo provedor de identidade do Quarkus
-- configurado via Jakarta Persistence. Como a entidade Usuario está
-- anotada com @Password(PasswordType.CLEAR), as senhas são armazenadas em
-- texto plano apenas para fins de desenvolvimento e testes.  Para produção,
-- consulte a documentação de Quarkus sobre hashing de senhas【875400523079968†L135-L148】.
INSERT INTO usuarios (id, username, password, role) VALUES
    (1, 'admin', 'admin', 'admin'),
    (2, 'user',  'user',  'user');
