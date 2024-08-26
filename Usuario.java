package lp2g17.biblioteca;

import java.util.ArrayList;
import java.util.Calendar;

public class Usuario extends Pessoa {
    private String endereco;
    private int codigoUsuario;
    private ArrayList<Emprest> hist;

    public Usuario(String nome, String sobrenome, Calendar dataNascimento, String endereco, int codigoUsuario) {
        super(nome, sobrenome, dataNascimento);
        this.endereco = endereco;
        this.codigoUsuario = codigoUsuario;
        this.hist = new ArrayList<>();
    }

    public void addLivroHist(Calendar dataLocacao, int codigoLivro) {
        Emprest emprestimo = new Emprest(codigoLivro, dataLocacao);
        this.hist.add(emprestimo);
    }

    public String getEndereco() {
        return this.endereco;
    }

    public int getCodigoUsuario() {
        return this.codigoUsuario;
    }

    public ArrayList<Emprest> getHistorico() {
        return this.hist;
    }
}
