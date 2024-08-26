import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class P3nX {

    private static Biblioteca biblioteca;
    private static List<Usuario> listaUsuarios = new ArrayList<>();
    private static List<Livro> listaLivros = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        biblioteca = new Biblioteca();

        criarBancoDadosTeste();
        // Chama o metodo para criar os novos arquivos
    criarNovosArquivos();

    // Chama o metodo para abrir e exibir os arquivos criados
    abrirArquivos("u.dat", "l.dat");
    
    salvarArquivos("u.dat", "l.dat", listaUsuarios, listaLivros);

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    moduloManutencao();
                    break;
                case 2:
                    moduloCadastro();
                    break;
                case 3:
                    moduloEmprestimo();
                    break;
                case 4:
                    moduloRelatorio();
                    break;
                case 5:
                    System.out.println("Encerrando o programa");
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
                    break;
            }
        } while (opcao != 5);

        scanner.close();
    }


    private static void exibirMenuPrincipal() {
        System.out.println("------ Biblioteca: Sistema de Gerenciamento ------");
        System.out.println("1. Manutencao");
        System.out.println("2. Cadastro");
        System.out.println("3. Emprestimo");
        System.out.println("4. Relatorio");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opcao: ");
    }

   private static void moduloManutencao() {
    Scanner scanner = new Scanner(System.in);
    int opcao;

    do {
        exibirMenuManutencao();
        opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                criarNovosArquivos();
                break;
            case 2:
                abrirArquivos();
                break;
            case 3:
                salvarArquivos();
                break;
            case 4:
                System.out.println("Retornando ao menu principal...");
                break;
            default:
                System.out.println("Op��o inv�lida! Tente novamente.");
                break;
        }
    } while (opcao != 4);

    scanner.close();
}

private static void exibirMenuManutencao() {
    System.out.println("------ M�dulo de Manuten��o ------");
    System.out.println("1. Criar novos arquivos");
    System.out.println("2. Abrir arquivos");
    System.out.println("3. Salvar arquivos");
    System.out.println("4. Voltar ao menu principal");
    System.out.print("Escolha uma op��o: ");
}

private static void criarNovosArquivos() {
    try {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do arquivo para usu�rios: ");
        String arquivoUsuarios = scanner.nextLine();

        System.out.println("Digite o nome do arquivo para livros: ");
        String arquivoLivros = scanner.nextLine();

        FileWriter arquivoUsuariosFW = new FileWriter(arquivoUsuarios + ".txt");
        FileWriter arquivoLivrosFW = new FileWriter(arquivoLivros + ".txt");

        PrintWriter pwUsuarios = new PrintWriter(arquivoUsuariosFW);
        PrintWriter pwLivros = new PrintWriter(arquivoLivrosFW);

        int numUsuarios, numLivros;

        System.out.println("Quantos usu�rios voc� deseja adicionar?");
        numUsuarios = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        for (int i = 1; i <= numUsuarios; i++) {
            System.out.println("Digite o nome do usu�rio " + i + ": ");
            String nomeUsuario = scanner.nextLine();
            pwUsuarios.println("Usu�rio " + i + ": " + nomeUsuario);
        }

        System.out.println("Quantos livros voc� deseja adicionar?");
        numLivros = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        for (int i = 1; i <= numLivros; i++) {
            System.out.println("Digite o nome do livro " + i + ": ");
            String nomeLivro = scanner.nextLine();
            pwLivros.println("Livro " + i + ": " + nomeLivro);
        }

        pwUsuarios.close();
        pwLivros.close();

        System.out.println("Arquivos criados com sucesso!");

        scanner.close();
    } catch (IOException e) {
        System.err.println("Erro ao criar arquivos: " + e.getMessage());
    }
}

private static void abrirArquivos(String arquivoUsuarios, String arquivoLivros) {
    try {
        BufferedReader brUsuarios = new BufferedReader(new FileReader(arquivoUsuarios + ".txt"));
        BufferedReader brLivros = new BufferedReader(new FileReader(arquivoLivros + ".txt"));

        String linhaUsuarios;
        System.out.println("Usu�rios recuperados:");

        while ((linhaUsuarios = brUsuarios.readLine()) != null) {
            System.out.println(linhaUsuarios);
            // Aqui voc� pode processar cada linha de usu�rio, se necess�rio
        }

        String linhaLivros;
        System.out.println("\nLivros recuperados:");

        while ((linhaLivros = brLivros.readLine()) != null) {
            System.out.println(linhaLivros);
            // Aqui voc� pode processar cada linha de livro, se necess�rio
        }

        brUsuarios.close();
        brLivros.close();
    } catch (IOException e) {
        System.err.println("Erro ao abrir arquivos: " + e.getMessage());
    }
}

