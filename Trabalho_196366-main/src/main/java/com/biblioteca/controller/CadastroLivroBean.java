package com.biblioteca.controller;

import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Livro;
import com.biblioteca.service.BibliotecaService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.RolesAllowed;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Bean responsável pela inclusão de novos livros na aplicação.
 * Esta classe é anotada com {@link RolesAllowed} para garantir que
 * apenas usuários com papel "admin" possam invocar suas ações. De acordo
 * com a documentação do Quarkus, a anotação {@code @RolesAllowed} permite
 * restringir o acesso a beans e métodos com base nos papéis presentes no
 * {@link io.quarkus.security.identity.SecurityIdentity} atual【337607969098578†L73-L77】.
 *
 * A lista de autores é carregada no método {@link #init()}, para que o
 * formulário possa apresentar as opções ao usuário. O método {@link #salvar()}
 * persiste o novo livro associado ao autor selecionado e fornece
 * feedback ao usuário através de mensagens JSF.
 */
@Named("cadastroLivroBean")
@ViewScoped
@RolesAllowed("admin")
public class CadastroLivroBean implements Serializable {

    @Inject
    BibliotecaService service;

    @Inject
    FacesContext facesContext;

    private Livro livro = new Livro();
    private Long idAutorSelecionado;
    private List<Autor> autores;

    /**
     * Carrega a lista de autores disponíveis após a construção do bean.
     */
    @PostConstruct
    public void init() {
        autores = service.listarTodosAutores();
    }

    /**
     * Persiste o novo livro associando-o ao autor selecionado.
     * Após a persistência, reinicia o formulário e exibe uma mensagem de sucesso.
     * Em caso de erro, a mensagem de exceção é exibida para o usuário.
     */
    public void salvar() {
        try {
            if (idAutorSelecionado == null || livro.getTitulo() == null || livro.getTitulo().isBlank()) {
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Informe título e autor do livro.", null));
                return;
            }
            service.salvarLivro(livro, idAutorSelecionado);
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Livro cadastrado com sucesso!", null));
            // reinicia os campos
            livro = new Livro();
            idAutorSelecionado = null;
        } catch (Exception e) {
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar livro: " + e.getMessage(), null));
        }
    }

    /**
     * Cancela a operação de cadastro redirecionando para a página inicial.
     * @return a URL para redirecionamento.
     */
    public String cancelar() {
        return "/index.xhtml?faces-redirect=true";
    }

    // Getters e setters para uso pelo JSF

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Long getIdAutorSelecionado() {
        return idAutorSelecionado;
    }

    public void setIdAutorSelecionado(Long idAutorSelecionado) {
        this.idAutorSelecionado = idAutorSelecionado;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
}