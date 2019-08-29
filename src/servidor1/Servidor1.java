
package servidor1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        
        //criando o servidor
        ServerSocket servidor = new ServerSocket(5555);
        
        //aguardando a conexao
        System.out.println("AGUARDANDO REQUISIÇÃO");
        Socket soquete = servidor.accept();
        System.out.println("CONEXÃO ACEITA");
        
        //criando os streams
        ObjectOutputStream saida = new ObjectOutputStream(soquete.getOutputStream());
        ObjectInputStream entrada = new ObjectInputStream(soquete.getInputStream());
        
        //lendo a mensagem enviado
        String mensagem = (String) entrada.readObject();
        System.out.println("SERVIDOR::RECEBI: ");
        System.out.println(mensagem);
        
        String invertida = "";
      
        for(int i=mensagem.length()-1; i>=0; i--){
            invertida = invertida + mensagem.charAt(i);
        }
        
        //respondendo ao cliente
        saida.writeObject("RESPOSTA "+ invertida);
        saida.flush();
        
        //fechando os streams e a conexao
        entrada.close();
        saida.close();
        soquete.close();
                       
    }
    
}