private static void salvarArquivos(String arquivoUsuarios, String arquivoLivros, List<Usuario> usuarios, List<Livro> livros) {
    try {
        BufferedWriter bwUsuarios = new BufferedWriter(new FileWriter(arquivoUsuarios + ".txt"));
        BufferedWriter bwLivros = new BufferedWriter(new FileWriter(arquivoLivros + ".txt"));

        // Iterando sobre os usu�rios e escrevendo no arquivo de usu�rios
        for (Usuario usuario : usuarios) {
            bwUsuarios.write(usuario.toString()); // Utilize o m�todo toString() adequado para seus objetos
            bwUsuarios.newLine();
        }

        // Iterando sobre os livros e escrevendo no arquivo de livros
        for (Livro livro : livros) {
            bwLivros.write(livro.toString()); // Utilize o m�todo toString() adequado para seus objetos
            bwLivros.newLine();
        }

        bwUsuarios.close();
        bwLivros.close();
        
        System.out.println("Informa��es salvas com sucesso!");
    } catch (IOException e) {
        System.err.println("Erro ao salvar arquivos: " + e.getMessage());
    }
}

    private static void moduloCadastro() {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\n### M�dulo de Cadastro ###");
            System.out.println("1. Cadastrar Usu�rio");
            System.out.println("2. Cadastrar Livro");
            System.out.println("3. Salvar em Arquivo");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha uma op��o: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    cadastrarNovoUsuario();
                    break;
                case 2:
                    cadastrarNovoLivro();
                    break;
                case 3:
                    salvarCadastrosEmArquivo();
                    break;
                case 4:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Op��o inv�lida!");
            }
        } while (opcao != 4);
    }

    private static void cadastrarNovoUsuario() {
        // L�gica para cadastrar um novo usu�rio
        // Voc� pode usar Scanner para obter os dados do usu�rio e criar um novo objeto Usuario
        // Adicione o objeto � lista de usu�rios
        // Exemplo:
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n### Cadastro de Novo Usu�rio ###");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Sobrenome: ");
        String sobrenome = scanner.nextLine();

        Usuario novoUsuario = new Usuario(nome, sobrenome); // Supondo que seu construtor necessite desses par�metros
        listaUsuarios.add(novoUsuario);
        System.out.println("Usu�rio cadastrado com sucesso!");
    }

    private static void cadastrarNovoLivro() {
        // L�gica para cadastrar um novo livro
        // Voc� pode usar Scanner para obter os dados do livro e criar um novo objeto Livro
        // Adicione o objeto � lista de livros
        // Exemplo:
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n### Cadastro de Novo Livro ###");
        System.out.print("T�tulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        Livro novoLivro = new Livro(titulo, autor); // Supondo que seu construtor necessite desses par�metros
        listaLivros.add(novoLivro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void salvarCadastrosEmArquivo() {
        // L�gica para salvar as listas de usu�rios e livros em arquivos
        // Utilize as opera��es de escrita de arquivo para gravar os dados em arquivos
        // Lembre-se de adaptar a l�gica para gravar os dados no formato desejado
        // Voc� pode percorrer as listas e escrever cada usu�rio e livro no arquivo
        // Exemplo:
        System.out.println("\n### Salvando em Arquivo ###");
        // Implemente aqui a l�gica para salvar os usu�rios e livros em arquivos
    }
}

     private static void moduloEmprestimo() {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\n### M�dulo de Empr�stimo ###");
            System.out.println("1. Exibir Cadastro de Livros");
            System.out.println("2. Realizar Empr�stimo");
            System.out.println("3. Realizar Devolu��o");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha uma op��o: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    exibirCadastroDeLivros();
                    break;
                case 2:
                    realizarEmprestimo();
                    break;
                case 3:
                    realizarDevolucao();
                    break;
                case 4:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Op��o inv�lida!");
            }
        } while (opcao != 4);
    }

    private static void exibirCadastroDeLivros() {
        // L�gica para exibir o cadastro de livros
        // Percorra a lista de livros e exiba seus detalhes
        // Exemplo:
        System.out.println("\n### Cadastro de Livros ###");
        for (Livro livro : listaLivros) {
            System.out.println("T�tulo: " + livro.getTitulo() + ", Autor: " + livro.getAutor());
            // Exibir outros detalhes do livro, se necess�rio
        }
    }

