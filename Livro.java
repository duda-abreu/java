package lp2g17.biblioteca;

import java.util.ArrayList;
import java.util.Calendar;

public class Livro {
    private String codigoLivro;
    private String tituloLivro;
    private String categoria;
    private int quantidade;
    private int emprestados;
    private ArrayList<EmprestPara> hist;

    public Livro(String codigoLivro, String tituloLivro, String categoria, int quantidade, int emprestados,
            ArrayList<EmprestPara> hist) {
        this.codigoLivro = codigoLivro;
        this.tituloLivro = tituloLivro;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.emprestados = emprestados;
        this.hist = hist;
    }

    public void empresta() throws CopiaNaoDisponivelEx {
        if (this.emprestados < this.quantidade) {
            this.emprestados++;
        } else {
            throw new CopiaNaoDisponivelEx("Todas as copias estao emprestadas");
        }
    }

    public void devolve() throws NenhumaCopiaEmprestadaEx {
        if (this.emprestados > 0) {
            this.emprestados--;
        } else {
            throw new NenhumaCopiaEmprestadaEx("Nenhuma copia foi emprestada");
        }
    }

    public void addUsuarioHist(Calendar dataLocacao, Calendar dataDevolucao, String codigoUsuario) {
        EmprestPara emprestadoPara = new EmprestPara(dataLocacao, dataDevolucao, codigoUsuario);
        this.hist.add(emprestadoPara);
    }

    public String getCodigoLivro() {
        return this.codigoLivro;
    }

    public String getTituloLivro() {
        return this.tituloLivro;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public int getEmprestados() {
        return this.emprestados;
    }

    public ArrayList<EmprestPara> getHist() {
        return this.hist;
    }
}
