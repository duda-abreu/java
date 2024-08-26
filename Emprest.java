package lp2g17.biblioteca;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Emprest {
    private Calendar dataEmprestimo;
    private Calendar dataDevolucao;
    private int codigoLivro;

    public Emprest(int codigoLivro) {
        this.codigoLivro = codigoLivro;
        this.dataEmprestimo = new GregorianCalendar();
        this.dataDevolucao = null;
    }

    public Emprest(int codigoLivro, Calendar dataEmpresitmo) {
        this.codigoLivro = codigoLivro;
        this.dataEmprestimo = dataEmpresitmo;
        this.dataDevolucao = null;
    }

    public void registrarDevolucao(Calendar dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean foiDevolvido() {
        return this.dataDevolucao != null;
    }

    public void registrarLocacao(Calendar dataLocacao) {
        this.dataEmprestimo = dataLocacao;
    }

    public String getDataDevolucaoString() {
        if (this.foiDevolvido()) {
            return new SimpleDateFormat("dd/MM/yyyy").format(this.dataDevolucao.getTime());
        } else {
            return "Pendente";
        }
    }

    public int getCodigoLivro() {
        return this.codigoLivro;
    }
}
