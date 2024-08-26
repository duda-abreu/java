package lp2g17.biblioteca;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Pessoa {
    private String nome;
    private String sobrenome;
    private Calendar dataNascimento;

    public Pessoa(String nome, String sobrenome, Calendar dataNascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSobrenome() {
        return this.sobrenome;
    }

    public String getDataNascimentoString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(this.dataNascimento.getTime());
    }

    public String toString() {
        return "Nome: " + this.nome + System.lineSeparator() +
                "Sobrenome: " + this.sobrenome + System.lineSeparator() +
                "Data de Nascimento: " + this.getDataNascimentoString() + System.lineSeparator();
    }
}