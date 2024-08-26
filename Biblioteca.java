package lp2g17.biblioteca;

import java.util.Hashtable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.io.*;
import java.util.*;

public class Biblioteca {
    private Hashtable<Integer, Usuario> cadastroUsuarios;
    private Hashtable<String, Livro> cadastroLivros;

    public Biblioteca() {
        this.cadastroUsuarios = new Hashtable<>();
        this.cadastroLivros = new Hashtable<>();
    }

    public Biblioteca(String arquivoUsuarios, String arquivoLivros) {
        this.cadastroUsuarios = new Hashtable<>();
        this.cadastroLivros = new Hashtable<>();

        this.carregarUsuariosDoArquivo(arquivoUsuarios);
        this.carregarLivrosDoArquivo(arquivoLivros);
    }

    private void carregarUsuariosDoArquivo(String arquivoUsuarios) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoUsuarios))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dadosUsuario = linha.split(";");
                if (dadosUsuario.length >= 3) {
                    int codigo = Integer.parseInt(dadosUsuario[0]);
                    String nome = dadosUsuario[1];
                    String sobrenome = dadosUsuario[2];
                    String endereco = dadosUsuario[3];
                    Calendar dataNascimento = Calendar(dadosUsuario[4]);
                    Usuario usuario = new Usuario(nome, sobrenome, dataNascimento, endereco, codigo);
                    this.cadastroUsuarios.put(codigo, usuario);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de usuarios nao encontrado: " + arquivoUsuarios);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de usuarios: " + e.getMessage());
        }
    }

    private void carregarLivrosDoArquivo(String arquivoLivros) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoLivros))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dadosLivro = linha.split(";");
                if (dadosLivro.length >= 3) {
                    String codigo = dadosLivro[0];
                    String titulo = dadosLivro[1];
                    String categoria = dadosLivro[2];
                    int quantidade = parseInt(dadosLivro[3]);
                    int emprestados = parseInt(dadosLivro[4]);
                    ArrayList<EmprestPara> hist = dadosLivro[5];
                    Livro livro = new Livro(codigo, titulo, categoria, quantidade, emprestados, hist);
                    this.cadastroLivros.put(codigo, livro);
                } else {
                    // Ignora a linha caso nao esteja no formato esperado e continua para a proxima
                    // linha
                    continue;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de livros: " + e.getMessage());
        }
    }

    public void cadastraUsuario(Usuario usuario) {
        Usuario old_user = cadastroUsuarios.get(usuario.getCodigoUsuario());
        if (old_user == null) {
            this.cadastroUsuarios.put(usuario.getCodigoUsuario(), usuario);
        }
    }

    public void cadastraLivro(Livro livro) {
        Livro old_livro = cadastroLivros.get(livro.getCodigoLivro());
        if (old_livro == null) {
            this.cadastroLivros.put(livro.getCodigoLivro(), livro);
        }
    }

    public void salvaArquivo(Hashtable<?, ?> tabela, String nomeArquivo) {
        try (FileOutputStream fileOut = new FileOutputStream(nomeArquivo);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(tabela);
            System.out.println("Objeto salvo em " + nomeArquivo);

        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public Hashtable<?, ?> leArquivo(String nomeArquivo) {
        Hashtable<?, ?> tabela = null;

        try (FileInputStream fileIn = new FileInputStream(nomeArquivo);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            tabela = (Hashtable<?, ?>) objectIn.readObject();
            System.out.println("Objeto lido de " + nomeArquivo);

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }

        return tabela;
    }

    public void emprestaLivro(Usuario usuario, Livro livro) {
        try {
            livro.empresta();

            Calendar dataAtual = Calendar.getInstance();

            usuario.addLivroHist(dataAtual, parseInt(livro.getCodigoLivro()));

            livro.addUsuarioHist(dataAtual, null, Integer.toString(usuario.getCodigoUsuario()));

            System.out.println("Livro emprestado com sucesso para o usuario " + usuario.getNome());
        } catch (LivroNaoDisponivelEx e) {
            System.out.println("Nao foi possivel emprestar o livro. Esta indisponivel no momento.");
        } catch (CopiaNaoDisponivelEx e) {
            System.out.println("Nao foi possivel emprestar o livro. Nao ha copias disponiveis no momento.");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao emprestar o livro: " + e.getMessage());
        }
    }

    public void devolveLivro(Usuario usuario, Livro livro) {
        try {
            livro.devolve();

            Calendar dataDevolucao = Calendar.getInstance();

            livro.devolve();

            long diferencaDias = calcularDiferencaDias(dataEmprestimo, dataDevolucao);

            if (diferencaDias > 7) {
                // Se a diferenca for maior que 7 dias, aplica uma multa
                System.out.println("O livro foi devolvido com atraso. Aplicar multa ao usuario " + usuario.getNome());
            } else {
                System.out.println("Livro devolvido no prazo pelo usuario " + usuario.getNome());
            }

            usuario.removeLivroHist(livro.getCodigo());

            livro.removeUsuarioHist(usuario.getCodigoUsuario());

        } catch (NenhumaCopiaEmprestadaEx e) {
            System.out.println("O usuario nao possui nenhuma copia deste livro para devolver.");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao devolver o livro: " + e.getMessage());
        }
    }

    // Calcular a diferenca em dias entre duas datas
    private long calcularDiferencaDias(Calendar dataInicial, Calendar dataFinal) {
        long diferencaMillis = dataFinal.getTimeInMillis() - dataInicial.getTimeInMillis();
        return diferencaMillis / (24 * 60 * 60 * 1000);
    }

    public String imprimeLivros() {
        List<Livro> livrosOrdenados = new ArrayList<>(cadastroLivros.values());

        Collections.sort(livrosOrdenados,
                (livro1, livro2) -> livro1.getTitulo().compareToIgnoreCase(livro2.getTitulo()));

        StringBuilder resultado = new StringBuilder();
        resultado.append("Livros cadastrados ordenados pelo titulo:\n");
        for (Livro livro : livrosOrdenados) {
            resultado.append("Titulo: ").append(livro.getTitulo()).append(", Codigo: ").append(livro.getCodigo())
                    .append("\n");
            // Adicionar outros detalhes do livro, se necessario
        }

        return resultado.toString();
    }

    public String imprimeUsuarios() {
        List<Usuario> usuariosOrdenados = new ArrayList<>(cadastroUsuarios.values());
        Collections.sort(usuariosOrdenados,
                (usuario1, usuario2) -> usuario1.getNome().compareToIgnoreCase(usuario2.getNome()));

        StringBuilder resultado = new StringBuilder();
        resultado.append("Usuarios cadastrados ordenados pelo nome:\n");
        for (Usuario usuario : usuariosOrdenados) {
            resultado.append("Nome: ").append(usuario.getNome()).append(" ").append(usuario.getSobrenome())
                    .append(", Codigo: ").append(usuario.getCodigoUsuario()).append("\n");
            // Adicionar outros detalhes do usuario, se necessario
        }

        return resultado.toString();
    }

    public Livro getLivro(String codigoLivro) throws LivroNaoCadastradoEx {
        if (cadastroLivros.containsKey(codigoLivro)) {
            return cadastroLivros.get(codigoLivro);
        } else {
            throw new LivroNaoCadastradoEx("Livro nao cadastrado na biblioteca");
        }
    }

    public Usuario getUsuario(int codigoUsuario) throws UsuarioNaoCadastradoEx {
        if (cadastroUsuarios.containsKey(codigoUsuario)) {
            return cadastroUsuarios.get(codigoUsuario);
        } else {
            throw new UsuarioNaoCadastradoEx("Usuario nao cadastrado na biblioteca");
        }
    }

    public Hashtable<Integer, Usuario> getUsuarios() {
        return cadastroUsuarios;
    }

    public Hashtable<String, Livro> getLivros() {
        return cadastroLivros;
    }
}