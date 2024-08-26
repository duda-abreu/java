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
                System.out.println("Opção inválida! Tente novamente.");
                break;
        }
    } while (opcao != 4);

    scanner.close();
}

private static void exibirMenuManutencao() {
    System.out.println("------ Módulo de Manutenção ------");
    System.out.println("1. Criar novos arquivos");
    System.out.println("2. Abrir arquivos");
    System.out.println("3. Salvar arquivos");
    System.out.println("4. Voltar ao menu principal");
    System.out.print("Escolha uma opção: ");
}

private static void criarNovosArquivos() {
    try {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do arquivo para usuários: ");
        String arquivoUsuarios = scanner.nextLine();

        System.out.println("Digite o nome do arquivo para livros: ");
        String arquivoLivros = scanner.nextLine();

        FileWriter arquivoUsuariosFW = new FileWriter(arquivoUsuarios + ".txt");
        FileWriter arquivoLivrosFW = new FileWriter(arquivoLivros + ".txt");

        PrintWriter pwUsuarios = new PrintWriter(arquivoUsuariosFW);
        PrintWriter pwLivros = new PrintWriter(arquivoLivrosFW);

        int numUsuarios, numLivros;

        System.out.println("Quantos usuários você deseja adicionar?");
        numUsuarios = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        for (int i = 1; i <= numUsuarios; i++) {
            System.out.println("Digite o nome do usuário " + i + ": ");
            String nomeUsuario = scanner.nextLine();
            pwUsuarios.println("Usuário " + i + ": " + nomeUsuario);
        }

        System.out.println("Quantos livros você deseja adicionar?");
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
        System.out.println("Usuários recuperados:");

        while ((linhaUsuarios = brUsuarios.readLine()) != null) {
            System.out.println(linhaUsuarios);
            // Aqui você pode processar cada linha de usuário, se necessário
        }

        String linhaLivros;
        System.out.println("\nLivros recuperados:");

        while ((linhaLivros = brLivros.readLine()) != null) {
            System.out.println(linhaLivros);
            // Aqui você pode processar cada linha de livro, se necessário
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

        // Iterando sobre os usuários e escrevendo no arquivo de usuários
        for (Usuario usuario : usuarios) {
            bwUsuarios.write(usuario.toString()); // Utilize o método toString() adequado para seus objetos
            bwUsuarios.newLine();
        }

        // Iterando sobre os livros e escrevendo no arquivo de livros
        for (Livro livro : livros) {
            bwLivros.write(livro.toString()); // Utilize o método toString() adequado para seus objetos
            bwLivros.newLine();
        }

        bwUsuarios.close();
        bwLivros.close();
        
        System.out.println("Informações salvas com sucesso!");
    } catch (IOException e) {
        System.err.println("Erro ao salvar arquivos: " + e.getMessage());
    }
}

    private static void moduloCadastro() {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\n### Módulo de Cadastro ###");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Cadastrar Livro");
            System.out.println("3. Salvar em Arquivo");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

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
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }

    private static void cadastrarNovoUsuario() {
        // Lógica para cadastrar um novo usuário
        // Você pode usar Scanner para obter os dados do usuário e criar um novo objeto Usuario
        // Adicione o objeto à lista de usuários
        // Exemplo:
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n### Cadastro de Novo Usuário ###");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Sobrenome: ");
        String sobrenome = scanner.nextLine();

        Usuario novoUsuario = new Usuario(nome, sobrenome); // Supondo que seu construtor necessite desses parâmetros
        listaUsuarios.add(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void cadastrarNovoLivro() {
        // Lógica para cadastrar um novo livro
        // Você pode usar Scanner para obter os dados do livro e criar um novo objeto Livro
        // Adicione o objeto à lista de livros
        // Exemplo:
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n### Cadastro de Novo Livro ###");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        Livro novoLivro = new Livro(titulo, autor); // Supondo que seu construtor necessite desses parâmetros
        listaLivros.add(novoLivro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void salvarCadastrosEmArquivo() {
        // Lógica para salvar as listas de usuários e livros em arquivos
        // Utilize as operações de escrita de arquivo para gravar os dados em arquivos
        // Lembre-se de adaptar a lógica para gravar os dados no formato desejado
        // Você pode percorrer as listas e escrever cada usuário e livro no arquivo
        // Exemplo:
        System.out.println("\n### Salvando em Arquivo ###");
        // Implemente aqui a lógica para salvar os usuários e livros em arquivos
    }
}

     private static void moduloEmprestimo() {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\n### Módulo de Empréstimo ###");
            System.out.println("1. Exibir Cadastro de Livros");
            System.out.println("2. Realizar Empréstimo");
            System.out.println("3. Realizar Devolução");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

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
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }

    private static void exibirCadastroDeLivros() {
        // Lógica para exibir o cadastro de livros
        // Percorra a lista de livros e exiba seus detalhes
        // Exemplo:
        System.out.println("\n### Cadastro de Livros ###");
        for (Livro livro : listaLivros) {
            System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor());
            // Exibir outros detalhes do livro, se necessário
        }
    }

private static void realizarEmprestimo() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("\n### Realizar Empréstimo ###");

    // Exibindo os livros disponíveis para empréstimo
    System.out.println("Livros Disponíveis:");
    exibirCadastroDeLivros();

    // Solicitando ao usuário o livro para empréstimo
    System.out.print("\nDigite o título do livro para empréstimo: ");
    String tituloLivro = scanner.nextLine();

    // Verificando se o livro está na lista de livros disponíveis
    Livro livroSelecionado = null;
    for (Livro livro : listaLivros) {
        if (livro.getTitulo().equalsIgnoreCase(tituloLivro)) {
            livroSelecionado = livro;
            break;
        }
    }

    if (livroSelecionado != null) {
        // Exibindo os usuários para seleção
        System.out.println("\nUsuários Cadastrados:");
        exibirCadastroDeUsuarios();

        // Solicitando ao usuário o ID do usuário para empréstimo
        System.out.print("\nDigite o ID do usuário para empréstimo: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine(); // Consumindo a quebra de linha

        // Verificando se o usuário está na lista de usuários cadastrados
        Usuario usuarioSelecionado = null;
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId() == idUsuario) {
                usuarioSelecionado = usuario;
                break;
            }
        }

        if (usuarioSelecionado != null) {
            try {
                // Realizando o empréstimo do livro para o usuário
                biblioteca.emprestaLivro(usuarioSelecionado, livroSelecionado);
                System.out.println("Empréstimo realizado com sucesso!");
            } catch (Exception e) {
                System.out.println("Erro ao realizar o empréstimo: " + e.getMessage());
            }
        } else {
            System.out.println("ID do usuário não encontrado.");
        }
    } else {
        System.out.println("Livro não encontrado.");
    }

    scanner.close();
}

private static void realizarDevolucao() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("\n### Realizar Devolução ###");

    // Exibindo os livros emprestados para devolução
    System.out.println("Livros Emprestados:");
    exibirLivrosEmprestados();

    // Solicitando ao usuário o livro para devolução
    System.out.print("\nDigite o título do livro para devolução: ");
    String tituloLivro = scanner.nextLine();

    // Verificando se o livro está na lista de livros emprestados
    Livro livroDevolvido = null;
    for (Livro livro : listaLivros) {
        if (livro.getTitulo().equalsIgnoreCase(tituloLivro) && livro.isEmprestado()) {
            livroDevolvido = livro;
            break;
        }
    }

    if (livroDevolvido != null) {
        // Obtendo o usuário associado ao livro devolvido
        Usuario usuario = livroDevolvido.getUsuarioEmprestado();

        try {
            // Realizando a devolução do livro
            biblioteca.devolveLivro(usuario, livroDevolvido);
            System.out.println("Devolução realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao realizar a devolução: " + e.getMessage());
        }
    } else {
        System.out.println("Livro não encontrado ou não está emprestado.");
    }

    scanner.close();
}

