package lp2g17.biblioteca;

public class LivroNaoDisponivelEx extends Exception {
    public LivroNaoDisponivelEx() {
        super("Livro nao esta disponivel para emprestimo");
    }

    public LivroNaoDisponivelEx(String mensagem) {
        super(mensagem);
    }
}