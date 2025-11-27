package com.biblioteca.entity;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.PasswordType;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.*;

/**
 * Representa um usuário autenticável na aplicação. Esta entidade é usada pelo
 * provedor de identidade do Quarkus para autenticação e autorização.
 *
 * O campo {@code username} é o identificador único utilizado no formulário de
 * login. A senha é armazenada em texto claro apenas para fins de
 * desenvolvimento/teste, conforme indicado pelo {@link PasswordType#CLEAR}. Em
 * ambientes de produção é recomendável armazenar as senhas utilizando
 * algoritmos de hash como bcrypt ou fornecer um {@link PasswordType} customizado
 * conforme descrito na documentação de Quarkus【875400523079968†L135-L148】.
 *
 * O campo {@code role} utiliza uma lista de valores separados por vírgula para
 * representar os papéis atribuídos a este usuário (por exemplo, "admin,user").
 */
@Entity
@Table(name = "usuarios")
@UserDefinition
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Username
    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Password(value = PasswordType.CLEAR)
    @Column(nullable = false, length = 255)
    private String password;

    @Roles
    @Column(nullable = false, length = 255)
    private String role;

    public Usuario() {
    }

    public Usuario(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}