private static void realizarEmprestimo() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("\n### Realizar Empr�stimo ###");

    // Exibindo os livros dispon�veis para empr�stimo
    System.out.println("Livros Dispon�veis:");
    exibirCadastroDeLivros();

    // Solicitando ao usu�rio o livro para empr�stimo
    System.out.print("\nDigite o t�tulo do livro para empr�stimo: ");
    String tituloLivro = scanner.nextLine();

    // Verificando se o livro est� na lista de livros dispon�veis
    Livro livroSelecionado = null;
    for (Livro livro : listaLivros) {
        if (livro.getTitulo().equalsIgnoreCase(tituloLivro)) {
            livroSelecionado = livro;
            break;
        }
    }

    if (livroSelecionado != null) {
        // Exibindo os usu�rios para sele��o
        System.out.println("\nUsu�rios Cadastrados:");
        exibirCadastroDeUsuarios();

        // Solicitando ao usu�rio o ID do usu�rio para empr�stimo
        System.out.print("\nDigite o ID do usu�rio para empr�stimo: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine(); // Consumindo a quebra de linha

        // Verificando se o usu�rio est� na lista de usu�rios cadastrados
        Usuario usuarioSelecionado = null;
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId() == idUsuario) {
                usuarioSelecionado = usuario;
                break;
            }
        }

        if (usuarioSelecionado != null) {
            try {
                // Realizando o empr�stimo do livro para o usu�rio
                biblioteca.emprestaLivro(usuarioSelecionado, livroSelecionado);
                System.out.println("Empr�stimo realizado com sucesso!");
            } catch (Exception e) {
                System.out.println("Erro ao realizar o empr�stimo: " + e.getMessage());
            }
        } else {
            System.out.println("ID do usu�rio n�o encontrado.");
        }
    } else {
        System.out.println("Livro n�o encontrado.");
    }

    scanner.close();
}

private static void realizarDevolucao() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("\n### Realizar Devolu��o ###");

    // Exibindo os livros emprestados para devolu��o
    System.out.println("Livros Emprestados:");
    exibirLivrosEmprestados();

    // Solicitando ao usu�rio o livro para devolu��o
    System.out.print("\nDigite o t�tulo do livro para devolu��o: ");
    String tituloLivro = scanner.nextLine();

    // Verificando se o livro est� na lista de livros emprestados
    Livro livroDevolvido = null;
    for (Livro livro : listaLivros) {
        if (livro.getTitulo().equalsIgnoreCase(tituloLivro) && livro.isEmprestado()) {
            livroDevolvido = livro;
            break;
        }
    }

    if (livroDevolvido != null) {
        // Obtendo o usu�rio associado ao livro devolvido
        Usuario usuario = livroDevolvido.getUsuarioEmprestado();

        try {
            // Realizando a devolu��o do livro
            biblioteca.devolveLivro(usuario, livroDevolvido);
            System.out.println("Devolu��o realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao realizar a devolu��o: " + e.getMessage());
        }
    } else {
        System.out.println("Livro n�o encontrado ou n�o est� emprestado.");
    }

    scanner.close();
}

private static void exibirLivrosEmprestados() {
    // Percorrendo a lista de livros para exibir os emprestados
    for (Livro livro : listaLivros) {
        if (livro.isEmprestado()) {
            System.out.println("T�tulo: " + livro.getTitulo() + ", Emprestado a: " + livro.getUsuarioEmprestado().getNome());
            // Exibir outras informa��es do livro emprestado, se necess�rio
        }
    }
}

private static void moduloRelatorio() {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\n### M�dulo de Relat�rio ###");
            System.out.println("1. Listar Acervo de Livros");
            System.out.println("2. Exibir Cadastro de Usu�rios");
            System.out.println("3. Mostrar Detalhes de Usu�rio");
            System.out.println("4. Mostrar Detalhes de Livro");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma op��o: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    listarAcervoDeLivros();
                    break;
                case 2:
                    exibirCadastroDeUsuarios();
                    break;
                case 3:
                    mostrarDetalhesUsuario();
                    break;
                case 4:
                    mostrarDetalhesLivro();
                    break;
                case 5:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Op��o inv�lida!");
            }
        } while (opcao != 5);
    }

    private static void listarAcervoDeLivros() {
        // L�gica para listar o acervo de livros
        // Percorra a lista de livros e liste suas informa��es b�sicas
        // Exemplo:
        System.out.println("\n### Acervo de Livros ###");
        for (Livro livro : listaLivros) {
            System.out.println("T�tulo: " + livro.getTitulo() + ", Autor: " + livro.getAutor());
            // Exibir outras informa��es do livro, se necess�rio
        }
    }

    private static void exibirCadastroDeUsuarios() {
        // L�gica para exibir o cadastro de usu�rios
        // Percorra a lista de usu�rios e exiba suas informa��es b�sicas
        // Exemplo:
        System.out.println("\n### Cadastro de Usu�rios ###");
        for (Usuario usuario : listaUsuarios) {
            System.out.println("Nome: " + usuario.getNome() + ", ID: " + usuario.getId());
            // Exibir outras informa��es do usu�rio, se necess�rio
        }
    }

