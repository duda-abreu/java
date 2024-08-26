import java.io.*;
import java.util.Hashtable;

public class BancoUsuarios {
    public static void main(String[] args) {
        Hashtable<Integer, String> cadastroUsuarios = new Hashtable<>();
        cadastroUsuarios.put(1, "Joao");
        cadastroUsuarios.put(2, "Maria");
        cadastroUsuarios.put(3, "Pedro");
        cadastroUsuarios.put(4, "Ana");
        cadastroUsuarios.put(5, "Lucas");

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("u.dat"));
            outputStream.writeObject(cadastroUsuarios);
            outputStream.close();
            System.out.println("Dados de usuarios salvos no arquivo u.dat com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados de usuarios no arquivo: " + e.getMessage());
        }
    }
}
