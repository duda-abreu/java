package lp2g17.biblioteca;

public class UsuarioNaoCadastradoEx extends Exception {
    public UsuarioNaoCadastradoEx() {
        super("Usuario nao cadastrado na biblioteca");
    }

    public UsuarioNaoCadastradoEx(String mensagem) {
        super(mensagem);
    }
}