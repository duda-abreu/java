package lp2g17.biblioteca;

public class LivroNaoCadastradoEx extends Exception {
    public LivroNaoCadastradoEx() {
        super("Livro nao cadastrado na biblioteca");
    }

    public LivroNaoCadastradoEx(String mensagem) {
        super(mensagem);
    }
}
