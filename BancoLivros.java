import java.io.*;
import java.util.Hashtable;

public class BancoLivros {
    public static void main(String[] args) {
        Hashtable<String, String> cadastroLivros = new Hashtable<>();
        cadastroLivros.put("L001", "Aventuras Fantasticas");
        cadastroLivros.put("L002", "O Segredo de Emma");
        cadastroLivros.put("L003", "Caminho das Estrelas");
        cadastroLivros.put("L004", "Poder da Imaginacao");
        cadastroLivros.put("L005", "Revolucao dos Robos");

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("l.dat"));
            outputStream.writeObject(cadastroLivros);
            outputStream.close();
            System.out.println("Dados de livros salvos no arquivo l.dat com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados de livros no arquivo: " + e.getMessage());
        }
    }
}