private static void mostrarDetalhesUsuario() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("\n### Detalhes de Usu�rio ###");

    // Solicitando ao usu�rio o ID ou nome do usu�rio para visualizar os detalhes
    System.out.print("Digite o ID ou nome do usu�rio: ");
    String userInput = scanner.nextLine();

    boolean usuarioEncontrado = false;

    // Procurando pelo usu�rio na lista
    for (Usuario usuario : listaUsuarios) {
        if (usuario.getId().equalsIgnoreCase(userInput) || usuario.getNome().equalsIgnoreCase(userInput)) {
            System.out.println("Detalhes do usu�rio:");
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Sobrenome: " + usuario.getSobrenome());
            // Exibir outras informa��es do usu�rio, se necess�rio
            usuarioEncontrado = true;
            break;
        }
    }

    if (!usuarioEncontrado) {
        System.out.println("Usu�rio n�o encontrado.");
    }

    scanner.close();
}


private static void mostrarDetalhesLivro() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("\n### Detalhes de Livro ###");

    // Solicitando ao usu�rio o t�tulo ou ID do livro para visualizar os detalhes
    System.out.print("Digite o t�tulo ou ID do livro: ");
    String userInput = scanner.nextLine();

    boolean livroEncontrado = false;

    // Procurando pelo livro na lista
    for (Livro livro : listaLivros) {
        if (livro.getTitulo().equalsIgnoreCase(userInput) || livro.getId().equalsIgnoreCase(userInput)) {
            System.out.println("T�tulo: " + livro.getTitulo());
            livroEncontrado = true;
            break;
        }
    }

    if (!livroEncontrado) {
        System.out.println("Livro n�o encontrado.");
    }

    scanner.close();
}
    
    private static void criarBancoDadosTeste() {
cadastroUsuarios.put(1, new Usuario("Jo�o", "Silva", /* outros dados */));
cadastroUsuarios.put(2, new Usuario("Maria", "Santos", /* outros dados */));
cadastroUsuarios.put(3, new Usuario("Pedro", "Oliveira", /* outros dados */));
cadastroUsuarios.put(4, new Usuario("Ana", "Souza", /* outros dados */));
cadastroUsuarios.put(5, new Usuario("Lucas", "Ferreira", /* outros dados */));

// Dados fict�cios para livros (exemplo)
cadastroLivros.put("L001", new Livro("L001", "Aventuras Fant�sticas", "Aventura", /* outros dados */));
cadastroLivros.put("L002", new Livro("L002", "O Segredo de Emma", "Suspense", /* outros dados */));
cadastroLivros.put("L003", new Livro("L003", "Caminho das Estrelas", "Fic��o Cient�fica", /* outros dados */));
cadastroLivros.put("L004", new Livro("L004", "Poder da Imagina��o", "Fantasia", /* outros dados */));
cadastroLivros.put("L005", new Livro("L005", "Revolu��o dos Rob�s", "Tecnologia", /* outros dados */));

// Usu�rio com empr�stimo em vigor
Usuario usuarioComEmprestimo = cadastroUsuarios.get(1); // Por exemplo, Jo�o Silva
Livro livroEmprestado = cadastroLivros.get("L001"); // Por exemplo, Aventuras Fant�sticas
try {
    biblioteca.emprestaLivro(usuarioComEmprestimo, livroEmprestado);
} catch (Exception e) {
    System.out.println("Erro ao realizar empr�stimo: " + e.getMessage());
}

// Usu�rio com livro devolvido
Usuario usuarioComDevolucao = cadastroUsuarios.get(2); // Por exemplo, Maria Santos
Livro livroDevolvido = cadastroLivros.get("L002"); // Por exemplo, O Segredo de Emma
try {
    biblioteca.devolveLivro(usuarioComDevolucao, livroDevolvido);
} catch (Exception e) {
    System.out.println("Erro ao realizar devolu��o: " + e.getMessage());
}
    }
}