private static void exibirLivrosEmprestados() {
    // Percorrendo a lista de livros para exibir os emprestados
    for (Livro livro : listaLivros) {
        if (livro.isEmprestado()) {
            System.out.println("Título: " + livro.getTitulo() + ", Emprestado a: " + livro.getUsuarioEmprestado().getNome());
            // Exibir outras informações do livro emprestado, se necessário
        }
    }
}

private static void moduloRelatorio() {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\n### Módulo de Relatório ###");
            System.out.println("1. Listar Acervo de Livros");
            System.out.println("2. Exibir Cadastro de Usuários");
            System.out.println("3. Mostrar Detalhes de Usuário");
            System.out.println("4. Mostrar Detalhes de Livro");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

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
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    private static void listarAcervoDeLivros() {
        // Lógica para listar o acervo de livros
        // Percorra a lista de livros e liste suas informações básicas
        // Exemplo:
        System.out.println("\n### Acervo de Livros ###");
        for (Livro livro : listaLivros) {
            System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor());
            // Exibir outras informações do livro, se necessário
        }
    }

    private static void exibirCadastroDeUsuarios() {
        // Lógica para exibir o cadastro de usuários
        // Percorra a lista de usuários e exiba suas informações básicas
        // Exemplo:
        System.out.println("\n### Cadastro de Usuários ###");
        for (Usuario usuario : listaUsuarios) {
            System.out.println("Nome: " + usuario.getNome() + ", ID: " + usuario.getId());
            // Exibir outras informações do usuário, se necessário
        }
    }

