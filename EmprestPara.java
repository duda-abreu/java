package lp2g17.biblioteca;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EmprestPara {
    private Calendar dataEmprestimo;
    private Calendar dataDevolucao;
    private String codigoUsuario;

    public EmprestPara(Calendar dataEmprestimo, Calendar dataDevolucao, String codigoUsuario) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.codigoUsuario = codigoUsuario;
    }

    public Calendar getDataEmprestimo() {
        return this.dataEmprestimo;
    }

    public Calendar getDataDevolucao() {
        return this.dataDevolucao;
    }

    public String getCodigoUsuario() {
        return this.codigoUsuario;
    }

    public void setDataEmprestimo(Calendar dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucao(Calendar dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
}