private static void mostrarDetalhesUsuario() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("\n### Detalhes de Usuário ###");

    // Solicitando ao usuário o ID ou nome do usuário para visualizar os detalhes
    System.out.print("Digite o ID ou nome do usuário: ");
    String userInput = scanner.nextLine();

    boolean usuarioEncontrado = false;

    // Procurando pelo usuário na lista
    for (Usuario usuario : listaUsuarios) {
        if (usuario.getId().equalsIgnoreCase(userInput) || usuario.getNome().equalsIgnoreCase(userInput)) {
            System.out.println("Detalhes do usuário:");
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Sobrenome: " + usuario.getSobrenome());
            // Exibir outras informações do usuário, se necessário
            usuarioEncontrado = true;
            break;
        }
    }

    if (!usuarioEncontrado) {
        System.out.println("Usuário não encontrado.");
    }

    scanner.close();
}


private static void mostrarDetalhesLivro() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("\n### Detalhes de Livro ###");

    // Solicitando ao usuário o título ou ID do livro para visualizar os detalhes
    System.out.print("Digite o título ou ID do livro: ");
    String userInput = scanner.nextLine();

    boolean livroEncontrado = false;

    // Procurando pelo livro na lista
    for (Livro livro : listaLivros) {
        if (livro.getTitulo().equalsIgnoreCase(userInput) || livro.getId().equalsIgnoreCase(userInput)) {
            System.out.println("Título: " + livro.getTitulo());
            livroEncontrado = true;
            break;
        }
    }

    if (!livroEncontrado) {
        System.out.println("Livro não encontrado.");
    }

    scanner.close();
}
    
    private static void criarBancoDadosTeste() {
cadastroUsuarios.put(1, new Usuario("João", "Silva", /* outros dados */));
cadastroUsuarios.put(2, new Usuario("Maria", "Santos", /* outros dados */));
cadastroUsuarios.put(3, new Usuario("Pedro", "Oliveira", /* outros dados */));
cadastroUsuarios.put(4, new Usuario("Ana", "Souza", /* outros dados */));
cadastroUsuarios.put(5, new Usuario("Lucas", "Ferreira", /* outros dados */));

// Dados fictícios para livros (exemplo)
cadastroLivros.put("L001", new Livro("L001", "Aventuras Fantásticas", "Aventura", /* outros dados */));
cadastroLivros.put("L002", new Livro("L002", "O Segredo de Emma", "Suspense", /* outros dados */));
cadastroLivros.put("L003", new Livro("L003", "Caminho das Estrelas", "Ficção Científica", /* outros dados */));
cadastroLivros.put("L004", new Livro("L004", "Poder da Imaginação", "Fantasia", /* outros dados */));
cadastroLivros.put("L005", new Livro("L005", "Revolução dos Robôs", "Tecnologia", /* outros dados */));

// Usuário com empréstimo em vigor
Usuario usuarioComEmprestimo = cadastroUsuarios.get(1); // Por exemplo, João Silva
Livro livroEmprestado = cadastroLivros.get("L001"); // Por exemplo, Aventuras Fantásticas
try {
    biblioteca.emprestaLivro(usuarioComEmprestimo, livroEmprestado);
} catch (Exception e) {
    System.out.println("Erro ao realizar empréstimo: " + e.getMessage());
}

// Usuário com livro devolvido
Usuario usuarioComDevolucao = cadastroUsuarios.get(2); // Por exemplo, Maria Santos
Livro livroDevolvido = cadastroLivros.get("L002"); // Por exemplo, O Segredo de Emma
try {
    biblioteca.devolveLivro(usuarioComDevolucao, livroDevolvido);
} catch (Exception e) {
    System.out.println("Erro ao realizar devolução: " + e.getMessage());
}
    }
}